<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Borrow List Page</title>
    <style>
       body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }

        th {
            background-color: #f78fb3;
            color: #800040;
            padding: 12px 10px;
            text-transform: uppercase;
            font-weight: bold;
            font-size: 14px;
        }

        td {
            padding: 12px 10px;
            text-align: center;
            border-top: 1px solid #eee;
            font-size: 14px;
            background-color: #fff;
        }

        tbody tr:hover {
            background-color: #fff0f5;
            transition: background-color 0.2s ease;
        }

        .message-box {
            padding: 15px;
            margin: 20px auto;
            background-color: #ffc0cb;
            border: 1px solid #d63384;
            color: #800040;
            font-weight: bold;
            text-align: center;
            border-radius: 8px;
            max-width: 800px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .btn-submit {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ff6699;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-submit:hover {
            background-color: #e05588;
        }

        form {
            margin: 0;
        }

        form[action="UserController"] {
            display: inline;
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

    <div style="max-width: 1000px; margin: auto;">
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
    </div>

    <form action="UserController" method="POST">
        <input type="hidden" name="action" value="sendbookrequest">
        <input type="submit" value="Send book request" class="btn-submit">
    </form>
</body>
</html>
