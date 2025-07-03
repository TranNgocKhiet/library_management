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
import java.util.ArrayList;
import library_management.dao.BookRequestDAO;
import library_management.dao.BorrowRecordDAO;
import library_management.dto.BookRequestDTO;
import library_management.dto.BorrowRecordDTO;
import library_management.utils.SessionUtils;

/**
 *
 * @author quang
 */
public class ReturnBookPageController extends HttpServlet {

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
            BorrowRecordDAO rDAO = new BorrowRecordDAO();
            String action = request.getParameter("action");
            HttpSession session = request.getSession(false);
            Integer userId = SessionUtils.getLoggedUserId(session);
            if ("viewreturnpage".equals(action)) {
                ArrayList<BorrowRecordDTO> rList = rDAO.getUnreturnedRecords(userId);
                request.setAttribute("unreturnlist", rList);
                request.getRequestDispatcher("/user_pages/ReturnBookPage.jsp").forward(request, response);
            } else if ("sendreturnrequest".equals(action)) {
                BookRequestDAO requestDAO = new BookRequestDAO();
                int bookId = Integer.parseInt(request.getParameter("bookid"));
                BookRequestDTO returnRequest = new BookRequestDTO();
                returnRequest.setBookId(bookId);
                returnRequest.setUserId(userId);
                returnRequest.setRequestType("return");
                returnRequest.setStatus("pending");
                returnRequest.setRequestDate(new Date(System.currentTimeMillis()));
                if(requestDAO.addBookRequest(returnRequest)){
                    request.setAttribute("result", "Book return request has been sent successfully!");
                }else{
                     request.setAttribute("error", "Unknow error!");
                }
                ArrayList<BorrowRecordDTO> rList = rDAO.getUnreturnedRecords(userId);
                request.setAttribute("unreturnlist", rList);
                request.getRequestDispatcher("/user_pages/ReturnBookPage.jsp").forward(request, response);
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
