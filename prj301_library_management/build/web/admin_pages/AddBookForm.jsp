<%-- 
    Document   : AddBookForm.jsp
    Created on : Jun 14, 2025, 9:30:54 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            ENTER NEW BOOK INFORMATION
        </h1>
        <form action="EditBookController" method="POST">
            <input type="hidden" name="action" value="addBookSubmit">
            Title(*) : <input type="text" name="title" value="${param.title}"><br>
            Author(*) : <input type="text" name="author" value="${param.author}"><br>
            ISBN(*) : <input type="text" name="isbn" value="${param.isbn}"><br>
            Category : <input type="text" name="category" value="${param.category}"><br>
            Published Year: <input type="number" name="published_year" value="${param.published_year}"><br>
            Total Copies: <input type="number" name="total_copies" value="${param.total_copies}"><br>
            Available Copies: <input type="number" name="available_copies" value="${param.available_copies}"><br>
            Image(if have): <input type="text" name="image" value="${param.image}"><br>
            <input type="submit" value="Add Book">
        </form>
        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>
        <p style="color:red;"><%= error%></p>
        <%
            }

            String result = (String) request.getAttribute("result");

            if (result != null) {
        %>
        <p style="color:green;"><%= result%></p>
        <%
            }
        %>


    </body>
</html>
