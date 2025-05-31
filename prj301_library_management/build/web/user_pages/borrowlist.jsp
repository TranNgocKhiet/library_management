<%-- 
    Document   : borrowlist
    Created on : May 22, 2025, 8:12:45 PM
    Author     : Slayer
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BookDTO"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrow Shelf Page</title>
    </head>
    <body>
        <h1>My Shelf</h1>
        
        <%@include file="/user_pages/dashboard.jsp" %>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Isbn</th>
                    <th>Category</th>
                    <th>Published Year</th>
                    <th>Total Copies</th>
                    <th>Available Copies</th>
                    <th>Status</th>
                </tr>
            </thead>
            
            <tbody>
                            
                    <%  
                        ArrayList<BookDTO> borrowList = (ArrayList<BookDTO>) session.getAttribute("borrowlist");
                        if (borrowList != null) {
                            for (BookDTO book : borrowList) {
                                pageContext.setAttribute("book", book);
                    %>
                <tr>
                    <td><%= book.getId() %></td>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getIsbn() %></td>
                    <td><%= book.getCategory() %></td>
                    <td><%= book.getPublishedYear() %></td>
                    <td><%= book.getTotalCopies() %></td>
                    <td><%= book.getAvailableCopies() %></td>
                    <td><%= book.getStatus() %></td>
                    <td>
                        <form action="UserController" method="POST">
                            <input type="hidden" name="deleteid" value="<%= book.getId() %>">
                            <input type="hidden" name="action" value="removefromborrowlist">
                            <input type="submit" value="Remove From Shelf">
                        </form>
                    </td>
                </tr>
               <%       }         
                    }
                    if (borrowList == null || borrowList.isEmpty()) { %>
                        <tr>
                            <td colspan="2">No books found.</td>
                        </tr>
                <%  } %>
            </tbody>
        </table>
        
        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="sendbookrequest">
            <input type="submit" value="Send book request">
        </form>
    </body>
</html>
