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
            <title>JSP Page</title>
        </head>
        <body>
            <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
                Process Request
            </h1>
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

                    <form action="ProcessRequestController" method="POST">
                        <input type="hidden" name="id" value="<%= bRequest.getId()%>">
                        <input type="hidden" name="action" value="updateRequestStatus">
                        <label>Status: </label>

                        <select name="status" <%= !"pending".equals(bRequest.getStatus()) ? "disabled" : ""%>>
                            <option value="pending" <%= "pending".equals(bRequest.getStatus()) ? "selected" : ""%>>Pending</option>
                            <option value="rejected" <%= "rejected".equals(bRequest.getStatus()) ? "selected" : ""%>>Rejected</option>
                            <option value="approved" <%= "approved".equals(bRequest.getStatus()) ? "selected" : ""%>>Approved</option>
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
