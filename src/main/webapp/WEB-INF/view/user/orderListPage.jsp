<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.04.17
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oreder List page</title>

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>

    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>

</head>

<jsp:include page="/WEB-INF/view/fragments/header.jsp" />
Orders list
<br>
<table>
    <c:forEach items="${bookOrders}" var="order">
        <c:set var="bookId" scope="request" value="${order.key}"/>
        <c:set var="orderItem" scope="request" value="${order.value}"/>
        <tr>

            <td><c:out value="${bookId}"/></td>
            <td><c:out value="${orderItem.orderStatus}"/></td>
            <td><c:out value="${orderItem.orderType}"/></td>
            <td>
                <button id="<c:out value="${bookId}"/>" class="remove-button">
                    remove from card!
                </button>
            </td>

        </tr>
    </c:forEach>
</table>

<jsp:include page="/WEB-INF/view/fragments/footer.jsp"/>
<script>
    //TODO remove item from table
    var removeButtons=$('.remove-button');
    for(let i=0; i<removeButtons.length; i++){
        let but=removeButtons[i].id;
        $(removeButtons[i]).click(()=>{
            $.ajax({
                type: "POST",
                url: "/user/books/remove",
                data: {id:but},
                success: function(){
                    alertify.success('Book was successfuly removed');
                    $(removeButtons[i]).parent().parent().remove();
                }
            });
        })
    }

</script>