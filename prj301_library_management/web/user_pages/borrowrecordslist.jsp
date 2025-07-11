<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BorrowRecordDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Borrow Record List Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: #f4f6f9;
                margin: 0;
                padding: 20px;
            }

            h2 {
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
            }

            th {
                background-color: #1890ff;
                color: white;
                padding: 12px 10px;
                font-weight: 600;
                text-transform: uppercase;
            }

            td {
                padding: 12px 10px;
                border-top: 1px solid #eee;
                background-color: #fff;
            }

            tbody tr:hover {
                background-color: #f1f5ff;
                transition: background-color 0.2s ease;
            }

            .overdue-row {
                background-color: #ffe6e6 !important;
                color: #c00 !important;
                font-weight: bold;
            }

            .warning-banner {
                background-color: #ff4d4f;
                color: white;
                padding: 15px;
                margin: 30px auto;
                font-size: 18px;
                text-align: center;
                border-radius: 8px;
                max-width: 800px;
                box-shadow: 0 0 12px rgba(0,0,0,0.2);
            }

            .dashboard-space {
                margin-top: 40px;
            }

        </style>
    </head>
    <body>
        
        <h2>Borrowing History</h2>

        <jsp:include page="/user_pages/dashboard.jsp" />
        <div class="dashboard-space"></div>
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
        
        <div style="max-width: 1000px; margin: auto;">
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
        </div>
    </body>
</html>
