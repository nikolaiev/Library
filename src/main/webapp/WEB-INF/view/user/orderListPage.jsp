<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.04.17
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oreder List page</title>
</head>
<body>
Books list
<br>
<table>
    <c:forEach items="${books}" var="book">
        <tr>

            <td><c:out value="${book.key}"/></td>
            <td><c:out value="${book.value}"/></td>

            <td>
                <button id="<c:out value="${book.key}"/>" class="remove-button">
                    remove from card!
                </button>
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
