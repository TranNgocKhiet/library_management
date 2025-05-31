/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import library_management.dto.BookDTO;
import library_management.dao.UserDAO;
import library_management.dto.UserDTO;

/**
 *
 * @author Slayer
 */
public class LoginController extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");        
        String name = request.getParameter("name");
        
        String password = request.getParameter("password");
        UserDAO dao = new UserDAO();
        UserDTO user = dao.login(name, password);
        
        if (user != null && user.getStatus().equals("active")){  
            if (user.getRole().equals("admin")) {
                HttpSession session = request.getSession(true);

                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("password", user.getPassword());
                session.setAttribute("role", user.getRole());
                session.setAttribute("status", user.getStatus());

                ArrayList<BookDTO> borrowList = new ArrayList<BookDTO>();

                session.setAttribute("borrowlist", borrowList);

                request.getRequestDispatcher("AdminController?action=viewadminhomepage").forward(request, response);
            } else if (user.getRole().equals("user")){
                HttpSession session = request.getSession(true);

                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("password", user.getPassword());
                session.setAttribute("role", user.getRole());
                session.setAttribute("status", user.getStatus());

                ArrayList<BookDTO> borrowList = new ArrayList<BookDTO>();

                session.setAttribute("borrowlist", borrowList);

                request.getRequestDispatcher("UserController?action=viewuserhomepage").forward(request, response);
            }
        } else {
            if (user != null && !user.getStatus().equals("active")) {
                request.setAttribute("error", "Account unactive!");

            } else {
                request.setAttribute("error", "Username or password is incorrect!");
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
