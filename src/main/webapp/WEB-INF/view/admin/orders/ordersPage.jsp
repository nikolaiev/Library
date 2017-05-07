<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/paginator.tld" %>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/dateFormatter.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/profile" var="order_fmt"/>
<fmt:setBundle basename="i18n/header" var="titles_fmt"/>
<fmt:setBundle basename="i18n/orderStatus" var="order_status_fmt"/>

<html>
<head>
    <title><fmt:message bundle="${titles_fmt}" key="orders"/></title>
</head>

<jsp:include page="../fragments/header.jsp"/>

<jsp:include page="../../fragments/orderFilter.jsp"/>



<br>
<div class="container top-buffer">
    <h2>
        <fmt:message bundle="${titles_fmt}" key="orders"/>
    </h2>
    <br>

    <table class="table table-hover">
        <tr>
            <th><fmt:message bundle="${order_fmt}" key="book"/></th>
            <th>
                <fmt:message bundle="${order_fmt}" key="author"/>
            </th>
            <th>
                <fmt:message bundle="${order_fmt}" key="date"/>
            </th>
            <th>
                <fmt:message bundle="${order_fmt}" key="status"/>
            </th>
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
                        <fmt:message bundle="${order_status_fmt}" key="${order.status}"/>
                    </span>
                </td>
                <td>
                    <c:if test="${order.status ne 'RETURNED'}">
                        <button class="return-button btn btn-info" id='${order.id}'>
                            <fmt:message bundle="${order_fmt}" key="return_book"/>
                        </button>
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
                    $('#status_'+but).html('<fmt:message bundle="${order_status_fmt}" key="RETURNED"/>');
                    alertify.success('<fmt:message bundle="${order_fmt}" key="status_changed"/>')
                },
                error:()=>{
                    alertify.error('<fmt:message bundle="${order_fmt}" key="book_not_returned"/>');
                }
            });
        })
    }
</script>
