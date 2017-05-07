<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.controller.constants.RegExConst" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/adminAuthors" var="authors_fmt"/>
<fmt:setBundle basename="i18n/header" var="title"/>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message bundle="${title}" key="authors"/>
    </title>
</head>

<jsp:include page="../fragments/header.jsp"/>

    <body>
    <c:if test="${not empty validation_errors}" >
        <div class="alert alert-danger" align="center">
            <td>${validation_errors}</td>
        </div>
    </c:if>

    <c:if test="${not empty param.success_message}" >
        <div class="alert alert-success" align="center">
            <td>${param.success_message}</td>
        </div>
    </c:if>


    <div  class="container top-buffer">
        <h2>
            <fmt:message bundle="${authors_fmt}" key="authors_list"/>
        </h2>
        <br>
        <table class="table table-hover">
            <tr>
                <th><fmt:message bundle="${authors_fmt}" key="title"/></th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach items="${authors}" var="author">
                <tr id='${author.id}_row'>
                    <form action="/admin/authors/update" method="post">
                        <td>
                            <input  hidden name="author_id" value="${author.id}">
                            <div class="form-group row">
                                <div class="col-xs-4">
                                    <input required class="form-control" id='${author.id}_title' name="author_name" value="${author.name}" type="text" pattern="${RegExConst.NAME_SONAME_REG_EX}">
                                </div>
                                <div class="col-xs-4">
                                    <input required class="form-control" id='${author.id}_title' name="author_soname" value="${author.soname}" type="text" pattern="${RegExConst.NAME_SONAME_REG_EX}">
                                </div>
                            </div>
                        </td>
                        <td>
                            <button type="submit" class="update-button btn btn-info" id='${author.id}_update'>
                                <fmt:message bundle="${authors_fmt}" key="update"/>
                            </button>
                        </td>
                    </form>

                    <td>
                        <button class="remove-button btn btn-danger" id='${author.id}_remove'>
                            <fmt:message bundle="${authors_fmt}" key="remove"/>
                        </button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="container top-buffer">
        <h4><fmt:message bundle="${authors_fmt}" key="add_new_author"/></h4>

            <form method="post" action="/admin/authors/add" >
                <%--TODO localize--%>
                <div class="form-group row">
                    <div class="col-xs-3">
                        <input required type="text" class="form-control" name="author_name"
                               placeholder='<fmt:message bundle="${authors_fmt}" key="name"/>'
                               pattern="${RegExConst.NAME_SONAME_REG_EX}"/>
                    </div>

                    <div class="col-xs-3">
                        <input required type="text" class="form-control" name="author_soname"
                               placeholder='<fmt:message bundle="${authors_fmt}" key="soname"/>'
                               pattern="${RegExConst.NAME_SONAME_REG_EX}"/>
                    </div>
                    <button type="submit" class="btn btn-info">
                        <fmt:message bundle="${authors_fmt}" key="add"/>
                    </button>
                </div>



            </form>

    </div>

    </body>

</html>

<script type="text/javascript">
    let bindRemoveButtons=(buttons)=>{
        for(let i=0; i<buttons.length; i++){
            let authorId=buttons[i].id.split('_')[0];
            //alert(but);
            $(buttons[i]).click(()=>{
                $.post({
                    data:{
                        author_id:authorId
                    },
                    url:"/admin/authors/remove",
                    success:()=>{
                        $('#'+authorId+'_row').remove();
                        alertify.success('<fmt:message bundle="${authors_fmt}" key="author_was_removed"/>');
                    },
                    error:()=>{
                        alertify.error('<fmt:message bundle="${authors_fmt}" key="author_not_removed"/>');
                    }
                })
            });
        }
    };


    let removeButtons=$('.remove-button');
    bindRemoveButtons(removeButtons);

</script>
