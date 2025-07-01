<%-- 
    Document   : editprofile
    Created on : May 16, 2025, 3:18:16 PM
    Author     : Slayer
--%>

<%@page import="library_management.utils.SessionUtils"%>
<%@page import="library_management.dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
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

        <%@include file="/user_pages/dashboard.jsp" %>

        <br>

        <%   UserDTO user = SessionUtils.getLoggedUser(session);
            if (user == null) {
                request.setAttribute("message", "You must be logged in to use this feature.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            String name = user.getName();
            String email = user.getEmail();
            String role = user.getRole();
            String status = user.getStatus();
        %>

        <form action="EditController" method="POST" class="edit-form">
            Name: <input type="text" name="name" value="<%=name%>">
            <br>
            Email: <input type="text" name="email" value="<%=email%>">
            <br>
            Role: <%=role%>
            <br>
            Status: <%=status%>
            <br>
            <input type="hidden" name="action" value="confirmedit">
            <input type="submit" value="Confirm Edit">
        </form>

        <% String msg = (String) request.getAttribute("msg");
            if (msg != null) {%>
        <h3> <%=msg%> </h3>
        <%
            }
        %>
    </body>
</html>
