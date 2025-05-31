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
            <input type="hidden" name="action" value="viewborrowrequests">
            <input type="submit" value="Borrow Requests">
        </form>
        
        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="viewborrowrecords">
            <input type="submit" value="Borrow History">
        </form>
                
        <form action="UserController?action=logout" method="POST" class='signout-btn'>                  
            <input type="submit" value="Sign out">
        </form>
    </body>
</html>
