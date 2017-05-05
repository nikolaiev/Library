<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.04.17
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publisher</title>
</head>

<jsp:include page="../fragments/header.jsp"/>

<body>
<div  class="container top-buffer">
    <h2>Publishers list</h2>
    <br>
    <table class="table table-hover">
        <tr>
            <th>Title</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${publishers}" var="publ">
            <tr id='${publ.id}_row'>
                <td><c:out value="${publ.title}"/></td>
                <td>
                    <button class="edit-button btn btn-info" id='${publ.id}_edit'>edit</button>
                </td>

                <td>
                    <button class="remove-button btn btn-danger" id='${publ.id}_remove'>remove</button>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>

</body>
<script type="text/javascript">

    //TODO do update
    let bindEditButtons=(buttons)=>{
        for(let i=0; i<buttons.length; i++){
            let publisherId=buttons[i].id.split('_')[0];

            $(buttons[i]).click(()=>{
                $.get({
                    data:{
                        publisher_id:publisherId
                    },
                    url:"/admin/publishers/update"
                })
            });
        }
    };

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


    /*edit buttons*/
    let editButtons=$('.edit-button');
    let removeButtons=$('.remove-button');

    bindRemoveButtons(removeButtons);
    bindEditButtons(editButtons);
</script>
</html>
