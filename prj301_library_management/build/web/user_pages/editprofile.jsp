<%-- 
    Document   : editprofile
    Created on : May 16, 2025, 3:18:16 PM
    Author     : Slayer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <%  int userid = (int) session.getAttribute("id");
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            String role = (String) session.getAttribute("role");
            String status = (String) session.getAttribute("status");
        %>
        
        <form action="EditController" method="POST" class="edit-form">
            Id: <%=userid%>
            <br>
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
            if (msg != null) { %>
            <h3> <%=msg%> </h3>
        <%
            }
        %>
</body>
</html>
