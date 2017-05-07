<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/orderFilter" var="order_filter_fmt"/>
<fmt:setBundle basename="i18n/orderType" var="order_type_fmt"/>
<fmt:setBundle basename="i18n/orderStatus" var="order_status_fmt"/>


<%--FILTERS--%>
<div class="row col-lg-1"></div>
<div class="row col-lg-11 center-block top-buffer alert alert-info">
    <form class="form-inline" method="get">
        <c:if test="${sessionScope.userRole eq 'ADMIN'}">
            <div class="form-group">
                <label class="mr-sm-2" >
                    <fmt:message bundle="${order_filter_fmt}" key="user"/>
                </label>
                <input type="number" class="form-control mb-2 mr-sm-2 mb-sm-0"
                       min="1"
                       name="user_id" placeholder='<fmt:message bundle="${order_filter_fmt}" key="user_id"/>' value="<c:out value="${param.user_id}"/>"/>
            </div>
        </c:if>

        <div class="form-group">
            <label class="mr-sm-2" ><fmt:message bundle="${order_filter_fmt}" key="title"/></label>
            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
                   name="title" placeholder='<fmt:message bundle="${order_filter_fmt}" key="book_title"/>' value="<c:out value="${param.title}"/>"/>
        </div>

        <%--author--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >
                <fmt:message bundle="${order_filter_fmt}" key="before_date"/>
            </label>
            <input type="date" id="bdate" name="before_date"/>
        </div>

        <%--genre--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >
                <fmt:message bundle="${order_filter_fmt}" key="type"/>
            </label>
            <select name="type" class="selectpicker" data-width="100px">
                <option value="" disabled selected>
                    <fmt:message bundle="${order_type_fmt}" key="all"/>
                </option>

                <c:forEach items="${types}" var="type">
                    <option value="<c:out value="${type}"/>"
                            <c:if test="${type==param.type}">
                                selected
                            </c:if>
                    >
                        <fmt:message bundle="${order_type_fmt}" key="${type}"/>
                    </option>
                </c:forEach>

            </select>
        </div>

        <%--language--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >
                <fmt:message bundle="${order_filter_fmt}" key="status"/>
            </label>
            <select name="status" class="selectpicker" data-width="150px">
                <option value="" disabled selected>
                    <fmt:message bundle="${order_status_fmt}" key="all"/>
                </option>

                <c:forEach items="${statuses}" var="status">
                    <option value="<c:out value="${status}"/>"
                            <c:if test="${status==param.status}">
                                selected
                            </c:if>
                    >
                        <fmt:message bundle="${order_status_fmt}" key="${status}"/>
                    </option>
                </c:forEach>

            </select>
        </div>

        <div class="form-group left-buffer">
            <label class="mr-sm-2" >
                <fmt:message bundle="${order_filter_fmt}" key="items_per_page"/>
            </label>

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
        <br>
        <br>
        <br>
        <button type="submit" class="btn btn-info right">
            <fmt:message bundle="${order_filter_fmt}" key="find_orders"/>
        </button>

    </form>
    <br>
    <br>

    <form method="get">
        <button type="submit" class="btn btn-warning right">
            <fmt:message bundle="${order_filter_fmt}" key="clear_filter"/>
        </button>
    </form>

</div>
<%--FILTERS END--%>