<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/paginator.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.04.17
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Add new Book</title>
</head>
<jsp:include page="../fragments/header.jsp"/>

<c:if test="${not empty param.success}">
<div class="alert alert-success">
    <strong>Success!</strong> ${param.success}
</div>
</c:if>

<jsp:include page="/WEB-INF/view/fragments/bookFilter.jsp"/>
<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <a href="${pageContext.request.contextPath}/admin/book/add" class="btn btn-info btn-block"> add new book</a>
    </div>
</div>

<div class="container top-buffer">
    <h2>Books library</h2>
    <p class="info-text">Books found :
        <c:out value="${totalCount}"/>
    </p>
    <br>


    <table class="table table-hover">
        <tr>
            <th>image</th>
            <th>title</th>
            <th>author</th>
            <th>genre</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>

                <td><img class="order-image" src='${pageContext.request.contextPath}/static/<c:out value="${book.image}"/>' /></td>
                    <%--<td><c:out value="${book.id}"/></td>--%>
                <td>
                    <a href="${pageContext.request.contextPath}/user/book/<c:out value="${book.id}"/>">
                        <c:out value="${book.title}"/>
                    </a>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/user/books?author_id=<c:out value="${book.author.id}"/>">
                        <c:out value="${book.author.name.concat(' ').concat(book.author.soname)}"/>
                    </a>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/user/books?genre=<c:out value="${book.genre}"/>">
                        <c:out value="${book.genre}"/>
                    </a>


                <td>
                    <a href="${pageContext.request.contextPath}/admin/book?id=<c:out value="${book.id}"/>" role="button" class="edit-button btn btn-warning">Edit book</a>
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
                    //TODO rewrite
                    url:"
                })
            });
        }
    };


    /*edit buttons*/
    let editButtons=$('.edit-button');

    bindEditButtons(editButtons);

</script>

