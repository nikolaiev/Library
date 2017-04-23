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
<%--FILTERS--%>
<div class="row col-lg-1"></div>
<div class="row col-lg-11 center-block top-buffer">
    <form class="form-inline" method="get">
        <div class="form-group">
            <label class="mr-sm-2" >Title</label>
            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
                   name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>
        </div>

        <%--author--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Before date</label>
            <input type="date" id="bdate" name="before_date"/>
        </div>

        <%--genre--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Type</label>
            <select name="type" class="selectpicker" data-width="100px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${types}" var="type">
                    <option value="<c:out value="${type}"/>"
                            <c:if test="${type==param.type}">
                                selected
                            </c:if>
                    >
                        <c:out value="${type}"/> </option>
                </c:forEach>

            </select>
        </div>

        <%--language--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Status</label>
            <select name="status" class="selectpicker" data-width="150px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${statuses}" var="status">
                    <option value="<c:out value="${status}"/>"
                            <c:if test="${status==param.status}">
                                selected
                            </c:if>
                    >
                        <c:out value="${status}"/> </option>
                </c:forEach>

            </select>
        </div>

        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Items per page</label>

            <select name="limit" class="selectpicker" data-width="75px">
                <%--set limit value or default--%>
                <c:if test="${not empty param.limit}">
                    <c:set var="limit" value="${param.limit}"/>
                </c:if>

                <c:if test="${empty param.limit}">
                    <c:set var="limit" value="${defLimit}"/>
                </c:if>
                <%--set limitation values--%>
                <c:set var="pages" value="${[5,10,15,20,30]}"/>

                <c:forEach var="amount" items="${pages}">
                    <option value="${amount}"
                            <c:if test="${amount==limit}">
                                selected
                            </c:if>
                    >${amount}</option>
                </c:forEach>

            </select>
        </div>

        <button type="submit" class="btn btn-info right">Find orders</button>

    </form>

    <form method="get">
        <button type="submit" class="btn btn-warning right">Clear filter</button>
    </form>

</div>
<%--FILTERS END--%>
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
                        <p>Date is: ${f:formatLocalDate(order.orderDateTime, 'dd.MM.yyyy ,hh:mm a')}</p>

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