<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <%
            String usernameRaw = "";
            String passwordRaw = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("username".equals(c.getName())) {
                        usernameRaw = URLDecoder.decode(c.getValue(), "UTF-8");
                    }
                    if ("password".equals(c.getName())) {
                        passwordRaw = URLDecoder.decode(c.getValue(), "UTF-8");
                    }
                }
            }
            request.setAttribute("usernameRaw", usernameRaw);
            request.setAttribute("passwordRaw", passwordRaw);
        %>
        <c:set var="username" value="${not empty usernameRaw ? usernameRaw : param.name}"/>
        <c:set var="password" value="${not empty passwordRaw ? passwordRaw : param.password}"/>
        <h1>Login</h1>
        <form action="./LoginController" method="GET">
            Username: <input type="text" name="name" value="${username}" /><br>
            Password: <input type="password" name="password" value="${password}" /><br>
            <label><input type ="checkbox" name ="remember"/>Remember account</label><br>
            <input type="hidden" name="action" value="login">
            <input type="submit" value="Log in">
        </form>

        <c:if test="${not empty error}">
            <div class="error-mes">
                <h3>${error}</h3>
            </div>
        </c:if>
    </body>
</html>
