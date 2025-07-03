<%-- 
    Document   : OverdueBookList
    Created on : Jun 17, 2025, 5:34:41 PM
    Author     : quang
--%>

<%@page import="library_management.dto.BorrowRecordDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overdue Book List</title>
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
            }</style>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            Overdue Book List
        </h1>
        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <c:set var="error" value="${requestScope.error}"/>
        <c:if test="${not empty error}">
            <div class="notification error">
                ${error}
            </div>
        </c:if>
        <c:set var="result" value="${requestScope.result}"/>
        <c:if test="${not empty result}">
            <div class="notification success">
                ${result}
            </div>
        </c:if>
        <div class="book-container">
            <c:set var="bList" value="${requestScope.overdueBookList}"/>
            <c:choose>
                <c:when test="${not empty bList}">
                    <c:forEach var="record" items="${bList}">
                        <div class="book-item">
                            <h4>Id: ${record.id}</h4>
                            <p>User Id: ${record.userId}</p>
                            <p>Book Id: ${record.bookId}</p>
                            <p>Borrow Date: ${record.borrowDate}</p>
                            <p>Due Date: ${record.dueDate}</p>
                            <p>Return Date: ${record.returnDate}</p>
                            <p>Status: ${record.status}</p>
                        </div><!-- book-item -->
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    ${"No Overdue Book Found!"}
                </c:otherwise>
            </c:choose>

        </div><!-- book-container -->

    </body>
</html>
