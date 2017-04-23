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
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="/WEB-INF/view/fragments/bookFilter.jsp"/>

   <%-- <form method="post" action="${pageContext.request.contextPath}/admin/books/add" enctype="multipart/form-data">
        <input type="text"  required name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>
        <br>
        <br>
        <input type="file" required name="book_image" accept="image/jpeg,image/png"/>
        <br>
        <br>
        <input type="number" required name="count" min="0" max="500"/>
        <br>
        <br>
        &lt;%&ndash;TODO make required&ndash;%&gt;
        <input type="date" name="publish_date" min="1900-01-01"/>
        <br>
        <br>

        &lt;%&ndash;author&ndash;%&gt;
        <select name="author_id" required>
            <option value="" disabled selected>All authors</option>

            <c:forEach items="${authors}" var="author">
                <option value="<c:out value="${author.id}"/>">
                    <c:out value="${author.name}"/> <c:out value="${author.soname}"/></option>
            </c:forEach>

        </select>
        <br>
        <br>

        &lt;%&ndash;genre&ndash;%&gt;

        <select name="genre" required>
            <option value="" disabled selected>All genres</option>

            <c:forEach items="${genres}" var="genre">
                <option value="<c:out value="${genre}"/>">
                    <c:out value="${genre}"/> </option>
            </c:forEach>

        </select>
        <br>
        <br>
        &lt;%&ndash;language&ndash;%&gt;
        <select name="language" required>
            <option value="" disabled selected>All languages</option>

            <c:forEach items="${languages}" var="language">
                <option value="<c:out value="${language}"/>">
                    <c:out value="${language}"/> </option>
            </c:forEach>

        </select>
        <br>
        <br>
        &lt;%&ndash;publishers&ndash;%&gt;
        <select name="publisher_id" required>
            <option value="" disabled selected>All publishers</option>

            <c:forEach items="${publishers}" var="publisher">
                <option value="<c:out value="${publisher.id}"/>">
                    <c:out value="${publisher.title}"/> </option>
            </c:forEach>

        </select>
        <br>
        <br>
        <br>
        <br>
        <input type="submit" value="create book"/>
    </form>
--%>

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
                    <button id="<c:out value="${book.id}_edit"/>" class="edit-button btn btn-warning">
                        Edit book
                    </button>
                </td>


            </tr>
        </c:forEach>
    </table>
</div>

<%--PAGINATOR--%>
<m:display paginParamName="page" totalPages="${totalPages}"/>

<jsp:include page="fragments/footer.jsp"/>


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
                    url:"${pageContext.request.contextPath}/admin/book"+"?id="+but
                })
            });
        }
    };


    /*edit buttons*/
    let editButtons=$('.edit-button');

    bindEditButtons(editButtons);

</script>

