<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Request Page</title>
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
                background-color: #ffb6c1;
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

            .alert {
                padding: 15px;
                margin: 20px auto;
                border-radius: 8px;
                font-weight: bold;
                max-width: 800px;
                text-align: center;
                box-shadow: 0 0 10px rgba(0,0,0,0.05);
            }

            .alert.success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }

            .alert.error {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }

            .cancel-btn {
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

            .cancel-btn:hover {
                background-color: #ff1493;
            }
        </style>
    </head>
    <body>
        <h1>My Book Requests</h1>

        <jsp:include page="/user_pages/dashboard.jsp" />

        <div style="margin-top: 30px;"></div>

        <c:if test="${not empty result}">
            <div class="alert success">${result}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <script>
            setTimeout(() => {
                document.querySelectorAll('.alert').forEach(el => el.style.display = 'none');
            }, 4000);
        </script>

        <c:choose>
            <c:when test="${empty myrequests}">
                <p style="text-align: center; color: #666;">You have no requests.</p>
            </c:when>
            <c:otherwise>
                <div style="max-width: 1000px; margin: auto;">
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
                                                    <input type="submit" value="Cancel Request" class="cancel-btn" />
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
                </div>
            </c:otherwise>
        </c:choose>

    </body>
</html>
