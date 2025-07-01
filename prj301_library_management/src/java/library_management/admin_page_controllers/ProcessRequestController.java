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
import library_management.dao.BookRequestDAO;
import library_management.dao.BorrowRecordDAO;
import library_management.dao.FineDAO;
import library_management.dao.HandledRequestDAO;
import library_management.dto.BookRequestDTO;
import library_management.dto.BorrowRecordDTO;
import library_management.dto.HandledRequestDTO;

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
                        HandledRequestDAO hDAO = new HandledRequestDAO();
                        if ("borrow".equals(requestType)) {
                            if (borrowRecord != null) {
                                request.setAttribute("error", "This user has already borrowed this book.");
                            } else {
                                if (hDAO.addHandledRequest(new HandledRequestDTO(id, userId, bookId, requestType, "pending")) && rDAO.setRequestStatusById(id, status)) {
                                    request.setAttribute("result", "Borrow request has been successfully accepted!");
                                } else {
                                    request.setAttribute("error", "Unknow error");
                                }
                            }
                        } else if ("return".equals(requestType)) {
                            if (borrowRecord == null) {
                                request.setAttribute("error", "No book borrowing history found. Unable to process book return request!");
                            } else {
                                 if (hDAO.addHandledRequest(new HandledRequestDTO(id, userId, bookId, requestType, "pending")) && rDAO.setRequestStatusById(id, status)) {
                                    request.setAttribute("result", "Return request has been successfully accepted!");
                                } else {
                                    request.setAttribute("error", "Unknow error");
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
