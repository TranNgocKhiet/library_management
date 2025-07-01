/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package library_management.user_page_controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library_management.dao.BookDAO;
import library_management.dto.BookDTO;
import library_management.utils.SessionUtils;

/**
 *
 * @author Slayer
 */
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        BookDAO bookDAO = new BookDAO();
        ArrayList<BookDTO> bookList = null;
        bookList = bookDAO.listBook();
        request.setAttribute("booklist", bookList);
        if (action == null || action.equals("viewuserhomepage")) {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else if (action.equals("search")) {
            String seacrhValue = request.getParameter("searchvalue").toLowerCase();
            bookList = bookDAO.listBook(seacrhValue);
            request.setAttribute("booklist", bookList);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else if (action.equals("savetoborrowlist")) {
            HttpSession session = request.getSession(false);
            if (session == null || SessionUtils.getLoggedUser(session) == null) {
                request.setAttribute("message", "You must be logged in to use this feature.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            boolean bookExist = false;
            int borrowId = Integer.parseInt(request.getParameter("borrowid"));
            ArrayList<BookDTO> borrowList = (ArrayList<BookDTO>) session.getAttribute("borrowlist");

            if (borrowList == null) {
                borrowList = new ArrayList<>();
                session.setAttribute("borrowlist", borrowList);
            }

            for (BookDTO book : borrowList) {
                if (book.getId() == borrowId) {
                    bookExist = true;
                    break;
                }
            }

            if (!bookExist) {
                borrowList.add(bookDAO.getBookById(borrowId));
            }

            request.getRequestDispatcher("SearchController?action=homeBookSearch").forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
