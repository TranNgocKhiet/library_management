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
        
        if (action.equals("viewborrowlist")) {
            request.getRequestDispatcher("user_pages/borrowlist.jsp").forward(request, response);
        } else if (action.equals("removefromborrowlist")) {
            HttpSession session = request.getSession(false);
            int deleteId = Integer.parseInt(request.getParameter("removeid"));
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
            HttpSession session = request.getSession(false);
            ArrayList<BookDTO> borrowList = (ArrayList<BookDTO>) session.getAttribute("borrowlist");  
            
            for (BookDTO book : borrowList) {
                BookRequestDTO bookRequest = new BookRequestDTO();
                int userId = (int) session.getAttribute("id");
                
                bookRequest.setUserId(userId);
                bookRequest.setBookId(book.getId());
                bookRequest.setRequestDate(new java.sql.Date(System.currentTimeMillis()));
                bookRequest.setStatus("pending");
                bookRequestDAO.addBookRequest(bookRequest);
            }
            
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
