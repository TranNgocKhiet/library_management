<%-- 
    Document   : borrowrecords
    Created on : May 23, 2025, 9:41:48 AM
    Author     : Slayer
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BorrowRecordDTO"%>
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
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            
            <tbody>
                            
                    <%  
                        ArrayList<BorrowRecordDTO> borrowRecordsList = (ArrayList<BorrowRecordDTO>) request.getAttribute("borrowrecordslist");
                        if (borrowRecordsList != null) {
                            for (BorrowRecordDTO record : borrowRecordsList) {
                                pageContext.setAttribute("record", record);
                    %>
                <tr>
                    <td><%= record.getId() %></td>
                    <td><%= record.getUserId() %></td>
                    <td><%= record.getBookId() %></td>
                    <td><%= record.getTitle() %></td>
                    <td><%= record.getBorrowDate() %></td>
                    <td><%= record.getDueDate() %></td>
                    <td><%= record.getReturnDate() %></td>
                    <td><%= record.getStatus() %></td>
                    <% String status = record.getStatus();
                        if (status.equals("borrowed")) { %>
                        <td>
                            <form action="UserController" method="POST">
                                <input type="hidden" name="returnid" value="<%= record.getBookId() %>">
                                <input type="hidden" name="action" value="requesttoreturnbook">
                                <input type="submit" value="Return Book">
                            </form>
                        </td>
                    <% } %>
                </tr>
               <%       }         
                    }
                    if (borrowRecordsList == null || borrowRecordsList.isEmpty()) { %>
                        <tr>
                            <td colspan="2">No records found.</td>
                        </tr>
                <%  } %>
            </tbody>
        </table>
    </body>
</html>
