<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:if test="${not empty result}">
            <meta http-equiv="refresh" content="5;URL=login.jsp" />
        </c:if>
    </head>
    <body>
        <h1>Register</h1>
        <form action="RegisterController">
            <input type="text" name="name" value="${param.name != null ? param.name : ''}" />
            <input type="password" name="password" value="${param.password != null ? param.password : ''}" />
            <input type="text" name="email" value="${param.email != null ? param.email : ''}" />
            <input type="hidden" name="action" value="register" />
            <input type="submit" name="register" />
        </form>

        <c:if test="${not empty error}">
            <div style="color: red;">${error}</div>
        </c:if>

        <c:if test="${not empty result}">
            <div style="color: green;">${result}</div>
            <p>You will be redirected to login page in 5 seconds...</p>
        </c:if>
    </body>
</html>
