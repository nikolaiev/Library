<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.controller.constants.RegExConst" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/adminPublishers" var="page_fmt"/>
<fmt:setBundle basename="i18n/header" var="titles_fmt"/>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message bundle="${titles_fmt}" key="publishers"/>
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
        <h2><fmt:message bundle="${page_fmt}" key="publishers_list"/></h2>
        <br>
        <table class="table table-hover">
            <tr>
                <th><fmt:message bundle="${page_fmt}" key="title"/></th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach items="${publishers}" var="publ">
                <tr id='${publ.id}_row'>
                    <form action="/admin/publishers/update" method="post">
                        <td>
                            <input  hidden name="publisher_id" value="${publ.id}">
                            <input required class="form-control" id='${publ.id}_title' name="publisher_title" value="${publ.title}" type="text" pattern="${RegExConst.PUBLISHER_TITLE_REG_EX}">
                        </td>
                        <td>
                            <button type="submit" class="update-button btn btn-info" id='${publ.id}_update'>
                                <fmt:message bundle="${page_fmt}" key="update"/>
                            </button>
                        </td>
                    </form>

                    <td>
                        <button class="remove-button btn btn-danger" id='${publ.id}_remove'>
                            <fmt:message bundle="${page_fmt}" key="remove"/>
                        </button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="container top-buffer">
        <h4>
            <fmt:message bundle="${page_fmt}" key="add_new_publisher"/>
        </h4>

        <div class="row">

            <form method="post" action="/admin/publishers/add" >

                <div class="col-xs-6">
                    <input required type="text" class="form-control" name="publisher_title"
                           placeholder='<fmt:message bundle="${page_fmt}" key="title_placeholder"/>'
                           pattern="${RegExConst.PUBLISHER_TITLE_REG_EX}"/>
                </div>
                <button type="submit" class="btn btn-info">
                    <fmt:message bundle="${page_fmt}" key="add"/>
                </button>

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
                            alertify.success('<fmt:message bundle="${page_fmt}" key="publ_removed"/>');
                        },
                        error:()=>{
                            alertify.error('<fmt:message bundle="${page_fmt}" key="publ_not_removed"/>');
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
