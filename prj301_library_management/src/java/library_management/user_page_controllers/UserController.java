/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.user_page_controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Slayer
 */
public class UserController extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "login.jsp"; 
        String action = request.getParameter("action");
        
        try {
            if (action != null) {
                action = action.toLowerCase(); 
            } else {
                action = ""; 
            }
            switch (action) {
                case "viewuserhomepage":
                    url = "HomeController";
                    break;
                case "edit":
                    url = "EditController";
                    break;
                case "search":
                    url = "HomeController";
                    break;
                case "savetoborrowlist":
                    url = "HomeController";
                    break;
                case "viewborrowlist":
                    url = "BorrowListController";
                    break;    
                case "removefromborrowlist":
                    url = "BorrowListController";
                    break;   
                case "sendbookrequest":
                    url = "BorrowListController";
                    break;   
                case "viewborrowrequests":
                    url = "BookRequestsController";
                    break; 
                case "viewborrowrecords":
                    url = "BorrowRecordsController";
                    break; 
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("User Controller Error: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
