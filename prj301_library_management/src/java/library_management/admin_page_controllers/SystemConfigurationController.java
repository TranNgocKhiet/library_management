/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package library_management.admin_page_controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library_management.dao.SystemConfigDAO;
import library_management.dto.SystemConfigDTO;

/**
 *
 * @author Slayer
 */
public class SystemConfigurationController extends HttpServlet {

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
        try {
            String action = request.getParameter("action");
            SystemConfigDAO systemConfigDAO = new SystemConfigDAO();
            SystemConfigDTO systemConfig = new SystemConfigDTO();

            if (action.equals("viewlistconfig")) {
                ArrayList<SystemConfigDTO> listConfig = systemConfigDAO.listConfigs();

                request.setAttribute("configlist", listConfig);

                request.getRequestDispatcher("admin_pages/SystemConfiguration.jsp").forward(request, response);
            } else if ("updateconfig".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String key = request.getParameter("configKey");
                String value = request.getParameter("configValue");
                String description = request.getParameter("description");
                
                systemConfig.setId(id);
                systemConfig.setConfigKey(key);
                systemConfig.setConfigValue(value);
                systemConfig.setDescription(description);
                
                systemConfigDAO.updateConfigValue(systemConfig); 

                request.getRequestDispatcher("AdminController?action=viewlistconfig").forward(request, response);
            } else if ("addconfig".equals(action)) {
                String key = request.getParameter("configKey");
                String value = request.getParameter("configValue");
                String description = request.getParameter("description");

                systemConfig.setConfigKey(key);
                systemConfig.setConfigValue(value);
                systemConfig.setDescription(description);

                systemConfigDAO.addSystemConfig(systemConfig); 

                request.getRequestDispatcher("AdminController?action=viewlistconfig").forward(request, response);
            } else if ("deleteconfig".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                systemConfigDAO.deleteSystemConfig(id);
                
                request.getRequestDispatcher("AdminController?action=viewlistconfig").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SystemConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SystemConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SystemConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SystemConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
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
