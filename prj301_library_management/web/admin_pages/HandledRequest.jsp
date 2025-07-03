<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Handled Requests</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
            }
            .popup {
                position: fixed;
                top: 20px;
                right: 20px;
                background-color: #fff5c4;
                border: 1px solid #aaa;
                padding: 10px;
                border-radius: 5px;
                z-index: 9999;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
            }
        </style>
    </head>
    <body>
        <h2>All Accepted Requests</h2>
        <jsp:include page="/admin_pages/dashboard.jsp" />
        <div style="margin-top: 30px;"></div>
        <c:if test="${not empty result}">
            <div class="popup" style="color: green;"><strong>${result}</strong></div>
                </c:if>

        <c:if test="${not empty error}">
            <div class="popup" style="color: red;"><strong>${error}</strong></div>
                </c:if>

        <c:if test="${not empty finedhandle}">
            <div class="popup" style="color: orange;">
                <p>${finedhandle}</p>
                <form action="FineController" method="get" style="display: inline;">
                    <input type="hidden" name="action" value="finecontrol" />
                    <input type="submit" value="Yes, go to Fine Control" />
                </form>
                <form action="HandledRequestController" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="handledrequest" />
                    <input type="submit" value="No, stay here" />
                </form>
            </div>
        </c:if>

        <c:if test="${empty hList}">
            <p>No handled requests found.</p>
        </c:if>

        <c:if test="${not empty hList}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Request ID</th>
                        <th>User ID</th>
                        <th>Book ID</th>
                        <th>Request Type</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="req" items="${hList}">
                        <tr>
                            <td>${req.handledRequestId}</td>
                            <td>${req.requestId}</td>
                            <td>${req.userId}</td>
                            <td>${req.bookId}</td>
                            <td>${req.requestType}</td>
                            <td>${req.status}</td>
                            <td>
                                <c:if test="${req.status == 'pending'}">
                                    <form action="HandledRequestController" method="post">
                                        <input type="hidden" name="handledrequestid" value="${req.handledRequestId}" />
                                        <input type="hidden" name="requestid" value="${req.requestId}" />
                                        <input type="hidden" name="userid" value="${req.userId}" />
                                        <input type="hidden" name="bookid" value="${req.bookId}" />
                                        <input type="hidden" name="requestType" value="${req.requestType}" />
                                        <c:choose>
                                            <c:when test="${req.requestType == 'borrow'}">
                                                <label>Borrow Date:</label>
                                                <input class="input-field" type="date" name="borrowdate" required />
                                                <button type="submit" name="action" value="submitborrow">Submit</button>
                                            </c:when>
                                            <c:when test="${req.requestType == 'return'}">
                                                <label>Return Date:</label>
                                                <input class="input-field" type="date" name="returndate" required />
                                                <button type="submit" name="action" value="submitreturn">Calculate Fine</button>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </c:if>
                                <c:if test="${req.status == 'handled'}">
                                    <span style="color: gray;">Already processed</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <script>
            setTimeout(() => {
                document.querySelectorAll('.popup').forEach(el => el.remove());
            }, 10000);
        </script>

    </body>
</html>
