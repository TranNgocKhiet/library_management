<%-- 
    Document   : dashboard.jsp
    Created on : Jun 8, 2025, 11:05:09 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            body > form {
                display: inline-block;
                margin-right: 15px;
                vertical-align: middle;
            }

            input[type="submit"] {
                background-color: grey;
                color: white;
                border: none;
                padding: 8px 12px;
                border-radius: 4px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
            form {
                margin: 0;
            }

            .signout-btn input {
                background-color: black;
            }
        </style>
    </head>
    <body>
        <button onclick="window.location.href = 'login.jsp'">Login</button>
        <button onclick="window.location.href = 'register.jsp'">Register</button>
    </body>
</html>
