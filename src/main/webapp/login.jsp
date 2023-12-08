<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘xiong
  Date: 7/12/2023
  Time: 上午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录&注册</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
<div class="shell">
    <h2 class="title">Login</h2>
    <form action="login" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <input type="text" id="username" name="username" placeholder="用户名" required>
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" placeholder="密码" required>
        </div>
        <div class="form-group" id="captchaGroup" style="display: flex;align-items: center">
            <input type="text" id="captcha" name="captcha" placeholder="验证码" required>
            <img id="captchaImage" src="kaptcha.jpg" alt="Captcha" onclick="changeCaptcha()"
                 style="width: 50%;height: 40px;border: 0;border-radius: 5px">
        </div>
        <%-- 读取错误信息并展示 --%>
        <c:if test="${not empty errorMessage}">
            <div class="error-message-box">
        <span class="error-message" id="error-message">
                ${errorMessage}
        </span>
            </div>
            <%-- 清除错误信息，避免在后续请求中再次显示 --%>
            <c:set var="errorMessage" value="" scope="session"/>
        </c:if>
        <input type="submit" value="登录" id="loginBtn">
        <div class="footer">
            <div class="Remember">
                <input type="checkbox" id="rememberMe" name="rememberMe">
                <label for="rememberMe">记住我</label>
            </div>
            <button id="switch">去注册</button>
        </div>
    </form>
</div>

</body>
<script src="js/login.js"></script>
</html>
