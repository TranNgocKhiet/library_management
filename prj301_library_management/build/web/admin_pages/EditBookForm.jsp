<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="library_management.dto.BookDTO"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Edit Book Form</title>
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

            .search-bar {
                margin-top: 20px;
            }
            .book-card {
                display: inline-block;
                width: 220px;
                margin: 15px;
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
                position: relative;
            }

            .book-card img {
                width: 150px;
                height: 200px;
                object-fit: cover;
            }

            .details {
                display: none;
                text-align: left;
                margin-top: 10px;
                padding: 10px;
                border: 1px solid #4CAF50;
                background: #e8f5e9;
            }

            .show-button {
                margin-top: 10px;
                cursor: pointer;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                padding: 5px 10px;
            }
        </style>
        <script>
            function toggleDetails(id) {
                var el = document.getElementById("details-" + id);
                if (el.style.display === '' ||
                        el.style.display === 'none') {
                    el.style.display = 'block';
                } else {
                    el.style.display = 'none';
                }
            }

        </script>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 32px;margin-bottom: 20px">
            Edit Book
        </h1>

        <br>
        <form action="SearchController" name='search' method='POST' class="search-bar">
            <input type="text" name="searchvalue" value="${param.searchvalue}">
            <input type="hidden"  name="action" value="editBoookSearch">
            <input type="submit" value="Search">
        </form>
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
                ArrayList<BookDTO> bookList = (ArrayList<BookDTO>) request.getAttribute("booklist");
                if (bookList == null) {
                    out.print("No Book Found !");
                } else {
                    for (BookDTO book : bookList) {
            %>
            <div class="book-card">
                <img src='images/<%= book.getImage()%>' alt='Book Image' /><br>
                <h4><%= book.getTitle()%></h4>
                <button class='show-button' onclick='toggleDetails(<%= book.getId()%>)'>Edit</button>

                <div id='details-<%= book.getId()%>' class='details'>
                    <p><strong>Tác giả:</strong> <%= book.getAuthor()%></p>
                    <p><strong>ISBN:</strong> <%= book.getIsbn()%></p>

                    <form action='EditBookController' method='POST'>
                        <input type='hidden' name='id' value='<%= book.getId()%>'>
                        <input type='hidden' name='action' value='editBookSubmit'>
                        Category: <input type='text' name='category' value='<%= book.getCategory()%>'><br>
                        Published Year: <input type='number' name='published_year' value='<%= book.getPublishedYear()%>'><br>
                        Total Copies: <input type='number' name='total_copies' value='<%= book.getTotalCopies()%>'><br>
                        Available Copies: <input type='number' name='available_copies' value='<%= book.getAvailableCopies()%>'><br>
                        Image (if have): <input type='text' name='image' value='<%= book.getImage()%>'><br>
                        Status:
                        Status:
                        <select name='status'>
                            <option value='active' <%= "active".equals(book.getStatus()) ? "selected" : ""%>>Active</option>
                            <option value='deleted' <%= "deleted".equals(book.getStatus()) ? "selected" : ""%>>Soft Remove</option>
                        </select><br>
                        <input type='submit' value='Save'>
                    </form>



                </div>
            </div>
            <%
                    }
                }
            %>
        </div><!-- book-container -->
    </body>
</html>
