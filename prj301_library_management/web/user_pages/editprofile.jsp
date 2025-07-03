<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Edit Profile Page</title>
        <style>
            .edit-form {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Edit Profile</h1>

        <jsp:include page="/user_pages/dashboard.jsp" />

        <br>

        <form action="EditController" method="POST" class="edit-form">
            Name: <input type="text" name="name" value="${sessionScope.user.name}">
            <br>
            Email: <input type="text" name="email" value="${sessionScope.user.email}">
            <br>
            Role: ${sessionScope.user.role}
            <br>
            Status: ${sessionScope.user.status}
            <br>
            <input type="hidden" name="action" value="confirmedit">
            <input type="submit" value="Confirm Edit">
        </form>

        <c:if test="${not empty msg}">
            <h3>${msg}</h3>
        </c:if>
    </body>
</html>
