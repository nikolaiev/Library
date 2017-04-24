<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 23.04.17
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--FILTERS--%>
<div class="row col-lg-1"></div>
<div class="row col-lg-11 center-block top-buffer alert alert-info">
    <form class="form-inline" method="get">
        <div class="form-group">
            <label class="mr-sm-2" >Title</label>
            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
                   name="title" placeholder="book title" value="<c:out value="${param.title}"/>"/>
        </div>

        <%--author--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Before date</label>
            <input type="date" id="bdate" name="before_date"/>
        </div>

        <%--genre--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Type</label>
            <select name="type" class="selectpicker" data-width="100px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${types}" var="type">
                    <option value="<c:out value="${type}"/>"
                            <c:if test="${type==param.type}">
                                selected
                            </c:if>
                    >
                        <c:out value="${type}"/> </option>
                </c:forEach>

            </select>
        </div>

        <%--language--%>
        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Status</label>
            <select name="status" class="selectpicker" data-width="150px">
                <option value="" disabled selected>All</option>

                <c:forEach items="${statuses}" var="status">
                    <option value="<c:out value="${status}"/>"
                            <c:if test="${status==param.status}">
                                selected
                            </c:if>
                    >
                        <c:out value="${status}"/> </option>
                </c:forEach>

            </select>
        </div>

        <div class="form-group left-buffer">
            <label class="mr-sm-2" >Items per page</label>

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

        <button type="submit" class="btn btn-info right">Find orders</button>

    </form>

    <form method="get">
        <button type="submit" class="btn btn-warning right">Clear filter</button>
    </form>

</div>
<%--FILTERS END--%>