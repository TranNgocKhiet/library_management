<%-- 
    Document   : ProcessRequest
    Created on : Jun 15, 2025, 8:37:41 PM
    Author     : quang
--%>

<%@page import="library_management.dto.BookRequestDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Request</title>
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
        </style>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            Process Request
        </h1>
        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <c:set var="error" value="${requestScope.error}" />
        <c:if test="${not empty error}">
            <div class="notification error">
                ${error}
            </div>
        </c:if>

        <c:set var="result" value="${requestScope.result}" />
        <c:if test="${not empty result}">
            <div class="notification success">
                ${result}
            </div>
        </c:if>

        <div class="book-container">
            <c:set var="rList" value="${requestScope.requestlist}" />
            <c:choose>
                <c:when test="${empty rList}">
                    No Request Found!
                </c:when>
                <c:otherwise>
                    <c:forEach var="bRequest" items="${rList}">
                        <div class="book-item">
                            <h4>Id: ${bRequest.id}</h4>
                            <p>UserId: ${bRequest.userId}</p>
                            <p>BookId: ${bRequest.bookId}</p>
                            <p>Request Date: ${bRequest.requestDate}</p>
                            <p>Request Type : ${bRequest.requestType}</p>

                            <form action="ProcessRequestController" method="POST">
                                <input type="hidden" name="id" value="${bRequest.id}">
                                <input type="hidden" name="userId" value="${bRequest.userId}">
                                <input type="hidden" name="bookId" value="${bRequest.bookId}">
                                <input type="hidden" name="requestType" value="${bRequest.requestType}">
                                <input type="hidden" name="action" value="requesthandle">

                                <label>Status: </label>
                                <select name="status" 
                                        <c:if test="${bRequest.status ne 'pending'}">disabled</c:if>>

                                            <option value="pending" 
                                            <c:if test="${bRequest.status eq 'pending'}">selected</c:if>>
                                                Pending
                                            </option>
                                            <option value="rejected" 
                                            <c:if test="${bRequest.status eq 'rejected'}">selected</c:if>>
                                                Rejected
                                            </option>
                                            <option value="approved" 
                                            <c:if test="${bRequest.status eq 'approved'}">selected</c:if>>
                                                Approved
                                            </option>
                                        <c:if test="${bRequest.status eq 'cancel by user'}">
                                            <option value="cancelbyuser" selected>Cancel By User</option>
                                        </c:if>
                                </select>

                                <c:if test="${bRequest.status eq 'pending'}">
                                    <input type="submit" value="Submit">
                                </c:if>
                            </form>
                        </div><!-- book-item -->
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div><!-- book-container -->

    </body>
</html>
