<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.controller.constants.RegExConst" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Publisher</title>
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
        <h2>Publishers list</h2>
        <br>
        <table class="table table-hover">
            <tr>
                <th>Title</th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach items="${publishers}" var="author">
                <tr id='${author.id}_row'>
                    <form action="/admin/publishers/update" method="post">
                        <td>
                            <input  hidden name="publisher_id" value="${author.id}">
                            <input class="form-control" id='${author.id}_title' name="publisher_title" value="${author.title}" type="text" pattern="${RegExConst.PUBLISHER_TITLE_REG_EX}">
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
        <h4>Add new publisher</h4>

        <div class="row">

            <form method="post" action="/admin/publishers/add" >
                <%--TODO localize--%>
                <div class="col-xs-6">
                    <input type="text" class="form-control" name="publisher_title" placeholder="publisher title" pattern="${RegExConst.PUBLISHER_TITLE_REG_EX}"/>
                </div>
                <button type="submit" class="btn btn-info">add</button>

            </form>

        </div>
    </div>

    </body>
    <script type="text/javascript">
        let bindRemoveButtons=(buttons)=>{
            for(let i=0; i<buttons.length; i++){
                let publId=buttons[i].id.split('_')[0];
                //alert(but);
                $(buttons[i]).click(()=>{
                    $.post({
                        data:{
                            publisher_id:publId
                        },
                        url:"/admin/publishers/remove",
                        success:()=>{
                            $('#'+publId+'_row').remove();
                            alertify.success("Publisher removed");
                        },
                        error:()=>{
                            alertify.error("Publisher can not be removed");
                        }
                    })
                });
            }
        };


        let removeButtons=$('.remove-button');
        bindRemoveButtons(removeButtons);

    </script>
</body>
</html>
