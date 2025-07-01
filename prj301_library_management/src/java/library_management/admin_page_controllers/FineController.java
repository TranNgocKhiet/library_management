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
import java.util.ArrayList;
import library_management.dao.FineDAO;
import library_management.dto.FineDTO;

/**
 *
 * @author quang
 */
public class FineController extends HttpServlet {

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
            FineDAO fDAO = new FineDAO();
            String action = request.getParameter("action");
            if ("finecontrol".equals(action)) {
                ArrayList<FineDTO> fineList = fDAO.listFines();
                request.setAttribute("fList", fineList);
                request.getRequestDispatcher("/admin_pages/FineControl.jsp").forward(request, response);
            } else if("updatefine".equals(action)){
                String newStatus = request.getParameter("newstatus");
                int id = Integer.parseInt(request.getParameter("fineid"));
                if(fDAO.updatePaidStatus(id, newStatus)){
                     request.setAttribute("result", "Update fine status successfully!");
                    } else {
                        request.setAttribute("error", "Unknow error");
                    }
                ArrayList<FineDTO> fineList = fDAO.listFines();
                request.setAttribute("fList", fineList);
                request.getRequestDispatcher("/admin_pages/FineControl.jsp").forward(request, response);
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
