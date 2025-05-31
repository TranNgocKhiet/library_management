<%-- 
    Document   : home
    Created on : May 16, 2025, 11:36:15 AM
    Author     : Slayer
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dao.BookDAO"%>
<%@page import="library_management.dto.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home Page</title>
        <style>
            .search-bar {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Home</h1>
        
        <%@include file="/user_pages/dashboard.jsp" %>

        <br>
        
        <form action="UserController" name='search' method="POST" class="search-bar">
            <input type="text" name="searchvalue" value="<%=request.getParameter("searchvalue")!=null?request.getParameter("searchvalue"):""%>">
            <input type="hidden"  name="action" value="search">
            <input type="submit" value="Search">
        </form>
        
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
                        ArrayList<BookDTO> bookList = (ArrayList<BookDTO>) request.getAttribute("booklist");
                        if (bookList != null) {
                            for (BookDTO book : bookList) {
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
                            <input type="hidden" name="borrowid" value="<%= book.getId() %>">
                            <input type="hidden" name="action" value="savetoborrowlist">
                            <input type="submit" value="Save To Shelf">
                        </form>
                    </td>
                </tr>
               <%       }         
                    }
                    if (bookList == null || bookList.isEmpty()) { %>
                        <tr>
                            <td colspan="2">No books found.</td>
                        </tr>
                <%  } %>
            </tbody>
        </table>
    </body>
</html>
