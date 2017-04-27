<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://mytags.com/jsp/mytags" %>
<%@ taglib prefix="f" uri="http://mytags.com/jsp/myfuncs"  %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12.04.17
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>

    <jsp:include page="/WEB-INF/view/user/fragments/header.jsp" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
</head>

<%--TODO replace explicit values comparing via jstl el by JS--%>
<jsp:include page="/WEB-INF/view/fragments/orderFilter.jsp"/>
<%--TABLE--%>
<div class="container top-buffer">

    <h2>User name :
        <span id="user-name">
            <c:out value="${user.name}"/>
            <c:out value="${user.soname}"/>
        </span>
    </h2>

    <h2>Profile id :
        <span id="profile-id">
            <c:out value="${sessionScope.userId}"/>
        </span>
    </h2>

    
    <c:if test="${not empty orders}">
        
        <h2>Processed books' orders</h2>
        <p  class="info-text">Orders found :
            <c:out value="${totalCount}"/>
        </p>
        <br>
        <table class="table table-hover">
            <tr>
                <th>Date</th>
                <th>Book</th>
                <th>Type</th>
                <th>Status</th>
                <th></th>
            </tr>

            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>
                        <p>Date is: ${f:formatInstantToLocale(order.instant, 'dd.MM.yyyy ,hh:mm a')}</p>

                    </td>

                    <td><c:out value="${order.book.title}"/></td>
                    <td><c:out value="${order.type}"/></td>
                    <td><c:out value="${order.status}"/></td>
                </tr>
            </c:forEach>
        </table>

    </c:if>

    <c:if test="${empty orders}">
        <h2>Empty history</h2>

        <p>
            You can
            <a href="/user/books">choose some books </a>
            to order them
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