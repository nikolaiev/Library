<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.04.17
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Book</title>
</head>
<body>
    fill next form
    <form method="post" action="${pageContext.request.contextPath}/admin/books/add" enctype="multipart/form-data">
        <input type="text"  required name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>
        <br>
        <br>
        <input type="file" required name="book_image" accept="image/jpeg,image/png"/>
        <br>
        <br>
        <input type="number" required name="count" min="0" max="500"/>
        <br>
        <br>
        <%--TODO make required--%>
        <input type="date" name="pday" min="1900-01-01"/>
        <br>
        <br>

        <%--author--%>
        <select name="author_id" required>
            <option value="" disabled selected>All authors</option>

            <c:forEach items="${authors}" var="author">
                <option value="<c:out value="${author.id}"/>">
                    <c:out value="${author.name}"/> <c:out value="${author.soname}"/></option>
            </c:forEach>

        </select>
        <br>
        <br>

        <%--genre--%>

        <select name="genre" required>
            <option value="" disabled selected>All genres</option>

            <c:forEach items="${genres}" var="genre">
                <option value="<c:out value="${genre}"/>">
                    <c:out value="${genre}"/> </option>
            </c:forEach>

        </select>
        <br>
        <br>
        <%--language--%>
        <select name="language" required>
            <option value="" disabled selected>All languages</option>

            <c:forEach items="${languages}" var="language">
                <option value="<c:out value="${language}"/>">
                    <c:out value="${language}"/> </option>
            </c:forEach>

        </select>
        <br>
        <br>
        <%--publishers--%>
        <select name="publisher_id" required>
            <option value="" disabled selected>All publishers</option>

            <c:forEach items="${publishers}" var="publ">
                <option value="<c:out value="${publ.id}"/>">
                    <c:out value="${publ.title}"/> </option>
            </c:forEach>

        </select>
        <br>
        <br>
        <br>
        <br>
        <input type="submit" value="create book"/>
    </form>
</body>
</html>
