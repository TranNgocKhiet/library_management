/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package library_management.admin_page_controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library_management.dao.BookDAO;
import library_management.dto.BookDTO;
import library_management.utils.IntUtils;

/**
 *
 * @author quang
 */
public class EditBookController extends HttpServlet {

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
        BookDAO dao = new BookDAO();
        if (action != null) {
            if ("editBook".equals(action)) {
                request.getRequestDispatcher("/admin_pages/EditBook.jsp").forward(request, response);
            } else if ("showAddForm".equals(action)) {
                request.getRequestDispatcher("/admin_pages/AddBookForm.jsp").forward(request, response);
            } else if ("addBookSubmit".equals(action)) {
                String title = request.getParameter("title");

                String author = request.getParameter("author");

                String isbn = request.getParameter("isbn");

                String category = request.getParameter("category");

                String image = request.getParameter("image");

                String publishedYearStr = request.getParameter("published_year");
                String totalCopiesStr = request.getParameter("total_copies");
                String availableCopiesStr = request.getParameter("available_copies");
                int publishedYear = 0;
                int totalCopies = 0;
                int availableCopies = 0;
                if (title == null || title.trim().isEmpty()
                        || author == null || author.trim().isEmpty()
                        || isbn == null || isbn.trim().isEmpty()) {
                    request.setAttribute("error", "Fields (*) cannot be left blank!");
                    request.getRequestDispatcher("/admin_pages/AddBookForm.jsp").forward(request, response);
                }
                if ((publishedYearStr != null
                        && !publishedYearStr.trim().isEmpty()
                        && !IntUtils.isNumber(publishedYearStr.trim()))
                        || (totalCopiesStr != null
                        && !totalCopiesStr.trim().isEmpty()
                        && !IntUtils.isNumber(totalCopiesStr.trim()))
                        || (availableCopiesStr != null
                        && !availableCopiesStr.trim().isEmpty()
                        && !IntUtils.isNumber(availableCopiesStr.trim()))) {
                    request.setAttribute("error", "publishedYear, totalCopies, availableCopies have to is number !");
                    request.getRequestDispatcher("/admin_pages/AddBookForm.jsp").forward(request, response);
                } else {
                    if (publishedYearStr != null && !publishedYearStr.trim().isEmpty()) {
                        publishedYear = Integer.parseInt(publishedYearStr.trim());
                    }

                    if (totalCopiesStr != null && !totalCopiesStr.trim().isEmpty()) {
                        totalCopies = Integer.parseInt(totalCopiesStr.trim());
                    }

                    if (availableCopiesStr != null && !availableCopiesStr.trim().isEmpty()) {
                        availableCopies = Integer.parseInt(availableCopiesStr.trim());
                    }
                    if (dao.addBook(title, author, isbn, category, publishedYear, totalCopies, availableCopies, image)) {
                        request.setAttribute("result", "Add book successfully!");
                    } else {
                        request.setAttribute("error", "The book is available in the library!");
                    }
                }
                request.getRequestDispatcher("/admin_pages/AddBookForm.jsp").forward(request, response);

            } else if ("showEditForm".equals(action)) {
                ArrayList<BookDTO> bookList = dao.listBook();
                request.setAttribute("booklist", bookList);
                request.getRequestDispatcher("/admin_pages/EditBookForm.jsp").forward(request, response);
            } else if ("editBookSubmit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                String publishedYearStr = request.getParameter("published_year");

                int publishedYear = 0;
                if (publishedYearStr != null && !publishedYearStr.trim().isEmpty()) {
                    publishedYear = Integer.parseInt(publishedYearStr.trim());
                }

                String totalCopiesStr = request.getParameter("total_copies");

                int totalCopies = 0;
                if (totalCopiesStr != null && !totalCopiesStr.trim().isEmpty()) {
                    totalCopies = Integer.parseInt(totalCopiesStr.trim());
                }

                String availableCopiesStr = request.getParameter("available_copies");

                int availableCopies = 0;
                if (availableCopiesStr != null && !availableCopiesStr.trim().isEmpty()) {
                    availableCopies = Integer.parseInt(availableCopiesStr.trim());
                }

                String status = request.getParameter("status");
                String image = request.getParameter("image");

                if (image == null) {
                    image = ""; 
                }

                String category = request.getParameter("category");

                if (category == null) {
                    category = "";
                }

                if (dao.editBook(id, category, publishedYear, totalCopies, availableCopies, image, status)) {
                    request.setAttribute("result", "Update book successfully!");
                } else {
                    request.setAttribute("error", "Unknown Error!");
                }

                ArrayList<BookDTO> bookList = dao.listBook();
                request.setAttribute("booklist", bookList);
                request.getRequestDispatcher("/admin_pages/EditBookForm.jsp").forward(request, response);

            }
        }
        try {

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
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
