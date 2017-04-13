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
    Books list
    <br>
    <table>
    <c:forEach items="${books}" var="book">
        <tr>

            <td><img class="order-image" src='${pageContext.request.contextPath}<c:out value="${book.image}"/>' /></td>
            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.author.soname}"/></td>
            <td><c:out value="${book.genre}"/></td>

            <td>
                <button id="<c:out value="${book.id}"/>" class="buy-button">
                    add to card!
                </button>
            </td>

        </tr>
    </c:forEach>
    </table>


<jsp:include page="/WEB-INF/view/fragments/footer.jsp" />

<script>
    var buyButtons=$('.buy-button');
    for(let i=0;i<buyButtons.length;i++){
        let but=buyButtons[i].id;
        $(buyButtons[i]).click(()=>{
            $.ajax({
                type: "POST",
                url: "/user/books/add",
                data: {id:but},
                //TODO make some DOM operations!!!
                success: function(){
                    $(buyButtons[i]).attr("disabled",true);
                    alertify.success('Book was successfuly added');

                }
            });
        })
    }
</script>
