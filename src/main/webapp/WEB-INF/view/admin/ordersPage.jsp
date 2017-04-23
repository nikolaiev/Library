<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.04.17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <!--alertify script-->
    <!-- JavaScript -->
    <script src="//cdn.jsdelivr.net/alertifyjs/1.9.0/alertify.min.js"></script>

    <!-- CSS -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.9.0/css/alertify.min.css"/>
    <!-- Default theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.9.0/css/themes/default.min.css"/>

</head>

<jsp:include page="fragments/header.jsp"/>

Orders list
<br>
    <table>
        <c:forEach items="${orders}" var="order">
            <tr>

            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><button class="return-button" id='<c:out value="${order.id}"/>'>returned</button></td>


            </tr>
        </c:forEach>
    </table>

<jsp:include page="fragments/footer.jsp"/>

<script type="text/javascript">

    var returnButtons=$('.return-button');
    for(let i=0; i<returnButtons.length; i++){
        let but=returnButtons[i].id;
        $(returnButtons[i]).click(()=>{

            $.ajax({
                type: "POST",
                url: "/admin/orders/update",
                data: {id:but},
                success:()=>{alertify.success('Status changed')}
            });
        })
    }
</script>
