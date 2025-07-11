<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Return Book Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f6f9;
                margin: 0;
                padding: 20px;
            }

            h1 {
                color: #d63384;
                text-align: center;
                margin-bottom: 30px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background: #fff;
                border-radius: 12px;
                overflow: hidden;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }

            th {
                background-color: #ff99cc;
                color: #800040;
                padding: 12px 10px;
                font-weight: bold;
                text-transform: uppercase;
                font-size: 14px;
                text-align: center;
            }

            td {
                padding: 12px 10px;
                border-top: 1px solid #eee;
                font-size: 14px;
                text-align: center;
                background-color: #fff;
            }

            tbody tr:hover {
                background-color: #fff0f5;
                transition: background-color 0.2s ease;
            }

            tr.overdue td {
                background-color: #ffe6e6 !important;
                color: #c00 !important;
                font-weight: bold;
            }

            .return-btn {
                background-color: #ff69b4;
                color: white;
                border: none;
                padding: 8px 14px;
                border-radius: 8px;
                font-size: 13px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .return-btn:hover {
                background-color: #ff1493;
            }

            .success-box, .error-box {
                padding: 15px;
                margin: 20px auto;
                border-radius: 8px;
                font-weight: bold;
                max-width: 800px;
                box-shadow: 0 0 10px rgba(0,0,0,0.05);
                text-align: center;
            }

            .success-box {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }

            .error-box {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
        </style>
    </head>
        <body>
        <h1>Books Not Yet Returned</h1>

        <jsp:include page="/user_pages/dashboard.jsp" />

        <div style="margin-top: 40px;"></div>

        <c:if test="${not empty result}">
            <div class="success-box">${result}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="error-box">${error}</div>
        </c:if>

        <div style="max-width: 1000px; margin: auto;">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Book Title</th>
                        <th>Borrow Date</th>
                        <th>Due Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty unreturnlist}">
                            <c:forEach var="record" items="${unreturnlist}">
                                <c:set var="rowClass" value="${record.status eq 'overdue' ? 'overdue' : ''}" />
                                <tr class="${rowClass}">
                                    <td>${record.id}</td>
                                    <td>${record.title}</td>
                                    <td>${record.borrowDate}</td>
                                    <td>${record.dueDate}</td>
                                    <td>${record.status}</td>
                                    <td>
                                        <form action="ReturnBookPageController" method="POST">
                                            <input type="hidden" name="action" value="sendreturnrequest" />
                                            <input type="hidden" name="bookid" value="${record.bookId}" />
                                            <button type="submit" class="return-btn">Return Book</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6">You have no unreturned books.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </body>
</html>
