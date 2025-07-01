<%-- 
    Document   : borrowlist.jsp
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
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f8cdd4;
            color: #800040;
        }

        .message-box {
            padding: 10px;
            margin: 10px;
            background-color: pink;
            border: 1px solid #d63384;
            color: #800040;
            font-weight: bold;
        }

        .btn-submit {
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #ff6699;
            color: white;
            border: none;
            cursor: pointer;
        }

        .btn-submit:hover {
            background-color: #e05588;
        }
    </style>
</head>
<body>
    <h1>My Shelf</h1>

    <%@include file="/user_pages/dashboard.jsp" %>

    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null) { %>
        <div class="message-box">
            <%= message %><br>Redirecting to Home in 5 seconds...
        </div>
        <script>
            setTimeout(function () {
                window.location.href = "HomeController";
            }, 5000);
        </script>
    <% } %>

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
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                ArrayList<BookDTO> borrowList = (ArrayList<BookDTO>) session.getAttribute("borrowlist");
                if (borrowList != null) {
                    for (BookDTO book : borrowList) {
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
                        <input type="submit" value="Remove From Shelf" class="btn-submit">
                    </form>
                </td>
            </tr>
            <%
                    }
                }

                if (borrowList == null || borrowList.isEmpty()) {
            %>
            <tr>
                <td colspan="10">No books found.</td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <form action="UserController" method="POST">
        <input type="hidden" name="action" value="sendbookrequest">
        <input type="submit" value="Send book request" class="btn-submit">
    </form>
</body>
</html>
