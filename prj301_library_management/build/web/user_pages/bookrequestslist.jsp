<%-- 
    Document   : requestlist
    Created on : May 22, 2025, 11:48:36 PM
    Author     : Slayer
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BookRequestDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrow History Page</title>
    </head>
    <body>
        <h1>Borrow History</h1>
        
        <%@include file="/user_pages/dashboard.jsp" %>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Request Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            
            <tbody>
                    <%  
                        ArrayList<BookRequestDTO> bookRequestsList = (ArrayList<BookRequestDTO>) request.getAttribute("bookrequestslist");
                        if (bookRequestsList != null) {
                            for (BookRequestDTO bookRequest : bookRequestsList) {
                                pageContext.setAttribute("request", request);
                    %>
                <tr>
                    <td><%= bookRequest.getId() %></td>
                    <td><%= bookRequest.getUserId() %></td>
                    <td><%= bookRequest.getBookId() %></td>
                    <td><%= bookRequest.getTitle() %></td>
                    <td><%= bookRequest.getRequestDate() %></td>
                    <td><%= bookRequest.getStatus() %></td>
                </tr>
               <%       }         
                    }
                    if (bookRequestsList == null || bookRequestsList.isEmpty()) { %>
                        <tr>
                            <td colspan="2">No history found.</td>
                        </tr>
                <%  } %>
            </tbody>
        </table>
    </body>
</html>
