<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.controller.constants.RegExConst" %>
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
<c:if test="${not empty validation_errors}" >
    <div class="alert alert-danger" align="center">
        <strong>Error!</strong>
        <td>${validation_errors}</td>
    </div>
</c:if>
<%--
<c:if test="${not empty param.error}" >
    <div class="alert alert-danger" align="center">
        <strong>Error!</strong>
        <td>${param.error}</td>
    </div>
</c:if>--%>

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
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <%--Login--%>
                            <form id="login-form" action="${pageContext.request.contextPath}/login" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <input pattern="${RegExConst.LOGIN_REG_EX}" required type="text" name="login" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                        </div>
                                    </div>
                                </div>

                            </form>

                            <%--Registration--%>

                            <form id="register-form" action="${pageContext.request.contextPath}/register" method="post" role="form" style="display: none;">
                                <div class="form-group">
                                    <input pattern="${RegExConst.NAME_SONAME_REG_EX}" required type="text" name="name" id="name_reg" tabindex="1" class="form-control" placeholder="Name" value="">
                                </div>
                                <div class="form-group">
                                    <input pattern="${RegExConst.NAME_SONAME_REG_EX}" required type="text" name="soname" id="soname_reg" tabindex="1" class="form-control" placeholder="Soname" value="">
                                </div>
                                <div class="form-group">
                                    <input pattern="${RegExConst.LOGIN_REG_EX}" required type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="password" id="password_reg" tabindex="2" class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input required type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
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
