<%-- 
    Document   : ManageAccountController
    Created on : Jun 17, 2025, 11:47:09 AM
    Author     : quang
--%>

<%@page import="library_management.dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
        <style>     .search-bar {
                margin-top: 20px;
            }
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
            Manage Account
        </h1>
        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <form action="SearchController" name='search' method="POST" class="search-bar">
            <input type="hidden" name="action" value="adminAccountSearch">
            <input type="text" name="searchvalue" value="${param.searchvalue}">
            <input type="submit" value="Search">
        </form>
        <br>
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
            <c:set var="usList" value="${requestScope.usList}"/>
            <c:choose>
                <c:when test="${not empty usList}">
                    <c:forEach var="us" items="${usList}">
                        <div class="book-item">
                            <h4>Id: ${us.id}</h4>
                            <p>Name: ${us.name}</p>
                            <p>Email: ${us.email}</p>
                            <p>Role: ${us.role}</p>
                            <form action="ManageAccountController" method="POST">
                                <input type="hidden" name="id" value="${us.id}">
                                <input type="hidden" name="action" value="statushandle">
                                <input type="hidden" name="status" value="${us.status}">
                                <label>Status: </label>
                                <select name="statusinput" <c:if test="${'admin' == us.role}">disabled</c:if>>
                                    <option value="active" <c:if test="${'active' == us.status}">selected</c:if>>Active</option>
                                    <option value="blocked" <c:if test="${'blocked' == us.status}">selected</c:if>>Blocked</option>
                                    </select>
                                <c:if test="${'admin' != us.role}"> <input type="submit" value="Submit"></c:if>
                                </form>
                            </div><!-- book-item -->
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    ${"No User Found!"}
                </c:otherwise>
            </c:choose>

        </div><!-- book-container -->

    </body>
</html>
