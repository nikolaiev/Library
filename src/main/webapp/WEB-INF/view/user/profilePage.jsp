<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12.04.17
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>

<jsp:include page="/WEB-INF/view/fragments/header.jsp" />


Processed books' orders
<br>
<table>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${id}"/></td>
            <td><c:out value="${order.orderDateTime}"/></td>
            <td><c:out value="${order.type}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><c:out value="${order.book.title}"/></td>
            <td>
                <button id="<c:out value="${bookId}"/>" class="remove-button">
                    remove from profile story
                </button>
            </td>

        </tr>
    </c:forEach>
</table>
<jsp:include page="/WEB-INF/view/fragments/footer.jsp"/>