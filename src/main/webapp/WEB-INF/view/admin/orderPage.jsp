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
</head>
<body>
Books list
<br>
    <table>
        <c:out value="${requestScope.orders}"/>

        <c:forEach items="${orders}" var="order">
            <tr>

            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><button class="return-button" id='<c:out value="${order.id}"/>'>returned</button></td>


            </tr>
        </c:forEach>
    </table>
</body>
<script>

    var buyButtons=$('.return-button');
    for(let i=0;i<buyButtons.length;i++){
        let but=buyButtons[i].id;
        $(buyButtons[i]).click(()=>{

            $.ajax({
                type: "POST",
                url: "/admin/orders/update",
                data: {id:but}
            });
        })
    }
</script>
</html>
