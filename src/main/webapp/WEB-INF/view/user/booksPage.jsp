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
    <jsp:include page="/WEB-INF/view/fragments/header.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
</head>

<%--TODO replace explicit values comparing via jstl el by JS--%>

<%--FILTERS--%>
<div class="row col-lg-12 center-block top-buffer">
    <form class="form-inline" method="get">
        <div class="form-group">
            <label class="mr-sm-2" >Title</label>
            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
                   name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>
        </div>


        <%--author--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Author</label>
            <select name="author_id" class="selectpicker" data-width="130px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${authors}" var="author">
                    <option value="<c:out value="${author.id}"/>"
                        <c:if test="${author.id==param.author_id}">
                            selected
                        </c:if>
                    >
                        <c:out value="${author.name}"/> <c:out value="${author.soname}"/></option>
                </c:forEach>

            </select>
        </div>

        <%--genre--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Genre</label>
            <select name="genre" class="selectpicker" data-width="130px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${genres}" var="genre">
                    <option value="<c:out value="${genre}"/>"
                            <c:if test="${genre==param.genre}">
                                selected
                            </c:if>
                    >
                        <c:out value="${genre}"/> </option>
                </c:forEach>

            </select>
        </div>

        <%--language--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Language</label>
            <select name="language" class="selectpicker" data-width="75px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${languages}" var="language">
                    <option value="<c:out value="${language}"/>"
                        <c:if test="${language==param.language}">
                            selected
                        </c:if>
                    >
                        <c:out value="${language}"/> </option>
                </c:forEach>

            </select>
        </div>

        <%--publishers--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Publisher</label>
            <select name="publisher_id" class="selectpicker" data-width="130px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${publishers}" var="publisher">
                    <option value="<c:out value="${publisher.id}"/>"
                        <c:if test="${publisher.id==param.publisher_id}">
                            selected
                        </c:if>
                    >
                        <c:out value="${publisher.title}"/> </option>
                </c:forEach>

            </select>
        </div>

        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Books per page</label>

            <select name="limit" class="selectpicker" data-width="75px">
                <%--set limit value or default--%>
                <c:if test="${not empty param.limit}">
                    <c:set var="limit" value="${param.limit}"/>
                </c:if>

                <c:if test="${empty param.limit}">
                    <c:set var="limit" value="${defLimit}"/>
                </c:if>
                <%--set limitation values--%>
                <c:set var="pages" value="${[2,5,10,15,20,30]}"/>

                <c:forEach var="amount" items="${pages}">
                    <option value="${amount}"
                        <c:if test="${amount==limit}">
                            selected
                        </c:if>
                    >${amount}</option>
                </c:forEach>

            </select>
        </div>

        <button type="submit" class="btn btn-info right">Find books</button>

    </form>

    <form method="get">
        <button type="submit" class="btn btn-warning right">Clear filter</button>
    </form>

</div>
<%--FILTERS END--%>


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
<jsp:include page="/WEB-INF/view/fragments/footer.jsp" />

<%--SCRIPTS--%>
<script>
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
                    success: function(){
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

    /*init selects*/
    $('.selectpicker').selectpicker();

    /*order home buttons*/
    let orderHomeButtons=$('.order-button-home');
    /*order library buttons*/
    let orderReadHallButtons=$('.order-button-read');

    bindButtons(orderHomeButtons,'HOME');
    bindButtons(orderReadHallButtons,'LIBRARY');

</script>
