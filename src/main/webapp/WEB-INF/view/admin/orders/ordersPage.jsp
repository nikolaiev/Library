<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/paginator.tld" %>

<html>
<head>
    <title>Orders</title>
</head>

<jsp:include page="../fragments/header.jsp"/>

<jsp:include page="../../fragments/orderFilter.jsp"/>



<br>
<div class="container">
    <h2>Orders list</h2>
    <div class="row col-md-offset-1">
        <table class="table table-hover">
        <tr>
            <th>Image</th>
            <th>Book title</th>
            <th>Status</th>
            <th></th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>

            <%--<td><img class="order-image" src='${pageContext.request.contextPath}/static/<c:out value="${order.book.image}"/>' /></td>--%>
            <td><c:out value="${order.book.title}"/></td>.
            <td><c:out value="${order.status}"/></td>
            <td>
                <c:if test="${order.status ne 'RETURNED'}">
                    <button class="return-button btn btn-info" id='<c:out value="${order.id}"/>'>RETURN BOOK</button>
                </c:if>
            </td>

            </tr>
        </c:forEach>
        </table>
    </div>
</div>

<%--PAGINATOR--%>
<m:display paginParamName="page" totalPages="${totalPages}"/>

<jsp:include page="../fragments/footer.jsp"/>

<script type="text/javascript">

    var returnButtons=$('.return-button');
    for(let i=0; i<returnButtons.length; i++){
        let but=returnButtons[i].id;
        $(returnButtons[i]).click(()=>{

            $.ajax({
                type: "POST",
                url: "/admin/orders/update",
                data: {id:but},
                success:()=>{alertify.success('Status changed')}
            });
        })
    }
</script>
