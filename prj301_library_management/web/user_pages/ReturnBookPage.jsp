<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Unreturned Books</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: left;
            }

            tr.overdue {
                background-color: #fe2020;
            }

            tr.normal {
                background-color: white;
            }

            .return-btn {
                background-color: #ff69b4;
                color: white;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
            }

            .return-btn:hover {
                background-color: #ff1493;
            }

            h1 {
                color: #d63384;
            }
        </style>
    </head>
    <body>

        <h1>Books Not Yet Returned</h1>
        <jsp:include page="/user_pages/dashboard.jsp" />
     <div style="margin-top: 30px;"></div>
        <c:if test="${not empty result}">
            <div style="padding: 12px; margin: 10px 0; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 5px;">
                ${result}
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div style="padding: 12px; margin: 10px 0; background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; border-radius: 5px;">
                ${error}
            </div>
        </c:if>

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
                            <c:set var="rowClass" value="${record.status eq 'overdue' ? 'overdue' : 'normal'}" />
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

    </body>
</html>
