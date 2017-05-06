<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mytags.com/jsp/mytags" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/books" var="books_fmt"/>
<fmt:setBundle basename="i18n/header" var="titles"/>
<fmt:setBundle basename="i18n/genres" var="genres_fmt"/>

<html>
<head>
    <title><fmt:message bundle="${titles}" key="books"/></title>
</head>
<jsp:include page="/WEB-INF/view/user/fragments/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css"/>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>



<%--FILTERS END--%>
<jsp:include page="/WEB-INF/view/fragments/bookFilter.jsp"/>

<%--BOOKS TABLE--%>
<div class="container top-buffer">
    <h2>
        <fmt:message bundle="${books_fmt}" key="table_title"/>
    </h2>

    <p class="info-text"><fmt:message bundle="${books_fmt}" key="books_found"/> :
        <c:out value="${totalCount}"/>
    </p>
    <br>


    <table class="table table-hover">
        <tr>
            <th><fmt:message bundle="${books_fmt}" key="image"/></th>
            <th><fmt:message bundle="${books_fmt}" key="title"/></th>
            <th><fmt:message bundle="${books_fmt}" key="author"/></th>
            <th><fmt:message bundle="${books_fmt}" key="genre"/></th>
            <th></th>
            <th></th>
        </tr>
    <c:forEach items="${books}" var="book">
        <tr>

            <td><img class="order-image" src='${pageContext.request.contextPath}/static/<c:out value="${book.image}"/>' /></td>
            <%--<td><c:out value="${book.id}"/></td>--%>
            <td>
                <%--<a href="${pageContext.request.contextPath}/user/book/<c:out value="${book.id}"/>">--%>
                    <c:out value="${book.title}"/>
                <%--</a>--%>
            </td>

            <td>
                <a href="${pageContext.request.contextPath}/user/books?author_id=<c:out value="${book.author.id}"/>">
                    <c:out value="${book.author.name.concat(' ').concat(book.author.soname)}"/>
                </a>
            </td>

            <td>
                <a href="${pageContext.request.contextPath}/user/books?genre=<c:out value="${book.genre}"/>">
                    <fmt:message bundle="${genres_fmt}" key="${book.genre}"/>
                </a>


            <td>
                <button id="<c:out value="${book.id}_home"/>" class="order-button-home btn btn-default">
                    <fmt:message bundle="${books_fmt}" key="take_home"/>
                </button>
            </td>

            <td>
                <button id="<c:out value="${book.id}_read"/>" class="order-button-read btn btn-info">
                    <fmt:message bundle="${books_fmt}" key="take_library"/>
                </button>
            </td>

        </tr>
    </c:forEach>
    </table>
</div>
<%--BOOKS TABLE ENDS--%>

<%--PAGINATOR--%>
<m:display paginParamName="page" totalPages="${totalPages}"/>

<%--FOOTER--%>
<jsp:include page="/WEB-INF/view/user/fragments/footer.jsp" />

<%--SCRIPTS--%>
<script type="text/javascript">

    /*init selects*/
    $('.selectpicker').selectpicker();


    let bindButtons=(buttons,orderType)=>{
        for(let i=0; i<buttons.length; i++){
            let but=buttons[i].id.split('_')[0];
            $(buttons[i]).click(()=>{
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/user/books/add",
                    data: {
                        id:but,
                        order_type:orderType//'LIBRARY'
                    },
                    success: function(data){
                        $(buttons[i]).attr("disabled",true);
                        $('#item-count-holder').load(' #item-count');
                        alertify.success('<fmt:message bundle="${books_fmt}" key="book_was_added"/>');
                    },
                    error:function () {
                        alertify.error('<fmt:message bundle="${books_fmt}" key="book_wan_not_added"/>');
                    }
                });
            })
        }
    };


    /*order home buttons*/
    let orderHomeButtons=$('.order-button-home');
    /*order library buttons*/
    let orderReadHallButtons=$('.order-button-read');

    bindButtons(orderHomeButtons,'HOME');
    bindButtons(orderReadHallButtons,'LIBRARY');

</script>
