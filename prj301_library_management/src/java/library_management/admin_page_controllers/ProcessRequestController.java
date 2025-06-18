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
import library_management.dao.BookRequestDAO;
import library_management.dao.BorrowRecordDAO;
import library_management.dao.FineDAO;
import library_management.dao.SystemConfigDAO;
import library_management.dto.BookRequestDTO;
import library_management.dto.BorrowRecordDTO;
import library_management.dto.FineDTO;
import library_management.dto.SystemConfigDTO;

/**
 *
 * @author quang
 */
public class ProcessRequestController extends HttpServlet {

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
            BookRequestDAO rDAO = new BookRequestDAO();
            BorrowRecordDAO bDAO = new BorrowRecordDAO();
            SystemConfigDAO sDAO = new SystemConfigDAO();
            FineDAO fDAO = new FineDAO();
            String action = request.getParameter("action");
            if (action != null) {
                if ("processrequest".equals(action)) {
                    ArrayList<BookRequestDTO> rList = rDAO.getAllBookRequests();
                    request.setAttribute("requestlist", rList);
                    request.getRequestDispatcher("/admin_pages/ProcessRequest.jsp").forward(request, response);
                } else if ("requesthandle".equals(action)) {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    String requestType = request.getParameter("requestType");
                    String status = request.getParameter("status");
                    BorrowRecordDTO borrowRecord = bDAO.getBorrowRecord(userId, bookId);
                    if ("pending".equals(status)) {
                        request.setAttribute("error", "Nothing change !");
                    } else if ("rejected".equals(status)) {
                        if (rDAO.setRequestStatusById(id, status)) {
                            request.setAttribute("result", "This request has been successfully rejected!");
                        }
                    } else {
                        if ("borrow".equals(requestType)) {
                            if (borrowRecord != null) {
                                request.setAttribute("error", "This user has already borrowed this book.");
                            } else {
                                LocalDate borrow = LocalDate.now();
                                java.sql.Date borrowDate = java.sql.Date.valueOf(borrow);
                                LocalDate due = borrow.plusDays(Integer.parseInt(sDAO.getConfigValueByKey("default_borrow_duration_days")));
                                java.sql.Date dueDate = java.sql.Date.valueOf(due);
                                if (bDAO.addBorrowRecord(new BorrowRecordDTO(userId, bookId, borrowDate, dueDate, null, "borrowed")) && rDAO.setRequestStatusById(id, status)) {
                                    request.setAttribute("result", "Borrow request has been successfully accepted!");
                                } else {
                                    request.setAttribute("error", "Unknow error");
                                }
                            }
                        } else if ("return".equals(requestType)) {
                            if (borrowRecord == null) {
                                request.setAttribute("error", "No book borrowing history found. Unable to process book return request!");
                            } else {
                                long overdueDays = ChronoUnit.DAYS.between(borrowRecord.getDueDate().toLocalDate(), LocalDate.now());
                                double fine = overdueDays > 0
                                        ? overdueDays * Double.parseDouble(sDAO.getConfigValueByKey("overdue_fine_per_day")) : 0.0;
                                String paidStatus = fine == 0.0 ? "paid" : "unpaid";
                                fDAO.addFine(new FineDTO(borrowRecord.getId(), fine, paidStatus));
                                String afterHandleStatus = overdueDays > 0 ? "overdue" : "returned";
                                if (bDAO.updateReturnStatus(userId, bookId, java.sql.Date.valueOf(LocalDate.now()), afterHandleStatus) && rDAO.setRequestStatusById(id, status)) {
                                    request.setAttribute("result", "Return request has been successfully accepted!");
                                } else {
                                    request.setAttribute("error", "Failed to process return.");
                                }

                            }
                        }
                    }
                    ArrayList<BookRequestDTO> rList = rDAO.getAllBookRequests();
                    request.setAttribute("requestlist", rList);
                    request.getRequestDispatcher("/admin_pages/ProcessRequest.jsp").forward(request, response);
                }
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
