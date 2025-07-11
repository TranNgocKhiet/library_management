<%-- 
    Document   : menu
    Created on : May 19, 2025, 9:53:51 AM
    Author     : Slayer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f5f7fa;
                padding: 20px;
                margin: 0;
                text-align: center;
            }

            body > form {
                display: inline-block;
                margin: 10px;
            }

            input[type="submit"] {
                background: linear-gradient(to right, #4e54c8, #8f94fb);
                color: white;
                border: none;
                padding: 10px 18px;
                border-radius: 8px;
                cursor: pointer;
                font-weight: 600;
                font-size: 15px;
                transition: transform 0.2s, background-color 0.3s;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            input[type="submit"]:hover {
                background-color: #5cb85c; /* xanh lá hover */
                transform: scale(1.05);
            }

            .signout-btn input {
                background: #333;
                color: #fff;
            }

            .signout-btn input:hover {
                background-color: #000;
            }
        </style>
    </head>

    <body>
        <form action="UserController?action=viewuserhomepage" method="POST">                  
            <input type="submit" value="Home">
        </form>

        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Edit profile">
        </form>

        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="viewborrowlist">
            <input type="submit" value="My Shelf">
        </form>
        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="viewreturnpage">
            <input type="submit" value="Return Borrowed Books">
        </form>
        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="viewmyrequests">
            <input type="submit" value="My Borrow/Return Requests">
        </form>

        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="viewborrowrecords">
            <input type="submit" value="My Borrow/Return History">
        </form>

        <form action="UserController?action=logout" method="POST" class='signout-btn'>                  
            <input type="submit" value="Sign out">
        </form>

    </body>
</html>
