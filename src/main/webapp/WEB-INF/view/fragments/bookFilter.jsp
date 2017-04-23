
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mytags.com/jsp/mytags" prefix="m" %>


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
