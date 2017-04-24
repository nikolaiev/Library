<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/dateFormatter.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 23.04.17
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>

<jsp:include page="../fragments/header.jsp"/>

<c:if test="${not empty validation_errors}">
    <div class="alert alert-danger">
        <strong>Error!</strong> ${validation_errors}
    </div>
</c:if>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
     <form method="post" action="${pageContext.request.contextPath}/admin/book/update" enctype="multipart/form-data">
         <input type="hidden" name="id" value="${param.id}"/>

         <div class="form-group">
             <label>Book title</label>
             <label class="label label-info">${book.title}</label>
             <input type="text" class="form-control" name="title" placeholder="book title"/>
             <%--<small class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
         </div>



         <div class="form-group">
             <label>Book image</label>
             <br>
             <img class="img-thumbnail" style="height: 150px;" src='${pageContext.request.contextPath}/static/<c:out value="${book.image}"/>' /></td>
             <br>
             <br>
            <input class="form-control-file" type="file" name="book_image" accept="image/jpeg,image/png"/>
         </div>


         <div class="form-group">

             <label>Book count</label>
             <label class="label label-info">${book.count}</label>

             <input  class="form-control" type="number" name="count" min="0" max="500"/>
         </div>

         <div class="form-group">

             <label>Publishing date</label>
             <label class="label label-info">Date is: ${f:formatLocalDate(book.date, 'dd.MM.yyyy')}</label>

             <input   class="form-control" type="date" name="publish_date" min="1900-01-01" max="2017-12-31"/>
         </div>


         <div class="form-group">

             <label>Author</label>

             <label class="label label-info">
                <c:out value="${book.author.name}"/> <c:out value="${book.author.soname}"/>
             </label>

             <select class="form-control" name="author_id" >
                   <option value="" disabled selected>Default</option>

                   <c:forEach items="${authors}" var="author">

                       <option value="<c:out value="${author.id}"/>">
                           <c:out value="${author.name}"/> <c:out value="${author.soname}"/>
                       </option>
                   </c:forEach>

               </select>
         </div>

         <div class="form-group">

             <label>Genre</label>

             <label class="label label-info">
                 ${book.genre}
             </label>
               <select class="form-control" name="genre" >
                   <option value="" disabled selected>Default</option>

                   <c:forEach items="${genres}" var="genre">
                       <option value="<c:out value="${genre}"/>" >
                           <c:out value="${genre}"/> </option>
                   </c:forEach>

               </select>
         </div>

         <div class="form-group">

             <label>Language</label>

             <label class="label label-info">
                 ${book.language}
             </label>


           <select class="form-control" name="language" >
               <option value="" disabled selected>Default</option>

               <c:forEach items="${languages}" var="language">
                   <option value="<c:out value="${language}"/>">
                       <c:out value="${language}"/> </option>
               </c:forEach>

           </select>
         </div>


         <div class="form-group">

             <label>Publisher</label>

             <label class="label label-info">
                 ${book.publisher.title}
             </label>



            <select class="form-control" name="publisher_id" >
               <option value="" disabled selected>Default</option>

               <c:forEach items="${publishers}" var="publisher">
                   <option value="<c:out value="${publisher.id}"/>">
                       <c:out value="${publisher.title}"/> </option>
               </c:forEach>

            </select>
         </div>
         <div class="text-center">
           <input type="submit" class="btn btn-primary" value="save changes"/>
         </div>
       </form>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
