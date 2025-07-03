<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Edit Book Form</title>
        <style>
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
            }

            .search-bar {
                margin-top: 20px;
            }
            .book-card {
                display: inline-block;
                width: 220px;
                margin: 15px;
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
                position: relative;
            }

            .book-card img {
                width: 150px;
                height: 200px;
                object-fit: cover;
            }

            .details {
                display: none;
                text-align: left;
                margin-top: 10px;
                padding: 10px;
                border: 1px solid #4CAF50;
                background: #e8f5e9;
            }

            .show-button {
                margin-top: 10px;
                cursor: pointer;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                padding: 5px 10px;
            }
        </style>
        <script>
            function toggleDetails(id) {
                var el = document.getElementById("details-" + id);
                if (el.style.display === '' ||
                        el.style.display === 'none') {
                    el.style.display = 'block';
                } else {
                    el.style.display = 'none';
                }
            }

        </script>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            Edit Book
        </h1>

        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <form action="SearchController" name='search' method='POST' class="search-bar">
            <input type="text" name="searchvalue" value="${param.searchvalue}">
            <input type="hidden"  name="action" value="editBoookSearch">
            <input type="submit" value="Search">
        </form>
        <c:set var = "error" value="${requestScope.error}" />
        <c:if test="${not empty error}">
            <div class="notification error">
                ${error}
            </div>
        </c:if>
        <c:set var = "result" value="${requestScope.result}" />
        <c:if test="${not empty result}">
            <div class="notification success">
                ${result}
            </div>
        </c:if>

        <div class="book-container">
            <c:set var="bookList" value="${requestScope.booklist}"/>
            <c:choose>
                <c:when test="${not empty bookList}">
                    <c:forEach var = "book" items="${bookList}">
                        <div class="book-card">
                            <img src='images/${book.image}' alt='Book Image' /><br>
                            <h4>${book.title}</h4>
                            <button class='show-button' onclick='toggleDetails(${book.id})'>Edit</button>

                            <div id='details-${book.id}' class='details'>
                                <p><strong>Author:</strong> ${book.author}</p>
                                <p><strong>ISBN:</strong> ${book.isbn}</p>

                                <form action='EditBookController' method='POST'>
                                    <input type='hidden' name='id' value='${book.id}'>
                                    <input type='hidden' name='action' value='editBookSubmit'>
                                    Category: <input type='text' name='category' value='${book.category}'><br>
                                    Published Year: <input type='number' name='published_year' value='${book.publishedYear}'><br>
                                    Total Copies: <input type='number' name='total_copies' value='${book.totalCopies}'><br>
                                    Available Copies: <input type='number' name='available_copies' value='${book.availableCopies}'><br>
                                    Image (if have): <input type='text' name='image' value='${book.image}'><br>
                                    Status:
                                    Status:
                                    <select name="status">
                                        <option value="active" <c:if test="${book.status == 'active'}">selected</c:if>>Active</option>
                                        <option value="deleted" <c:if test="${book.status == 'deleted'}">selected</c:if>>Soft Remove</option>
                                        </select>
                                        <br>
                                        <input type='submit' value='Save'>
                                    </form>



                                </div>
                            </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    ${"No Book Found !"}
                </c:otherwise>
            </c:choose>

        </div><!-- book-container -->
    </body>
</html>
