<%-- 
    Document   : LoginController
    Created on : May 16, 2025, 9:30:55 AM
    Author     : Slayer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            .h3 {
                color: red;
            }
        </style>
    </head>
    <body>
    <c:if test="${not empty message}">
        <div class="alert alert-warning">
            ${message}
        </div>
    </c:if>

    <h1>Login</h1>
    <form action="./LoginController" method="GET">
        <input type="text" name="name" value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>">
        <input type="password" name="password" value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>">
        <input type="hidden" name="action" value="login">
        <input type="submit" value="Log in">
    </form>

    <div class="error-mes">
        <% String error = (String) request.getAttribute("error");
                if (error != null) {%>
        <h3> <%=error%> </h3>
        <%
            }
        %>
    </div>
</body>
</html>
