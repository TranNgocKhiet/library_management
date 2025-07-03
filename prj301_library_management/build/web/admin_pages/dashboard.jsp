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
        <form action="AdminController?action=viewadminhomepage" method="POST">                  
            <input type="submit" value="Home">
        </form>
        <form action="AdminController?action=editBook" method="POST">                  
            <input type="submit" value="Edit Books">
        </form>
        <form action="AdminController?action=processrequest" method="POST">                  
            <input type="submit" value="Process Requests">
        </form>   
        <form action="AdminController?action=handledrequest" method="POST">                  
            <input type="submit" value="CheckIn/Out Book">
        </form>
        <form action="AdminController?action=manageaccount" method="POST">                  
            <input type="submit" value="Manage Account">
        </form>
        <form action="AdminController?action=viewbookoverdue" method="POST">                  
            <input type="submit" value="Overdue Book List">
        </form>
        <form action="AdminController?action=finecontrol" method="POST">                  
            <input type="submit" value="Manage Fine">
        </form>
        <form action="AdminController?action=viewstatistics" method="POST">                  
            <input type="submit" value="Statistics Page">
        </form>
        <form action="AdminController?action=viewlistconfig" method="POST">                  
            <input type="submit" value="System Configuration">
        </form>
        <form action="UserController?action=logout" method="POST" class='signout-btn'>                  
            <input type="submit" value="Sign out">
        </form>

    </body>
</html>
