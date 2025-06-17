<%-- 
    Document   : StatisticsPage
    Created on : Jun 17, 2025, 6:54:26 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics Page</title>
    </head>
    <body>
        <h1>Library Statistics</h1>

        <h2>General</h2>
        <ul>
            <li>Total number of books: ${totalBooks}</li>
            <li>Total number of users: ${totalUsers}</li>
            <li>Currently borrowed books: ${currentlyBorrowed}</li>
            <li>Average borrowing duration (days): ${averageDuration}</li>
        </ul>

        <h2>Most Borrowed Books</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>ISBN</th>
                    <th>Borrow Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${mostBorrowed}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.isbn}</td>
                        <td>${book.borrowCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Monthly Borrowing Statistics</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Month</th>
                    <th>Borrow Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stat" items="${borrowingStats}">
                    <tr>
                        <td>${stat.year}</td>
                        <td>${stat.month}</td>
                        <td>${stat.total}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
