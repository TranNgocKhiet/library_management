<%-- 
    Document   : ProcessRequest
    Created on : Jun 15, 2025, 8:37:41 PM
    Author     : quang
--%>

<%@page import="library_management.dto.BookRequestDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Request</title>
        <style>  .notification {
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
            Process Request
        </h1>
        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>
        <div class="notification error">
            <%= error%>
        </div>
        <%
            }

            String result = (String) request.getAttribute("result");

            if (result != null) {
        %>
        <div class="notification success">
            <%= result%>
        </div>
        <%
            }
        %>
        <div class="book-container">
            <%
                ArrayList<BookRequestDTO> rList = (ArrayList<BookRequestDTO>) request.getAttribute("requestlist");

                if (rList == null) {
                    out.print("No Request Found!");
                } else {
                    for (BookRequestDTO bRequest : rList) {
            %>
            <div class="book-item">
                <h4>Id: <%= bRequest.getId()%></h4>
                <p>UserId: <%= bRequest.getUserId()%></p>
                <p>BookId: <%= bRequest.getBookId()%></p>
                <p>Request Date: <%= bRequest.getRequestDate()%></p>
                <p>Request Type : <%= bRequest.getRequestType()%></p>

                <form action="ProcessRequestController" method="POST">
                    <input type="hidden" name="id" value="<%= bRequest.getId()%>">
                    <input type="hidden" name="userId" value="<%= bRequest.getUserId()%>">
                    <input type="hidden" name="bookId" value="<%= bRequest.getBookId()%>">
                    <input type="hidden" name="requestType" value="<%= bRequest.getRequestType()%>">
                    <input type="hidden" name="action" value="requesthandle">
                    <label>Status: </label>

                    <select name="status" <%= !"pending".equals(bRequest.getStatus()) ? "disabled" : ""%>>
                        <option value="pending" <%= "pending".equals(bRequest.getStatus()) ? "selected" : ""%>>Pending</option>
                        <option value="rejected" <%= "rejected".equals(bRequest.getStatus()) ? "selected" : ""%>>Rejected</option>
                        <option value="approved" <%= "approved".equals(bRequest.getStatus()) ? "selected" : ""%>>Approved</option>
                        <option value="approved" <%= "cancel by user".equals(bRequest.getStatus()) ? "selected" : ""%>>Cancel By User</option>
                    </select>

                    <%
                        if ("pending".equals(bRequest.getStatus())) {
                    %>
                    <input type="submit" value="Submit">
                    <%
                        }
                    %>
                </form>
            </div><!-- book-item -->
            <%
                    }
                }
            %>
        </div><!-- book-container -->

    </body>
</html>
