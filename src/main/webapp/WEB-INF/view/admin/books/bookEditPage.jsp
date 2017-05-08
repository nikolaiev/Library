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
     <form method="post" action="${pageContext.request.contextPath}/admin/book/update" enctype="multipart/form-data">

         <input type="hidden" name="id" value="${book.id}"/>

         <div class="form-group">
             <label>
                 <fmt:message bundle="${page_fmt}" key="book_title"/>
             </label>
             <label class="label label-info">${book.title}</label>
             <input type="text" class="form-control" name="title"
                    placeholder='<fmt:message bundle="${page_fmt}" key="book_title_placeholder"/>'/>
         </div>

         <div class="form-group">
             <label>
                 <fmt:message bundle="${page_fmt}" key="book_image"/>
             </label>
             <br>
             <img class="img-thumbnail" style="height: 150px;" src='${pageContext.request.contextPath}/static/<c:out value="${book.image}"/>' /></td>
             <br>
             <br>
            <input class="form-control-file" type="file" name="book_image" accept="image/jpeg,image/png"/>
         </div>


         <div class="form-group">

             <label>
                 <fmt:message bundle="${page_fmt}" key="book_count"/>
             </label>
             <label class="label label-info">${book.count}</label>

             <input  class="form-control" type="number" name="count" min="0" max="500"/>
         </div>

         <div class="form-group">

             <label>
                 <fmt:message bundle="${page_fmt}" key="publish_date"/>
             </label>
             <label class="label label-info">${f:formatInstantToLocale(book.instant, 'dd.MM.yyyy')}</label>

             <input   class="form-control" type="date" name="publish_date" min="1900-01-01" max="2017-12-31"/>
         </div>


         <div class="form-group">

             <label>
                 <fmt:message bundle="${page_fmt}" key="book_author"/>
             </label>

             <label class="label label-info">
                <c:out value="${book.author.name}"/> <c:out value="${book.author.soname}"/>
             </label>

             <select class="form-control" name="author_id" >
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

             <label class="label label-info">
                 ${book.genre}
             </label>
               <select class="form-control" name="genre" >
                   <option value="" disabled selected>
                       <fmt:message bundle="${page_fmt}" key="default"/>
                   </option>

                   <c:forEach items="${genres}" var="genre">
                       <option value="<c:out value="${genre}"/>" >
                           <fmt:message bundle="${genres_fmt}" key="${genre}"/>
                       </option>
                   </c:forEach>

               </select>
         </div>

         <div class="form-group">

             <label>
                 <fmt:message bundle="${page_fmt}" key="book_language"/>
             </label>

             <label class="label label-info">
                 ${book.language}
             </label>


           <select class="form-control" name="language" >
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

             <label>
                 <fmt:message bundle="${page_fmt}" key="book_publisher"/>
             </label>

             <label class="label label-info">
                 ${book.publisher.title}
             </label>



            <select class="form-control" name="publisher_id" >
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
