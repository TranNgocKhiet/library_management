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
    </head>
    <body>
        <a href="MainController?action=viewhome">Home</a>
                
        <form action="MainController?action=logout" method="POST">                  
            <button class='signout-button' type="submit">Sign Out</button>
        </form>
                
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Edit profile">
        </form>
                        
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="viewborrowlist">
            <input type="submit" value="View borrow list">
        </form>
        
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="viewborrowrequests">
            <input type="submit" value="View borrow requests">
        </form>
        
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="viewborrowrecords">
            <input type="submit" value="View borrow records">
        </form>
    </body>
</html>
