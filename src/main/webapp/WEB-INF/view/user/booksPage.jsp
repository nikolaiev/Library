<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 03.04.17
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mytags.com/jsp/mytags" prefix="m" %>

<html>
<head>
    <title>Books</title>
</head>
<jsp:include page="/WEB-INF/view/user/fragments/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css"/>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>



<%--FILTERS END--%>
<jsp:include page="/WEB-INF/view/fragments/bookFilter.jsp"/>

<%--BOOKS TABLE--%>
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
                <button id="<c:out value="${book.id}_home"/>" class="order-button-home btn btn-default">
                    Take home
                </button>
            </td>

            <td>
                <button id="<c:out value="${book.id}_read"/>" class="order-button-read btn btn-info">
                    Take read hall
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
                        alertify.success('Book was successfully added');
                    },
                    error:function () {
                        alertify.error("Can't request book");
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
