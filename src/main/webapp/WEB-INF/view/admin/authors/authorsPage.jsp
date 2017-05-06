<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.controller.constants.RegExConst" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Authors</title>
</head>

<jsp:include page="../fragments/header.jsp"/>

    <body>
    <c:if test="${not empty validation_errors}" >
        <div class="alert alert-danger" align="center">
            <strong>Error!</strong>
            <td>${validation_errors}</td>
        </div>
    </c:if>

    <c:if test="${not empty param.success_message}" >
        <div class="alert alert-success" align="center">
            <td>${param.success_message}</td>
        </div>
    </c:if>


    <div  class="container top-buffer">
        <h2>Authors list</h2>
        <br>
        <table class="table table-hover">
            <tr>
                <th>Title</th>
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
                            <button type="submit" class="update-button btn btn-info" id='${author.id}_update'>update</button>
                        </td>
                    </form>

                    <td>
                        <button class="remove-button btn btn-danger" id='${author.id}_remove'>remove</button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="container top-buffer">
        <h4>Add new author</h4>

            <form method="post" action="/admin/authors/add" >
                <%--TODO localize--%>
                <div class="form-group row">
                    <div class="col-xs-3">
                        <input required type="text" class="form-control" name="author_name" placeholder="name" pattern="${RegExConst.NAME_SONAME_REG_EX}"/>
                    </div>

                    <div class="col-xs-3">
                        <input required type="text" class="form-control" name="author_soname" placeholder="soname" pattern="${RegExConst.NAME_SONAME_REG_EX}"/>
                    </div>
                    <button type="submit" class="btn btn-info">add</button>
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
                        alertify.success("Author was removed");
                    },
                    error:()=>{
                        alertify.error("Author can not be removed");
                    }
                })
            });
        }
    };


    let removeButtons=$('.remove-button');
    bindRemoveButtons(removeButtons);

</script>
