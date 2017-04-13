<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 03.04.17
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>

</head>

<jsp:include page="/WEB-INF/view/fragments/header.jsp" />
<p>
    Home page!
    You can order books here. Enjoy!
</p>
<jsp:include page="/WEB-INF/view/fragments/footer.jsp" />

