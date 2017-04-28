<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/header" var="book"/>

<div>

    <%--CSS--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myCss.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"/>

    <%--JS--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

</div>

<body>
    <ul class="menu-list">
        <li ><a href="${pageContext.request.contextPath}/user/profile">
            <fmt:message bundle="${book}" key="profile"/>
        </a></li>
        <li ><a href="${pageContext.request.contextPath}/user/books">
            <fmt:message bundle="${book}" key="books"/>
        </a></li>

        <li>
            <a href="${pageContext.request.contextPath}/user/orders">
                <fmt:message bundle="${book}" key="orders"/>
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

        <li class="right" ><a href="${pageContext.request.contextPath}/logout">
            <fmt:message bundle="${book}" key="logout"/>
        </a></li>
        <li class="right" ><a href="?locale=RUS">rus</a></li>
        <li class="right" ><a href="?locale=UA">ua</a></li>
        <li class="right" ><a href="?locale=ENG">eng</a></li>

    </ul>

