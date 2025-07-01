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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Date;
import library_management.dao.BookDAO;
import library_management.dao.BorrowRecordDAO;
import library_management.dao.FineDAO;
import library_management.dao.HandledRequestDAO;
import library_management.dao.SystemConfigDAO;
import library_management.dto.BorrowRecordDTO;
import library_management.dto.FineDTO;
import library_management.dto.HandledRequestDTO;

/**
 *
 * @author quang
 */
public class HandledRequestController extends HttpServlet {

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
            HandledRequestDAO hDAO = new HandledRequestDAO();
            SystemConfigDAO sDAO = new SystemConfigDAO();
            BookDAO bDAO = new BookDAO();
            BorrowRecordDAO rDAO = new BorrowRecordDAO();
            
            String action = request.getParameter("action");
            if ("handledrequest".equals(action)) {
                ArrayList<HandledRequestDTO> hList = hDAO.getAllHandledRequest();
                request.setAttribute("hList", hList);
                request.getRequestDispatcher("/admin_pages/HandledRequest.jsp").forward(request, response);
            } else if ("submitborrow".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userid"));
                int bookId = Integer.parseInt(request.getParameter("bookid"));
                int handledRequestId = Integer.parseInt(request.getParameter("handledrequestid"));
                String borrowDateStr = request.getParameter("borrowdate");
                LocalDate borrow = LocalDate.parse(borrowDateStr);
                java.sql.Date borrowDate = java.sql.Date.valueOf(borrow);
                LocalDate due = borrow.plusDays(Integer.parseInt(sDAO.getConfigValueByKey("default_borrow_duration_days")));
                java.sql.Date dueDate = java.sql.Date.valueOf(due);
                if (rDAO.addBorrowRecord(new BorrowRecordDTO(userId, bookId, borrowDate, dueDate, null, "borrowed"))
                        && hDAO.setHandledRequestStatusById(handledRequestId, "handled")
                        && bDAO.updateAvailableCopies(bookId, -1)) {
                    request.setAttribute("result", "Check out book successfully!");
                } else {
                    request.setAttribute("error", "Unknown error");
                }
                 ArrayList<HandledRequestDTO> hList = hDAO.getAllHandledRequest();
                request.setAttribute("hList", hList);
                request.getRequestDispatcher("/admin_pages/HandledRequest.jsp").forward(request, response);
            } else if ("submitreturn".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userid"));
                int bookId = Integer.parseInt(request.getParameter("bookid"));
                int handledRequestId = Integer.parseInt(request.getParameter("handledrequestid"));
                String returnDateStr = request.getParameter("returndate");
                LocalDate returnLocalDate = LocalDate.parse(returnDateStr);
                Date returnDate = java.sql.Date.valueOf(returnLocalDate);
                BorrowRecordDTO borrowRecord = rDAO.getBorrowRecord(userId, bookId);
                long overdueDays = ChronoUnit.DAYS.between(borrowRecord.getDueDate().toLocalDate(), returnLocalDate);
                double fine = overdueDays > 0
                        ? overdueDays * Double.parseDouble(sDAO.getConfigValueByKey("overdue_fine_per_day")) : 0.0;
                if (fine == 0.0) {
                    if (rDAO.updateReturnStatus(userId, bookId, returnDate, "returned")
                            && hDAO.setHandledRequestStatusById(handledRequestId, "handled")
                            && bDAO.updateAvailableCopies(bookId, 1)) {
                        request.setAttribute("result", "Check in book successfully!");
                    } else {
                        request.setAttribute("error", "Unknow error");
                    }
                } else {
                    FineDAO fDAO = new FineDAO();
                    if (rDAO.updateReturnStatus(userId, bookId, returnDate, "overdue")
                            && hDAO.setHandledRequestStatusById(handledRequestId, "handled")
                            && bDAO.updateAvailableCopies(bookId, 1) && fDAO.addFine(new FineDTO(borrowRecord.getId(), fine, "unpaid"))) {
                        request.setAttribute("finedhandle", "This user has borrowed a book that is overdue.<br>Total fine: "
                                + fine + " VND.<br>Do you want to go to the Fine Control page?");
                    } else {
                        request.setAttribute("error", "Unknown error");
                    }
                }
                ArrayList<HandledRequestDTO> hList = hDAO.getAllHandledRequest();
                request.setAttribute("hList", hList);
                request.getRequestDispatcher("/admin_pages/HandledRequest.jsp").forward(request, response);
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
