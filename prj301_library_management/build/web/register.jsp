<%-- 
    Document   : register.jsp
    Created on : Jun 8, 2025, 11:24:25 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <style>
            .h3 {
                color: red;
            }
        </style>
        <%
            String result = (String) request.getAttribute("result");
            if (result != null) {
        %>
        <meta http-equiv="refresh" content="5;URL=login.jsp">
        <%
            }
        %>

    </head>
    <body>
        <h1>Register</h1>
        <form action="RegisterController">
            <input type="text" name="name" value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>">
            <input type="password" name="password" value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>">
            <input type="text" name="email" value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>">
            <input type="hidden" name="action" value = "register">
            <input type="submit" name="register">
        </form>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) {%>
        <div style="color: red;"><%= error%></div>
        <% } %>
        <% if (result != null) {%>
        <div style="color: green;"><%= result%></div>
        <p>You will be redirected to login page in 5 seconds...</p>
        <% }%>

    </body>
</html>
