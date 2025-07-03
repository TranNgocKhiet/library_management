<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            .search-bar {
                margin-top: 20px;
            }
            .book-card {
                display: inline-block;
                width: 200px;
                margin: 15px;
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
            }
            .book-card img {
                width: 150px;
                height: 200px;
                object-fit: cover;
            }
            .details {
                display: none;
                text-align: left;
            }
            .show-button {
                margin-top: 10px;
                cursor: pointer;
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 5px 10px;
            }
            .notification.error {
                background: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }

        </style>
        <script>
            function toggleDetails(id) {
                var el = document.getElementById("details-" + id);
                if (window.getComputedStyle(el).display === "none") {
                    el.style.display = "block";
                } else {
                    el.style.display = "none";
                }
            }
        </script>
    </head>
    <body>
        <h1>Home</h1>

        <c:set var="role" value="${sessionScope.user.role}" />
        <c:choose>
            <c:when test="${empty role}">
                Login to use more functions!
                <br>
                <jsp:include page="/guest_pages/dashboard.jsp"/>
            </c:when>
            <c:when test="${role eq 'admin'}">
                <jsp:include page="/admin_pages/dashboard.jsp"/>
            </c:when>
            <c:when test="${role eq 'user'}">
                <jsp:include page="/user_pages/dashboard.jsp"/>
            </c:when>
        </c:choose>
        <c:set var="advertiseBookList" value="${requestScope.advertise}"/>
        <c:if test="${not empty advertiseBookList}">
            <h2 style="margin-top: 30px; color: #e91e63;">You may also be interested in</h2>
            <div class="book-container">
                <c:forEach var="book" items="${advertiseBookList}">
                    <c:if test="${book.status ne 'deleted'}">
                        <div class="book-card">
                            <img src="images/${book.image}" alt="book image"/>
                            <h4>${book.title}</h4>
                            <p><strong>Tác giả:</strong> ${book.author}</p>
                            <p><strong>ISBN:</strong> ${book.isbn}</p>
                            <p><strong>Thể loại:</strong> ${book.category}</p>
                            <p><strong>Năm xuất bản:</strong> ${book.publishedYear}</p>
                            <p><strong>Số lượng trong thư viện:</strong> ${book.availableCopies}/${book.totalCopies}</p>
                            <c:if test="${empty role or role ne 'admin'}">
                                <form action="UserController" method="POST">
                                    <input type="hidden" name="borrowid" value="${book.id}">
                                    <input type="hidden" name="action" value="savetoborrowlist">
                                    <input type="hidden" name="searchvalue" value="${param.searchvalue}">
                                    <input type="submit" value="Save To Shelf">
                                </form>
                            </c:if>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
        <br>
        <form action="SearchController" name='search' method="POST" class="search-bar">
            <input type="hidden" name="action" value="homeBookSearch">
            <input type="text" name="searchvalue" value="${param.searchvalue}">
            <input type="submit" value="Search">
        </form>
        <c:set var = "error" value="${requestScope.error}" />
        <c:if test="${not empty error}">
            <div class="notification error">
                ${error}
            </div>
        </c:if>
        <div class="book-container">
            <c:forEach var="book" items="${requestScope.booklist}">
                <c:if test="${book.status ne 'deleted'}">
                    <div class="book-card">
                        <img src="images/${book.image}" alt="book image"/>
                        <h4>${book.title}</h4>
                        <button class="show-button" onclick="toggleDetails(${book.id})">Chi tiết</button>
                        <div id="details-${book.id}" class="details">
                            <p><strong>Tác giả:</strong> ${book.author}</p>
                            <p><strong>ISBN:</strong> ${book.isbn}</p>
                            <p><strong>Thể loại:</strong> ${book.category}</p>
                            <p><strong>Năm xuất bản:</strong> ${book.publishedYear}</p>
                            <p><strong>Số lượng trong thư viện:</strong> ${book.availableCopies}/${book.totalCopies}</p>
                            <c:if test="${empty role or role ne 'admin'}">
                                <form action="UserController" method="POST">
                                    <input type="hidden" name="borrowid" value="${book.id}">
                                    <input type="hidden" name="action" value="savetoborrowlist">
                                    <input type="hidden" name="searchvalue" value="${param.searchvalue}">
                                    <input type="submit" value="Save To Shelf">
                                </form>
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </body>
</html>
