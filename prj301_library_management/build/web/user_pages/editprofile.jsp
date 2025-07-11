<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Edit Profile Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: #f4f6f9;
                margin: 0;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }

            .edit-form {
                background: #fff;
                max-width: 500px;
                min-width: 320px;
                margin: 50px auto 0 auto; 
                padding: 30px 40px;
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }   

            .edit-form input[type="text"] {
                width: 100%;
                padding: 10px 12px;
                margin: 10px 0 20px 0;
                border: 1px solid #ccc;
                border-radius: 8px;
                font-size: 16px;
                box-sizing: border-box;
            }

            .edit-form input[type="password"] {
                width: 100%;
                padding: 10px 12px;
                margin: 10px 0 20px 0;
                border: 1px solid #ccc;
                border-radius: 8px;
                font-size: 16px;
                box-sizing: border-box;
            }

            .edit-form input[type="submit"] {
                width: 100%;
                background-color: #1890ff;
                color: white;
                padding: 12px;
                font-size: 16px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .edit-form input[type="submit"]:hover {
                background-color: #40a9ff;
            }

            .edit-form br {
                display: none; /* Ẩn line-breaks thô */
            }

            .edit-form div {
                margin-bottom: 20px;
            }

            .edit-form label {
                display: block;
                margin: 0 0 8px 0;
                font-weight: bold;
                color: #444;
                text-align: left;
            }

            h3 {
                color: green;
                text-align: center;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Edit Profile</h1>

        <jsp:include page="/user_pages/dashboard.jsp" />

        <br>

        <form action="EditController" method="POST" class="edit-form">
            <div>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${sessionScope.user.name}">
            </div>

            <div>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" value="${sessionScope.user.email}">
            </div>
            <div>
                <label for="password">New Password</label>
                <input type="password" id="password" name="password">
            </div>

            <div>
                <label for="confirmPassword">Confirm New Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword">
            </div>

            <div style="text-align: left;">
                <input type="checkbox" id="togglePassword" onclick="togglePasswordVisibility()">
                <label for="togglePassword" style="display: inline; font-weight: normal;">Show Passwords</label>
            </div>

            <input type="hidden" name="action" value="confirmedit">
            <input type="submit" value="Confirm Edit">
        </form>


        <c:if test="${not empty msg}">
            <h3>${msg}</h3>
        </c:if>
            
        <script>
            function togglePasswordVisibility() {
                const password = document.getElementById("password");
                const confirmPassword = document.getElementById("confirmPassword");
                const type = password.type === "password" ? "text" : "password";
                password.type = type;
                confirmPassword.type = type;
            }
        </script>
    </body>
</html>
