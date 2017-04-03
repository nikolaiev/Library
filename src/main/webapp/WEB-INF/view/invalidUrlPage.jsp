<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 04.04.17
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>InvalidUrl</title>
</head>
<body>
    404
    <b><%= request.getParameter("url") %></b>!
    <b><%= request.getParameter("testAttr") %></b>!
    <c:set var="url" value='${requestScope["url"]}' />
    <c:set var="message" value='${requestScope["testAttr"]}' />
</body>
</html>
