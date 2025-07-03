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
import jakarta.websocket.Session;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library_management.dao.BookRequestDAO;
import library_management.dto.BookRequestDTO;
import library_management.dto.BookDTO;
import java.time.LocalDate;
import java.sql.Date;
import library_management.utils.SessionUtils;

/**
 *
 * @author Slayer
 */
public class BorrowListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        BookRequestDAO bookRequestDAO = new BookRequestDAO();
        HttpSession session = request.getSession(false);
        Integer userId = SessionUtils.getLoggedUserId(session);
        if (action.equals("viewborrowlist")) {
            request.getRequestDispatcher("user_pages/borrowlist.jsp").forward(request, response);
        } else if (action.equals("removefromborrowlist")) {
            int deleteId = Integer.parseInt(request.getParameter("deleteid"));
            ArrayList<BookDTO> borrowList = (ArrayList<BookDTO>) session.getAttribute("borrowlist");

            BookDTO removeBook = null;
            for (BookDTO book : borrowList) {
                if (book.getId() == deleteId) {
                    removeBook = book;
                    break;
                }
            }

            if (removeBook != null) {
                borrowList.remove(removeBook);
            }
            request.getRequestDispatcher("user_pages/borrowlist.jsp").forward(request, response);
        } else if (action.equals("sendbookrequest")) {

            ArrayList<BookDTO> borrowList = (ArrayList<BookDTO>) session.getAttribute("borrowlist");
            int result = bookRequestDAO.insertBorrowedRequest(userId, borrowList);
            if (result <= 0) {
                request.setAttribute("error", "Unknown error occurred while sending book request.");
            } else {
                request.setAttribute("message", "Book request sent successfully!");
            }
            session.removeAttribute("borrowlist");
            request.getRequestDispatcher("user_pages/borrowlist.jsp").forward(request, response);
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
            Logger.getLogger(BorrowListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowListController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BorrowListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrowListController.class.getName()).log(Level.SEVERE, null, ex);
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
