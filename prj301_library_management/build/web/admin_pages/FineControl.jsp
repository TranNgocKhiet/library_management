<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Fine Control</title>
        <style>
            table {
                border-collapse: collapse;
                width: 80%;
                margin: auto;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }
            th {
                background-color: #f0f0f0;
            }
            .paid {
                color: green;
            }
            .unpaid {
                color: red;
            }
        </style>
    </head>
    <body>
        <h2 style="text-align:center;">Fine Control Page</h2>

        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <c:if test="${not empty result}">
            <script>
                alert("${result}");
            </script>
        </c:if>


        <c:if test="${not empty error}">
            <script>
                alert("${error}");
            </script>
        </c:if>
        <c:if test="${empty fList}">
            <p style="text-align:center;">No fine records found.</p>
        </c:if>

        <c:if test="${not empty fList}">
            <table>
                <thead>
                    <tr>
                        <th>Fine ID</th>
                        <th>Borrow ID</th>
                        <th>Fine Amount (VND)</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="f" items="${fList}">
                        <tr>
                            <td>${f.id}</td>
                            <td>${f.borrowId}</td>
                            <td>${f.fineAmount}</td>
                            <td class="${f.paidStatus == 'paid' ? 'paid' : 'unpaid'}">${f.paidStatus}</td>
                            <td>
                                <c:if test="${f.paidStatus == 'unpaid'}">
                                    <form action="FineController" method="post">
                                        <input type="hidden" name="action" value="updatefine" />
                                        <input type="hidden" name="fineid" value="${f.id}" />
                                        <select name="newstatus">
                                            <option value="unpaid" selected>Unpaid</option>
                                            <option value="paid">Paid</option>
                                        </select>
                                        <button type="submit">Update</button>
                                    </form>
                                </c:if>
                                <c:if test="${f.paidStatus == 'paid'}">
                                    <span style="color: gray;">No action</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
