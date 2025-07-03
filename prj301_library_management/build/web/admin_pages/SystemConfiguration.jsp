<%-- 
    Document   : SystemConfiguration
    Created on : Jun 18, 2025, 8:56:18 PM
    Author     : Slayer
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>System Configuration Page</title>
    </head>
    <body>
        <h1>System Configuration</h1>
        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Configuration Key</th>
                    <th>Value</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="config" items="${configlist}">
                    <tr>
                        <td>${config.id}</td>
                        <td>${config.configKey}</td>
                        <td>
                            <form action="AdminController" method="POST" style="display: inline;">
                                <input type="hidden" name="id" value="${config.id}" />
                                <input type="hidden" name="action" value="updateconfig" />
                                <input type="text" name="configValue" value="${config.configValue}" style="width: 150px;" />
                        </td>
                        <td>
                            <input type="text" name="description" value="${config.description}" style="width: 300px;" />
                        </td>
                        <td>
                            <input type="submit" value="Save" />
                            </form>

                            <form action="AdminController" method="post" style="display: inline;" onsubmit="return confirm('Delete this config?');">
                                <input type="hidden" name="id" value="${config.id}" />
                                <input type="hidden" name="action" value="deleteconfig" />
                                <input type="submit" value="Delete" style="background-color: red; color: white;" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                <tr>
            <form action="AdminController" method="POST">
                <td>New</td>
                <td><input type="text" name="configKey" required /></td>
                <td><input type="text" name="configValue" required /></td>
                <td><input type="text" name="description" style="width: 300px;" /></td>
                <td>
                    <input type="hidden" name="action" value="addconfig" />
                    <input type="submit" value="Add" />
                </td>
            </form>
        </tr>
    </tbody>
</table>
</body>
</html>
