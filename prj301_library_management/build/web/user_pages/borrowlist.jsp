<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Borrow Shelf Page</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f8cdd4;
            color: #800040;
        }

        .message-box {
            padding: 10px;
            margin: 10px;
            background-color: pink;
            border: 1px solid #d63384;
            color: #800040;
            font-weight: bold;
        }

        .btn-submit {
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #ff6699;
            color: white;
            border: none;
            cursor: pointer;
        }

        .btn-submit:hover {
            background-color: #e05588;
        }
    </style>
</head>
<body>
    <h1>My Shelf</h1>

    <jsp:include page="/user_pages/dashboard.jsp" />

    <c:if test="${not empty message}">
        <div class="message-box">
            ${message}<br>Redirecting to Home in 5 seconds...
        </div>
        <script>
            setTimeout(function () {
                window.location.href = "HomeController";
            }, 5000);
        </script>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Isbn</th>
                <th>Category</th>
                <th>Published Year</th>
                <th>Total Copies</th>
                <th>Available Copies</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty sessionScope.borrowlist}">
                    <c:forEach var="book" items="${sessionScope.borrowlist}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.isbn}</td>
                            <td>${book.category}</td>
                            <td>${book.publishedYear}</td>
                            <td>${book.totalCopies}</td>
                            <td>${book.availableCopies}</td>
                            <td>${book.status}</td>
                            <td>
                                <form action="UserController" method="POST">
                                    <input type="hidden" name="deleteid" value="${book.id}" />
                                    <input type="hidden" name="action" value="removefromborrowlist" />
                                    <input type="submit" value="Remove From Shelf" class="btn-submit" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="10">No books found.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <form action="UserController" method="POST">
        <input type="hidden" name="action" value="sendbookrequest">
        <input type="submit" value="Send book request" class="btn-submit">
    </form>
</body>
</html>
