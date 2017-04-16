<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 03.04.17
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Books</title>

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>

    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myCss.css"/>


</head>

<jsp:include page="/WEB-INF/view/fragments/header.jsp" />
    <form method="get">
        <input type="text" name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>

        <%--author--%>
        <select name="author_id">
            <option value="" disabled selected>All authors</option>

            <c:forEach items="${authors}" var="author">
                <option value="<c:out value="${author.id}"/>">
                    <c:out value="${author.name}"/> <c:out value="${author.soname}"/></option>
            </c:forEach>

        </select>

        <%--genre--%>

        <select name="genre">
            <option value="" disabled selected>All genres</option>

            <c:forEach items="${genres}" var="genre">
                <option value="<c:out value="${genre}"/>">
                    <c:out value="${genre}"/> </option>
            </c:forEach>

        </select>

        <%--language--%>
        <select name="language">
            <option value="" disabled selected>All languages</option>

            <c:forEach items="${languages}" var="language">
                <option value="<c:out value="${language}"/>">
                    <c:out value="${language}"/> </option>
            </c:forEach>

        </select>

        <%--publishers--%>
        <select name="publisher_id">
            <option value="" disabled selected>All publishers</option>

            <c:forEach items="${publishers}" var="publ">
                <option value="<c:out value="${publ.id}"/>">
                    <c:out value="${publ.title}"/> </option>
            </c:forEach>

        </select>

        <input type="submit" value="filter"/>
    </form>

    <form method="get">
        <input type="submit" value="clear filter">
    </form>
    <br>
    <br>
    <br>
    <br>

    <table>
    <c:forEach items="${books}" var="book">
        <tr>

            <td><img class="order-image" src='${pageContext.request.contextPath}/<c:out value="${book.image}"/>' /></td>
            <%--<td><c:out value="${book.id}"/></td>--%>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.author.soname}"/></td>
            <td><c:out value="${book.genre}"/></td>

            <td>
                <button id="<c:out value="${book.id}_home"/>" class="order-button-home">
                    add to card!
                </button>
            </td>

            <td>
                <button id="<c:out value="${book.id}_read"/>" class="order-button-read">
                    take to read hall!
                </button>
            </td>

        </tr>
    </c:forEach>
    </table>


<jsp:include page="/WEB-INF/view/fragments/footer.jsp" />

<script>
    let orderHomeButtons=$('.order-button-home');

    for(let i=0; i<orderHomeButtons.length; i++){
        let but=orderHomeButtons[i].id.split('_')[0];
        $(orderHomeButtons[i]).click(()=>{
            $.ajax({
                type: "POST",
                url: "/user/books/add",
                data: {
                    id:but,
                    order_type:'HOME'
                },
                success: function(){
                    $(orderHomeButtons[i]).attr("disabled",true);
                    alertify.success('Book was successfully added');
                },
                error:function () {
                    alertify.error("Can't request book");
                }
            });
        })
    }

    /*take to read buttons*/

    let orderReadHallButtons=$('.order-button-read');

    for(let i=0; i<orderReadHallButtons.length; i++){
        let but=orderReadHallButtons[i].id.split('_')[0];
        $(orderReadHallButtons[i]).click(()=>{
            $.ajax({
                type: "POST",
                url: "/user/books/add",
                data: {
                    id:but,
                    order_type:'LIBRARY'
                },
                success: function(){
                    $(orderReadHallButtons[i]).attr("disabled",true);
                    alertify.success('Book was successfully added');
                },
                error:function () {
                    alertify.error("Can't request book");
                }
            });
        })
    }
</script>
