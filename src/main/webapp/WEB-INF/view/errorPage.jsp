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
    <title>Error page</title>
</head>
<body>
<h3>Error occurred!</h3>
    <br>
    <c:out value="${requestScope.error}"/>
    <br>
    <c:out value="${requestScope.error_message}"/>
    <br>
    <c:out value="${requestScope.error_additional_message}"/>
</body>
</html>
