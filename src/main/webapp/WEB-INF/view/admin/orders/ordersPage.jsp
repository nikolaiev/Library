<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/paginator.tld" %>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/dateFormatter.tld" %>

<html>
<head>
    <title>Orders</title>
</head>

<jsp:include page="../fragments/header.jsp"/>

<jsp:include page="../../fragments/orderFilter.jsp"/>



<br>
<div class="container top-buffer">
    <h2>Orders list</h2>
    <br>
    <table class="table table-hover">
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Order date</th>
            <th>Status</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${orders}" var="order">
            <tr>
                <td><c:out value="${order.book.title}"/></td>
                <td><c:out value="${order.book.author.name} ${order.book.author.soname}"/></td>
                <td>${f:formatInstantToLocale(order.instant, 'dd.MM.yyyy ,hh:mm a')}</td>
                <td>
                    <span id='status_${order.id}'>
                        ${order.status}
                    </span>
                </td>
                <td>
                    <c:if test="${order.status ne 'RETURNED'}">
                        <button class="return-button btn btn-info" id='${order.id}'>RETURN BOOK</button>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>

<%--PAGINATOR--%>
<m:display paginParamName="page" totalPages="${totalPages}"/>

<jsp:include page="../fragments/footer.jsp"/>

<script type="text/javascript">

    let returnButtons=$('.return-button');

    for(let i=0; i<returnButtons.length; i++){
        let but=returnButtons[i].id;
        $(returnButtons[i]).click(()=>{

            $.post({
                url: "${pageContext.request.contextPath}/admin/orders/update",
                data: {id:but},

                success:()=>{
                    $('#'+but).remove();
                    //TODO localize!!!
                    $('#status_'+but).html("RETURNED");
                    alertify.success('Status changed')
                },
                error:()=>{
                    alertify.danger("Book can't be returned")
                }
            });
        })
    }
</script>
