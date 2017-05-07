<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://mytags.com/jsp/mytags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/header" var="menu_fmt"/>


<div>

    <%--CSS--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myCss.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css"/>

    <%--JS--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>

</div>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header navbar-brand">
            <fmt:message bundle="${menu_fmt}" key="admin_panel"/>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="${pageContext.request.contextPath}/admin/books">
                <fmt:message bundle="${menu_fmt}" key="books"/>
            </a></li>
            <li><a href="${pageContext.request.contextPath}/admin/orders">
                <fmt:message bundle="${menu_fmt}" key="orders"/>
            </a></li>
            <li><a href="${pageContext.request.contextPath}/admin/publishers">
                <fmt:message bundle="${menu_fmt}" key="publishers"/>
            </a></li>
            <li><a href="${pageContext.request.contextPath}/admin/authors">
                <fmt:message bundle="${menu_fmt}" key="authors"/>
            </a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li class="right" ><a href="?locale=ENG">eng</a></li>
            <li class="right" ><a href="?locale=UA">ua</a></li>
            <li class="right" ><a href="?locale=RUS">rus</a></li>

            <li class="right"><a href="${pageContext.request.contextPath}/logout" >
                <fmt:message bundle="${menu_fmt}" key="logout"/>
            </a></li>
        </ul>
    </div>
</nav>
