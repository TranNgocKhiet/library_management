<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BorrowRecordDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Borrowing History</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }
            .overdue-row {
                background-color: #ffcccc;
                color: #990000;
                font-weight: bold;
            }
            .warning-banner {
                background-color: #ff4444;
                color: white;
                padding: 15px;
                margin: 15px 0;
                font-size: 18px;
                text-align: center;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
            }
        </style>
    </head>
    <body>

        <h2>Borrowing History</h2>

        <jsp:include page="/user_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <c:set var="hasOverdue" value="false" />
        <c:forEach var="record" items="${borrowrecordslist}">
            <c:if test="${record.returnDate == null && record.status == 'overdue'}">
                <c:set var="hasOverdue" value="true" />
            </c:if>
        </c:forEach>

        <c:if test="${hasOverdue}">
            <div class="warning-banner">
                ⚠️ You have a book that is overdue,
                please come to the library to return it immediately
                to avoid heavy fines!
            </div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>Book Title</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${borrowrecordslist}">
                    <tr class="<c:if test='${record.returnDate == null && record.status == "overdue"}'>overdue-row</c:if>">
                        <td>${record.title}</td>
                        <td><fmt:formatDate value="${record.borrowDate}" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${record.dueDate}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${record.returnDate != null}">
                                    <fmt:formatDate value="${record.returnDate}" pattern="yyyy-MM-dd"/>
                                </c:when>
                                <c:otherwise>
                                    Not Returned
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${record.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
