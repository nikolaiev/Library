<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/header" var="titles"/>
<fmt:setBundle basename="i18n/userOrders" var="user_orders_fmt"/>
<fmt:setBundle basename="i18n/orderStatus" var="order_status_fmt"/>
<fmt:setBundle basename="i18n/orderType" var="order_type_fmt"/>

<html>
<head>
    <title>
        <fmt:message bundle="${titles}" key="orders"/>
    </title>

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>

    <script src="${pageContext.request.contextPath}/js/alertify.min.js" ></script>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.min.css"/>

</head>

<jsp:include page="/WEB-INF/view/user/fragments/header.jsp" />

<div class="container top-buffer">
    <c:if test="${not empty bookOrders}">
        <h2>
            <fmt:message bundle="${user_orders_fmt}" key="your_orders"/>
        </h2>

        <br>

        <table class="table table-hover">
            <tr>
                <th>
                    <fmt:message bundle="${user_orders_fmt}" key="title"/>
                </th>
                <th>
                    <fmt:message bundle="${user_orders_fmt}" key="status"/>
                </th>
                <th>
                    <fmt:message bundle="${user_orders_fmt}" key="order_type"/>
                </th>
                <th></th>
            </tr>

            <c:forEach items="${bookOrders}" var="order">
                <c:set var="book" scope="page" value="${order.key}"/>
                <c:set var="orderItem" scope="page" value="${order.value}"/>
                <tr>

                    <td>
                        <a href="${pageContext.request.contextPath}/user/book/<c:out value="${book.id}"/>" >
                            <c:out value="${book.title}"/>
                        </a>
                    </td>

                    <td>
                        <fmt:message bundle="${order_status_fmt}" key="${orderItem.orderStatus}"/>
                    </td>
                    <td>
                        <fmt:message bundle="${order_type_fmt}" key="${orderItem.orderType}"/>
                    </td>
                    <td>
                        <button id="<c:out value="${book.id}"/>" class="remove-button btn btn-danger">
                            <fmt:message bundle="${user_orders_fmt}" key="remove_from_orders"/>
                        </button>
                    </td>

                </tr>
            </c:forEach>
        </table>


            <div align="center">
                <form action="${pageContext.request.contextPath}/user/process" method="post">

                    <button type="submit" id="submit-button"  class="remove-button btn btn-success" value="Process order list">
                        <fmt:message bundle="${user_orders_fmt}" key="submit_orders"/>
                    </button>

                </form>
            </div>
    </c:if>

    <%--else--%>

    <c:if test="${empty bookOrders}">
        <h2>
            <fmt:message bundle="${user_orders_fmt}" key="empty_list"/>
        </h2>

        <p>
            <fmt:message bundle="${user_orders_fmt}" key="suggesting"/>
        </p>
    </c:if>
</div>


<jsp:include page="/WEB-INF/view/user/fragments/footer.jsp"/>
<script>
    var removeButtons=$('.remove-button');

    for(let i=0; i<removeButtons.length; i++){
        let but=removeButtons[i].id;
        $(removeButtons[i]).click(()=>{
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/books/remove",
                data: {id:but},
                success: ()=>{
                    updatePageElements(removeButtons[i]);
                    alertify.success('<fmt:message bundle="${user_orders_fmt}" key="book_was_removed"/>');
                },
                error:()=>{
                    alertify.error('<fmt:message bundle="${user_orders_fmt}" key="book_was_not_removed"/>');
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