<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.controller.constants.RegExConst" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n/login" var="log"/>



<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <%--CSS--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"/>

    <%--JS--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
    <script src="${pageContext.request.contextPath}/js/login.js" ></script>



</head>

<body>

<div class="container buffer-bottom">
    <a href="?locale=UA" class="local">ua</a>
    <a href="?locale=RUS" class="local">ru</a>
    <a href="?locale=ENG" class="local">eng</a>
</div>

<c:if test="${not empty validation_errors}" >
    <div class="alert alert-danger" align="center">
        <strong>Error!</strong>
        <td>${validation_errors}</td>
    </div>
</c:if>

<c:if test="${not empty param.success_message}" >
    <div class="alert alert-success" align="center">
        <strong>Log in now!</strong>
        <td>${param.success_message}</td>
    </div>
</c:if>



<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#"
                                <c:if test="${not isRegistrationAttempt}">
                                    class="active"
                                </c:if>
                               id="login-form-link"
                            >
                                <fmt:message key="login" bundle="${log}"/>
                            </a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link"
                                <c:if test="${isRegistrationAttempt}">
                                    class="active"
                                </c:if>

                            >
                                <fmt:message key="register" bundle="${log}"/>
                            </a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <%--Login--%>
                            <form id="login-form" action="${pageContext.request.contextPath}/login" method="post" role="form"
                                  <c:if test="${isRegistrationAttempt}">
                                    hidden
                                  </c:if>
                            >
                                <div class="form-group">

                                    <input pattern="${RegExConst.LOGIN_REG_EX}" required type="text" name="login"
                                           id="username" tabindex="1" class="form-control" placeholder=<fmt:message key="login_placeholder" bundle="${log}"/>  value="${param.login}">
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="password" id="password" tabindex="2" class="form-control" placeholder=<fmt:message key="password_placeholder" bundle="${log}"/>>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value=<fmt:message key="submit_login" bundle="${log}"/>>
                                        </div>
                                    </div>
                                </div>

                            </form>

                            <%--Registration--%>

                            <form id="register-form" action="${pageContext.request.contextPath}/register" method="post" role="form"
                                <c:if test="${not isRegistrationAttempt}">
                                    hidden
                                </c:if>
                            >
                                <div class="form-group">
                                    <input pattern="${RegExConst.NAME_SONAME_REG_EX}" required type="text" name="name" id="name_reg" tabindex="1" class="form-control" placeholder=<fmt:message key="name" bundle="${log}"/> value="${param.name}">
                                </div>
                                <div class="form-group">
                                    <input pattern="${RegExConst.NAME_SONAME_REG_EX}" required type="text" name="soname" id="soname_reg" tabindex="1" class="form-control" placeholder=<fmt:message key="soname" bundle="${log}"/> value="${param.soname}">
                                </div>
                                <div class="form-group">
                                    <input pattern="${RegExConst.LOGIN_REG_EX}" required type="email" name="email" id="email" tabindex="1" class="form-control" placeholder=<fmt:message key="email" bundle="${log}"/> value="${param.email}">
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="password" id="password_reg" tabindex="2" class="form-control" placeholder=<fmt:message key="password_placeholder" bundle="${log}"/> >
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder=<fmt:message key="confirm_password_placeholder" bundle="${log}"/>>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value=<fmt:message key="register_now" bundle="${log}"/>>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>
