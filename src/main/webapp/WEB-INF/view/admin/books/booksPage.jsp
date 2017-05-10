<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/paginator.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/books" var="books_fmt"/>
<fmt:setBundle basename="i18n/header" var="titles_fmt"/>
<fmt:setBundle basename="i18n/genres" var="genres_fmt"/>
<html>

<head>
    <title>
        <fmt:message bundle="${titles_fmt}" key="books"/>
    </title>
</head>
<jsp:include page="../fragments/header.jsp"/>

<c:if test="${not empty param.success}">
<div class="alert alert-success">
    ${param.success}
</div>
</c:if>

<jsp:include page="/WEB-INF/view/fragments/bookFilter.jsp"/>
<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <a href="${pageContext.request.contextPath}/admin/book/add" class="btn btn-info btn-block">
            <fmt:message bundle="${books_fmt}" key="add_new_book"/>
        </a>
    </div>
</div>

<div class="container top-buffer">
    <h2><fmt:message bundle="${titles_fmt}" key="books"/></h2>
    <p class="info-text"><fmt:message bundle="${books_fmt}" key="books_found"/> :
        <c:out value="${totalCount}"/>
    </p>
    <br>


    <table class="table table-hover">
        <tr>
            <th><fmt:message bundle="${books_fmt}" key="image"/></th>
            <th><fmt:message bundle="${books_fmt}" key="table_title"/></th>
            <th><fmt:message bundle="${books_fmt}" key="author"/></th>
            <th><fmt:message bundle="${books_fmt}" key="genre"/></th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>

                <td>
                    <img class="order-image" src='${pageContext.request.contextPath}/static/<c:out value="${book.image}"/>' /></td>

                <td>
                    <c:out value="${book.title}"/>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/admin/books?author_id=<c:out value="${book.author.id}"/>">
                        <c:out value="${book.author.name.concat(' ').concat(book.author.soname)}"/>
                    </a>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/admin/books?genre=<c:out value="${book.genre}"/>">
                        <fmt:message bundle="${genres_fmt}" key="${book.genre}"/>
                    </a>


                <td>
                    <a href="${pageContext.request.contextPath}/admin/book/<c:out value="${book.id}"/>" role="button"
                       class="edit-button btn btn-warning"><fmt:message bundle="${books_fmt}" key="edit_book"/></a>
                </td>


            </tr>
        </c:forEach>
    </table>
</div>

<%--PAGINATOR--%>
<m:display paginParamName="page" totalPages="${totalPages}"/>

<jsp:include page="../fragments/footer.jsp"/>


<%--SCRIPTS--%>
<script type="text/javascript">

    /*init selects*/
    $('.selectpicker').selectpicker();



    let bindEditButtons=(buttons)=>{
        for(let i=0; i<buttons.length; i++){
            let but=buttons[i].id.split('_')[0];
            //alert(but);
            $(buttons[i]).click(()=>{
                $.get({
                    url:"${pageContext.request.contextPath}/admin/book/update"
                })
            });
        }
    };


    /*edit buttons*/
    let editButtons=$('.edit-button');

    bindEditButtons(editButtons);

</script>

