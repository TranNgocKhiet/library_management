/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package library_management.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import library_management.dao.BookDAO;
import library_management.dao.UserDAO;
import library_management.dto.BookDTO;
import library_management.dto.UserDTO;

/**
 *
 * @author quang
 */
public class SearchController extends HttpServlet {

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
            BookDAO bookDAO = new BookDAO();
            ArrayList<BookDTO> bookList = null;
            String searchValue = request.getParameter("searchvalue").toLowerCase();
            bookList = bookDAO.listBook(searchValue);
            request.setAttribute("booklist", bookList);
            if ("editBoookSearch".equals(action)) {
                request.getRequestDispatcher("/admin_pages/EditBookForm.jsp").forward(request, response);
            } else if ("homeBookSearch".equals(action)) {
                if (searchValue != null && !searchValue.trim().isEmpty()) {
                    String encodedSearchValue = URLEncoder.encode(searchValue, "UTF-8");
                    Cookie searchValueCookie = new Cookie("searchCookie", encodedSearchValue);
                    searchValueCookie.setMaxAge(2332800);
                    response.addCookie(searchValueCookie);
                }
                if (bookList == null || bookList.isEmpty()) {
                    request.setAttribute("error", "No Book Found !");
                }
                request.getRequestDispatcher("/home.jsp").forward(request, response);
            } else if ("adminAccountSearch".equals(action)) {
                UserDAO usDAO = new UserDAO();
                ArrayList<UserDTO> usList = usDAO.getUserByEmail(searchValue);
                request.setAttribute("usList", usList);
                request.getRequestDispatcher("/admin_pages/ManageAccountController.jsp").forward(request, response);
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
