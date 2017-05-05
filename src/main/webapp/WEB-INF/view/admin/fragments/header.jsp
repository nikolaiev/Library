<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://mytags.com/jsp/mytags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Admin panel</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="${pageContext.request.contextPath}/admin/books">Books</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/orders">Orders</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/publishers">Publishers</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/authors">Authors</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li class="right" ><a href="?locale=ENG">eng</a></li>
            <li class="right" ><a href="?locale=UA">ua</a></li>
            <li class="right" ><a href="?locale=RUS">rus</a></li>

            <li class="right"><a href="${pageContext.request.contextPath}/logout" >Logout</a></li>
        </ul>
    </div>
</nav>
