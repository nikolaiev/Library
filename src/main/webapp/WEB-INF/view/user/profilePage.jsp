<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://mytags.com/jsp/mytags" %>
<%@ taglib prefix="f" uri="http://mytags.com/jsp/myfuncs"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/header" var="titles"/>
<fmt:setBundle basename="i18n/profile" var="profile_fmt"/>
<fmt:setBundle basename="i18n/orderType" var="order_type_fmt"/>
<fmt:setBundle basename="i18n/orderStatus" var="order_status_fmt"/>



<html>
<head>
    <title><fmt:message bundle="${titles}" key="profile"/></title>

    <jsp:include page="/WEB-INF/view/user/fragments/header.jsp" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
</head>

<%--TODO replace explicit values comparing via jstl el by JS--%>
<jsp:include page="/WEB-INF/view/fragments/orderFilter.jsp"/>
<%--TABLE--%>
<div class="container top-buffer">

    <h2><fmt:message bundle="${profile_fmt}" key="user_name"/> :
        <span id="user-name">
            <c:out value="${user.name}"/>
            <c:out value="${user.soname}"/>
        </span>
    </h2>

    <h2><fmt:message bundle="${profile_fmt}" key="profile_id"/> :
        <span id="profile-id">
            <c:out value="${sessionScope.userId}"/>
        </span>
    </h2>

    
    <c:if test="${not empty orders}">
        
        <h2><fmt:message bundle="${profile_fmt}" key="processed_user_order"/></h2>
        <p  class="info-text"><fmt:message bundle="${profile_fmt}" key="orders_found"/> :
            <c:out value="${totalCount}"/>
        </p>
        <br>
        <table class="table table-hover">
            <tr>
                <th><fmt:message bundle="${profile_fmt}" key="date"/></th>
                <th><fmt:message bundle="${profile_fmt}" key="book"/></th>
                <th><fmt:message bundle="${profile_fmt}" key="type"/></th>
                <th><fmt:message bundle="${profile_fmt}" key="status"/></th>
                <th></th>
            </tr>

            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>
                        <p>${f:formatInstantToLocale(order.instant, 'dd.MM.yyyy ,hh:mm a')}</p>

                    </td>

                    <td><c:out value="${order.book.title}"/></td>
                    <td>
                        <fmt:message bundle="${order_type_fmt}" key="${order.type}"/>
                    </td>
                    <td>
                        <fmt:message bundle="${order_status_fmt}" key="${order.status}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </c:if>

    <c:if test="${empty orders}">
        <h2><fmt:message bundle="${profile_fmt}" key="empty_history"/></h2>

        <p>
            <%--You can
            <a href="/user/books">choose some books </a>
            to order them--%>
                <fmt:message bundle="${profile_fmt}" key="suggesting"/>
        </p>
    </c:if>
</div>


<m:display paginParamName="page" totalPages="${totalPages}"/>

<jsp:include page="/WEB-INF/view/user/fragments/footer.jsp"/>

<%--SCRIPTS--%>
<script>

    let getUrlParameter = (sParam)=>{
        let sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };

    /*init selects*/
    $('.selectpicker').selectpicker();

    /*set timpicker value from get params (unable to do in JSP)*/
    let bdateValue=getUrlParameter("before_date");

    if(bdateValue!=="") {
        $('#bdate').val(bdateValue);
    }



</script>