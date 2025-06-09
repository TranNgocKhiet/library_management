<%-- 
    Document   : home.jsp
    Created on : Jun 8, 2025, 7:44:50 PM
    Author     : quang
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="library_management.dto.BookDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            .search-bar {
                margin-top: 20px;
            }
            .book-card {
                display: inline-block;
                width: 200px;
                margin: 15px;
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
            }

            .book-card img {
                width: 150px;
                height: 200px;
                object-fit: cover;
            }

            .details {
                display: none;
                text-align: left;
            }

            .show-button {
                margin-top: 10px;
                cursor: pointer;
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 5px 10px;
            }
        </style>
        <script>
            function toggleDetails(id) {
                var el = document.getElementById("details-" + id);
                if (window.getComputedStyle(el).display === "none") {
                    el.style.display = "block";
                } else {
                    el.style.display = "none";
                }
            }
        </script>
    </head>
    <body>
        <h1>Home</h1>
        <%
            String role = (String) session.getAttribute("role");
            if (role == null) {
                out.print("Login to use more functions!");
        %>
        <br>
        <jsp:include page="/guest_pages/dashboard.jsp"/>
        <%} else if ("admin".equals(role)) {
        %>
        <jsp:include page="/admin_pages/dashboard.jsp"/>
        <%
        } else if ("user".equals(role)) {
        %>
        <jsp:include page="/user_pages/dashboard.jsp"/>
        <%
            }
        %>
        <br>
        <form action="UserController" name='search' method="POST" class="search-bar">
            <input type="text" name="searchvalue" value="<%=request.getParameter("searchvalue") != null ? request.getParameter("searchvalue") : ""%>">
            <input type="hidden"  name="action" value="search">
            <input type="submit" value="Search">
        </form>

        <div class="book-container">
            <%
                ArrayList<BookDTO> bookList = (ArrayList<BookDTO>) request.getAttribute("booklist");
                if (bookList != null) {
                    for (BookDTO book : bookList) {
            %>
            <div class="book-card">
                <img src="images/<%= book.getImage()%>" alt="book image"/>
                <h4><%= book.getTitle()%></h4>
                <button class="show-button" onclick="toggleDetails(<%= book.getId()%>)">Chi tiết</button>
                <div id="details-<%= book.getId()%>" class="details">
                    <p><strong>Tác giả:</strong> <%= book.getAuthor()%></p>
                    <p><strong>ISBN:</strong> <%= book.getIsbn()%></p>
                    <p><strong>Thể loại:</strong> <%= book.getCategory()%></p>
                    <p><strong>Năm xuất bản:</strong> <%= book.getPublishedYear()%></p>
                    <p><strong>Số lượng trong thư viện:</strong> <%= book.getAvailableCopies()%>/<%= book.getTotalCopies()%></p>
                    <p><strong>Trạng thái:</strong> <%= book.getStatus()%></p>
                </div>
            </div>
            <%
                    }
                }
            %>
    </body>
</html>
