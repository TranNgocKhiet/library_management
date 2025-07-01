<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BookRequestDTO"%>
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
        <% String result = (String) request.getAttribute("result"); %>
        <% String error = (String) request.getAttribute("error"); %>

        <% if (result != null) {%>
        <div style="padding: 10px; margin: 10px; background-color: #d4edda; border: 1px solid #28a745; color: #155724;">
            <strong> <%= result%></strong>
        </div>
        <% } %>

        <% if (error != null) {%>
        <div style="padding: 10px; margin: 10px; background-color: #f8d7da; border: 1px solid #dc3545; color: #721c24;">
            <strong> <%= error%></strong>
        </div>
        <% } %>
        <script>
            setTimeout(() => {
                const alerts = document.querySelectorAll('div[style*="padding"]');
                alerts.forEach(el => el.style.display = 'none');
            }, 4000);
        </script>

        <%
            ArrayList<BookRequestDTO> list = (ArrayList<BookRequestDTO>) request.getAttribute("myrequests");
            if (list == null || list.isEmpty()) {
        %>
        <p>You have no requests.</p>
        <%
        } else {
        %>
        <table>
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Book Title</th>
                    <th>Request Type</th>
                    <th>Request Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (BookRequestDTO req : list) {
                %>
                <tr>
                    <td><%= req.getId()%></td>
                    <td><%= req.getTitle()%></td>
                    <td><%= req.getRequestType()%></td>
                    <td><%= req.getRequestDate()%></td>
                    <td><%= req.getStatus()%></td>
                    <td>
                        <% if ("pending".equalsIgnoreCase(req.getStatus())) {%>
                        <form action="MyRequestController" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="cancelrequest">
                            <input type="hidden" name="requestId" value="<%= req.getId()%>">
                            <input type="submit" value="Cancel Request">
                        </form>
                        <% } else { %>
                        -
                        <% } %>
                    </td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>
        <%
            }
        %>
    </body>
</html>
