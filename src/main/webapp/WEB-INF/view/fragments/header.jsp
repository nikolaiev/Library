<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 30.03.17
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%--CSS--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myCss.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"/>

    <%--JS--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

</head>
<body>
    <ul class="menu-list">
        <li ><a href="${pageContext.request.contextPath}/user/profile">Profile</a></li>
        <li ><a href="${pageContext.request.contextPath}/user/books">Books</a></li>

        <li>
            <a href="${pageContext.request.contextPath}/user/orders">
                Orders
                <div id="item-count-holder">

                    <c:set  scope="session" var="ordersSize" value="${fn:length(sessionScope.orderList.bookOrders)}"/>
                    <c:if test="${ordersSize !=0}">
                        <span id="item-count">
                            ${ordersSize}
                        </span>
                    </c:if>
                </div>
            </a>
        </li>

        <li class="right" ><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>

