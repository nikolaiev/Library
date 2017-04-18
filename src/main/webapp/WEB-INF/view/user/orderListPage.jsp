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
    <title>Order list</title>

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>

    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>

</head>

<jsp:include page="/WEB-INF/view/fragments/header.jsp" />

<div class="container top-buffer">
    <c:if test="${not empty bookOrders}">
        <h2>Your orders</h2>
        <p>The .table-hover class enables a hover state on table rows:</p>
        <br>


        <table class="table table-hover">
            <tr>
                <th>Title</th>
                <th>Status</th>
                <th>Order type</th>
                <th></th>
            </tr>

            <c:forEach items="${bookOrders}" var="order">
                <c:set var="book" scope="page" value="${order.key}"/>
                <c:set var="orderItem" scope="page" value="${order.value}"/>
                <tr>

                    <td>
                        <a href="/user/book/<c:out value="${book.id}"/>" >
                            <c:out value="${book.title}"/>
                        </a>
                    </td>

                    <td><c:out value="${orderItem.orderStatus}"/></td>
                    <td><c:out value="${orderItem.orderType}"/></td>
                    <td>
                        <button id="<c:out value="${book.id}"/>" class="remove-button btn btn-danger">
                            remove from orders
                        </button>
                    </td>

                </tr>
            </c:forEach>
        </table>


            <div align="center">
                <form action="${pageContext.request.contextPath}/user/process" method="post">

                    <button type="submit" id="submit-button"  class="remove-button btn btn-success" value="Process order list">
                        Submit orders
                    </button>

                </form>
            </div>
    </c:if>

    <%--else--%>

    <c:if test="${empty bookOrders}">
        <h2>Empty order list</h2>

        <p>
            You can
            <a href="/user/books">choose some books </a>
            to order them
        </p>
    </c:if>
</div>

<jsp:include page="/WEB-INF/view/fragments/footer.jsp"/>
<script>
    var removeButtons=$('.remove-button');

    for(let i=0; i<removeButtons.length; i++){
        let but=removeButtons[i].id;
        $(removeButtons[i]).click(()=>{
            $.ajax({
                type: "POST",
                url: "/user/books/remove",
                data: {id:but},
                success: ()=>{
                    updatePageElements(removeButtons[i]);
                    alertify.success('Book was successfuly removed');
                },
                error:()=>{
                    alertify.error("Book can'n be removed");
                }
            });
        })
    }

    var updatePageElements=(clickedButton)=>{
        $(clickedButton).parent().parent().remove();
        $('#item-count-holder').load(' #item-count');

        if($('.table-hover').find('tr').length===1){
            $('#submit-button').attr("disabled", true);
        }
    };

</script>