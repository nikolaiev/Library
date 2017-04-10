<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 03.04.17
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Books</title>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
</head>
<body>
    Books list
    <br>
    <table>
    <c:forEach items="${books}" var="book">
        <tr>

            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.author.soname}"/></td>
            <td><c:out value="${book.genre}"/></td>
            <td>
                <button id="<c:out value="${book.id}"/>" class="buy-button">
                    add to card!
                </button>
            </td>

        </tr>
    </c:forEach>
    </table>




</body>
<script>
    var buyButtons=$('.buy-button');
    for(let i=0;i<buyButtons.length;i++){
        let but=buyButtons[i].id;
        $('#'+but).click(()=>{
            $.ajax({
                type: "POST",
                url: "/user/books/addToOrderList",
                data: {id:but},
                success: function(){console.log('Book was successfuly added')}
            });
        })
    }


</script>
</html>
