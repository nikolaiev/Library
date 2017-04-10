
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 30.03.17
  Time: 2:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
    <link href="../../css/myCss.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="login-form">
    <c:if test="${not empty error}">
        <h3>${error}</h3>
    </c:if>

    <form action="login" method="post">
        <label>e-mail : <input type="email" name="login"></label>
        <br>
        <label>password : <input type="password" name="password"></label>
        <br>
        <input type="submit" value="log in">
    </form>
</div>

</body>
</html>
