/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package library_management.admin_page_controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import library_management.dao.StatisticsDAO;
import library_management.dto.BookDTO;
import library_management.dto.MonthlyBorrowingStatistics;

/**
 *
 * @author quang
 */
public class StatisticsPageController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            StatisticsDAO sdao = new StatisticsDAO();
            if ("viewstatistics".equals(action)) {
                int totalBooks = sdao.getTotalBooks();
                int totalUsers = sdao.getTotalUsers();
                int currentlyBorrowed = sdao.getCurrentlyBorrowedBooks();
                double averageDuration = sdao.getAverageBorrowDuration();

                ArrayList<BookDTO> mostBorrowed = sdao.getMostBorrowedBooks();
                ArrayList<MonthlyBorrowingStatistics> borrowingStats = sdao.getMonthlyBorrowingStats();

                // Set vào request để truyền sang JSP
                request.setAttribute("totalBooks", totalBooks);
                request.setAttribute("totalUsers", totalUsers);
                request.setAttribute("currentlyBorrowed", currentlyBorrowed);
                request.setAttribute("averageDuration", averageDuration);
                request.setAttribute("mostBorrowed", mostBorrowed);
                request.setAttribute("borrowingStats", borrowingStats);

                request.getRequestDispatcher("/admin_pages/StatisticsPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
