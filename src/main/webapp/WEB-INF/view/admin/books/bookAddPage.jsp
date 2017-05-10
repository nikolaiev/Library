<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/dateFormatter.tld" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/adminAddEditBook" var="page_fmt"/>
<fmt:setBundle basename="i18n/genres" var="genres_fmt"/>
<fmt:setBundle basename="i18n/header" var="titles_fmt"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <fmt:message bundle="${titles_fmt}" key="books"/>
    </title>
</head>

<jsp:include page="../fragments/header.jsp"/>

<c:if test="${not empty validation_errors}">
<div class="alert alert-danger">
    ${validation_errors}
</div>
</c:if>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <form method="post" action="${pageContext.request.contextPath}/admin/book/add" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${param.id}"/>

            <div class="form-group">
                <label>
                    <fmt:message bundle="${page_fmt}" key="book_title"/>
                </label>
                <input required type="text" class="form-control" name="title" placeholder="book title"/>
            </div>

            <div class="form-group">
                <label>
                    <fmt:message bundle="${page_fmt}" key="book_image"/>
                </label>
                <br>
                <br>
                <input required class="form-control-file" type="file" name="book_image" accept="image/jpeg,image/png"/>
            </div>


            <div class="form-group">

                <label>
                    <fmt:message bundle="${page_fmt}" key="book_count"/>
                </label>

                <input required class="form-control" type="number" name="count" min="0" max="500"/>
            </div>

            <div class="form-group">

                <label>
                    <fmt:message bundle="${page_fmt}" key="publish_date"/>
                </label>

                <input required  class="form-control" type="date" name="publish_date" min="1900-01-01" max="2017-12-31"/>
            </div>


            <div class="form-group">

                <label>
                    <fmt:message bundle="${page_fmt}" key="book_author"/>
                </label>

                <select required class="form-control" name="author_id" >
                    <option value="" disabled selected>
                        <fmt:message bundle="${page_fmt}" key="default"/>
                    </option>

                    <c:forEach items="${authors}" var="author">

                        <option value="<c:out value="${author.id}"/>">
                            <c:out value="${author.name}"/> <c:out value="${author.soname}"/>
                        </option>
                    </c:forEach>

                </select>
            </div>

            <div class="form-group">

                <label>
                    <fmt:message bundle="${page_fmt}" key="book_genre"/>
                </label>

                <select  required class="form-control" name="genre" >
                    <option value="" disabled selected>
                        <fmt:message bundle="${page_fmt}" key="book_genre"/>
                    </option>

                    <c:forEach items="${genres}" var="genre">
                        <option value="<c:out value="${genre}"/>" >
                        <fmt:message bundle="${genres_fmt}" key="${genre}"/>
                    </c:forEach>

                </select>
            </div>

            <div class="form-group">

                <label>
                    <fmt:message bundle="${page_fmt}" key="book_language"/>
                </label>

                <select  required class="form-control" name="language" >
                    <option value="" disabled selected>
                        <fmt:message bundle="${page_fmt}" key="default"/>
                    </option>

                    <c:forEach items="${languages}" var="language">
                        <option value="<c:out value="${language}"/>">
                            <c:out value="${language}"/> </option>
                    </c:forEach>

                </select>
            </div>


            <div class="form-group">

                <label>Publisher</label>

                <select required class="form-control" name="publisher_id" >
                    <option value="" disabled selected>
                        <fmt:message bundle="${page_fmt}" key="default"/>
                    </option>

                    <c:forEach items="${publishers}" var="publisher">
                        <option value="<c:out value="${publisher.id}"/>">
                            <c:out value="${publisher.title}"/> </option>
                    </c:forEach>

                </select>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary"
                       value='<fmt:message bundle="${page_fmt}" key="submit_changes"/>'/>

            </div>
        </form>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
