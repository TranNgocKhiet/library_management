<%-- 
    Document   : ManageAccountController
    Created on : Jun 17, 2025, 11:47:09 AM
    Author     : quang
--%>

<%@page import="library_management.dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
        <style>     .search-bar {
                margin-top: 20px;
            }
            .notification {
                padding: 10px 20px;
                border-radius: 5px;
                margin-bottom: 20px;
                font-size: 0.95em;
                box-shadow: 0 2px 5px rgb(0 0 0 / 0.2);
                position: relative;
                max-width: 300px;
                word-wrap: break-word;
            }

            .notification.error {
                background: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }

            .notification.success {
                background: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }</style>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            Manage Account
        </h1>
        <br>
           <form action="SearchController" name='search' method="POST" class="search-bar">
            <input type="hidden" name="action" value="adminAccountSearch">
            <input type="text" name="searchvalue" value="${param.searchvalue}">
            <input type="submit" value="Search">
        </form>
            <br>
        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>
        <div class="notification error">
            <%= error%>
        </div>
        <%
            }

            String result = (String) request.getAttribute("result");

            if (result != null) {
        %>
        <div class="notification success">
            <%= result%>
        </div>
        <%
            }
        %>
        <div class="book-container">
            <%
                ArrayList<UserDTO> usList = (ArrayList<UserDTO>) request.getAttribute("usList");

                if (usList == null || usList.isEmpty()) {
                    out.print("No User Found!");
                } else {
                    for (UserDTO us : usList) {
            %>
            <div class="book-item">
                <h4>Id: <%= us.getId()%></h4>
                <p>Name: <%= us.getName()%></p>
                <p>Email: <%= us.getEmail()%></p>
                <p>Role: <%= us.getRole()%></p>
                <form action="ManageAccountController" method="POST">
                    <input type="hidden" name="id" value="<%= us.getId()%>">
                    <input type="hidden" name="action" value="statushandle">
                    <input type="hidden" name="status" value="<%= us.getStatus()%>">
                    <label>Status: </label>
                    <select name="statusinput" <%= "admin".equals(us.getRole()) ? "disabled" : ""%>>
                        <option value="active" <%= "active".equals(us.getStatus()) ? "selected" : ""%>>active</option>
                        <option value="blocked" <%= "blocked".equals(us.getStatus()) ? "selected" : ""%>>blocked</option>
                    </select>

                    <%
                        if (!"admin".equals(us.getRole())) {
                    %>
                    <input type="submit" value="Submit">
                    <%
                        }
                    %>
                </form>
            </div><!-- book-item -->
            <%
                    }
                }
            %>
        </div><!-- book-container -->

    </body>
</html>
