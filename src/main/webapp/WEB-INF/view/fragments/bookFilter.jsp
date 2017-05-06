
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mytags.com/jsp/mytags" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/bookFilter" var="book"/>


<%--FILTERS--%>
<div class="row col-lg-12 center-block alert alert-info">
    <form class="form-inline" method="get">
        <div class="form-group">
            <label class="mr-sm-2" >
                <fmt:message bundle="${book}" key="title"/>
            </label>
            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
                   name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>
        </div>


        <%--author--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" ><fmt:message bundle="${book}" key="author"/></label>
            <select name="author_id" class="selectpicker" data-width="170px">
                <option value="" disabled selected><fmt:message bundle="${book}" key="all"/></option>

                <c:forEach items="${authors}" var="publ">
                    <option value="<c:out value="${publ.id}"/>"
                            <c:if test="${publ.id==param.author_id}">
                                selected
                            </c:if>
                    >
                        <c:out value="${publ.name}"/> <c:out value="${publ.soname}"/></option>
                </c:forEach>

            </select>
        </div>

        <%--genre--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" ><fmt:message bundle="${book}" key="genre"/></label>
            <select name="genre" class="selectpicker" data-width="140px">
                <option value="" disabled selected><fmt:message bundle="${book}" key="all"/></option>

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
            <label class="mr-sm-2" ><fmt:message bundle="${book}" key="lang"/></label>
            <select name="language" class="selectpicker" data-width="75px">
                <option value="" disabled selected><fmt:message bundle="${book}" key="all"/></option>

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
            <label class="mr-sm-2" ><fmt:message bundle="${book}" key="publisher"/></label>
            <select name="publisher_id" class="selectpicker" data-width="130px">
                <option value="" disabled selected><fmt:message bundle="${book}" key="all"/></option>

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
            <label class="mr-sm-2" ><fmt:message bundle="${book}" key="books_per_page"/></label>

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

        <button type="submit" class="btn btn-success right"><fmt:message bundle="${book}" key="filter"/></button>

    </form>

    <form method="get">
        <button type="submit" class="btn btn-warning right"><fmt:message bundle="${book}" key="clear_filter"/></button>
    </form>

</div>
