<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="/WEB-INF/view/errorPage.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/footer" var="footer"/>


<br>
<br>
        <footer class="navbar-default navbar-fixed-bottom">
            <div class="container-fluid footer" align="center">
                <span><fmt:message bundle="${footer}" key="label"/>&reg; - 2017</span>
            </div>
        </footer>
    </body>
</html>