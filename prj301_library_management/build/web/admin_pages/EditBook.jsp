<%-- 
    Document   : editBook
    Created on : Jun 14, 2025, 9:09:07 PM
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
            Edit Book
        </h1>

        <form action="EditBookController" method="POST">
            <input type="hidden" name="action" value="showAddForm">
            <input type="submit" value="Add new book">
        </form>
        <br>
        <form action="EditBookController" method="POST">
            <input type="hidden" name="action" value="showEditForm">
            <input type="submit" value="Edit book">
        </form>
        <br>
    </body>
</html>
