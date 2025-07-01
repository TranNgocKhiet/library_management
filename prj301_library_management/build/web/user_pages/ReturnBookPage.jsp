<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="library_management.dto.BorrowRecordDTO"%>
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
        <%
            String result = (String) request.getAttribute("result");
            String error = (String) request.getAttribute("error");
        %>

        <% if (result != null) {%>
        <div style="padding: 12px; margin: 10px 0; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 5px;">
            <%= result%>
        </div>
        <% } %>

        <% if (error != null) {%>
        <div style="padding: 12px; margin: 10px 0; background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; border-radius: 5px;">
            <%= error%>
        </div>
        <% } %>

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
                <%
                    ArrayList<BorrowRecordDTO> list = (ArrayList<BorrowRecordDTO>) request.getAttribute("unreturnlist");
                    if (list != null && !list.isEmpty()) {
                        for (BorrowRecordDTO record : list) {
                            String rowClass = "normal";
                            if ("overdue".equalsIgnoreCase(record.getStatus())) {
                                rowClass = "overdue";
                            }
                %>
                <tr class="<%= rowClass%>">
                    <td><%= record.getId()%></td>
                    <td><%= record.getTitle()%></td>
                    <td><%= record.getBorrowDate()%></td>
                    <td><%= record.getDueDate()%></td>
                    <td><%= record.getStatus()%></td>
                    <td>
                        <form action="ReturnBookPageController" method="POST">
                            <input type="hidden" name="action" value="sendreturnrequest">
                            <input type="hidden" name="bookid" value="<%= record.getBookId()%>">
                            <button type="submit" class="return-btn">Return Book</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">You have no unreturned books.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
