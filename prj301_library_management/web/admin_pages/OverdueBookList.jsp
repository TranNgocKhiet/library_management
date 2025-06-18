<%-- 
    Document   : OverdueBookList
    Created on : Jun 17, 2025, 5:34:41 PM
    Author     : quang
--%>

<%@page import="library_management.dto.BorrowRecordDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overdue Book List</title>
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
            }</style>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            Overdue Book List
        </h1>
        <br>
        <br>
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
                ArrayList<BorrowRecordDTO> bList = (ArrayList<BorrowRecordDTO>) request.getAttribute("overdueBookList");

                if (bList == null || bList.isEmpty()) {
                    out.print("No Overdue Book Found!");
                } else {
                    for (BorrowRecordDTO record : bList) {
            %>
            <div class="book-item">
                <h4>Id: <%= record.getId()%></h4>
                <p>User Id: <%= record.getUserId()%></p>
                <p>Book Id: <%= record.getBookId()%></p>
                <p>Borrow Date: <%= record.getBorrowDate()%></p>
                <p>Due Date: <%= record.getDueDate()%></p>
                <p>Return Date: <%= record.getReturnDate()%></p>
                <p>Status: <%= record.getStatus()%></p>
            </div><!-- book-item -->
            <%
                    }
                }
            %>
        </div><!-- book-container -->

    </body>
</html>
