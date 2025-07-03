<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Requests</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                padding: 8px 12px;
                border: 1px solid #aaa;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h1>My Book Requests</h1>
          <jsp:include page="/user_pages/dashboard.jsp" />
     <div style="margin-top: 30px;"></div>
        <c:if test="${not empty result}">
            <div style="padding: 10px; margin: 10px; background-color: #d4edda; border: 1px solid #28a745; color: #155724;">
                <strong>${result}</strong>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div style="padding: 10px; margin: 10px; background-color: #f8d7da; border: 1px solid #dc3545; color: #721c24;">
                <strong>${error}</strong>
            </div>
        </c:if>

        <script>
            setTimeout(() => {
                const alerts = document.querySelectorAll('div[style*="padding"]');
                alerts.forEach(el => el.style.display = 'none');
            }, 4000);
        </script>

        <c:choose>
            <c:when test="${empty myrequests}">
                <p>You have no requests.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Request ID</th>
                            <th>Book Title</th>
                            <th>Request Type</th>
                            <th>Request Date</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="req" items="${myrequests}">
                            <tr>
                                <td>${req.id}</td>
                                <td>${req.title}</td>
                                <td>${req.requestType}</td>
                                <td>${req.requestDate}</td>
                                <td>${req.status}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${req.status eq 'pending'}">
                                            <form action="MyRequestController" method="POST" style="display:inline;">
                                                <input type="hidden" name="action" value="cancelrequest" />
                                                <input type="hidden" name="requestId" value="${req.id}" />
                                                <input type="submit" value="Cancel Request" />
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>
