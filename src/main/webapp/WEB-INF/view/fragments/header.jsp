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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myCss.css"/>
</head>
<body>
    <ul>
        <li><a href="/user/home">Home</a></li>
        <li><a href="/user/books">Books</a></li>
        <li><a href="/user/profile">Profile</a></li>
        <li style="float: right"><a href="/logout">Logout</a></li>
        <li style="float: right"><a href="/user/orders">Orders</a></li>
    </ul>

