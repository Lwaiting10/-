<%--
  Created by IntelliJ IDEA.
  User: 刘xiong
  Date: 7/12/2023
  Time: 下午11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>简易商家后台管理系统</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body style="background-image: url('./img/background3.webp')">
<%@include file="header.jsp" %>
<h2 class="subtitle">分类编辑</h2>
<div class="content-container">
    <form style="flex-direction: column" action="editClassifySubmit" method="post" onsubmit="return validateForm()">
        <label  for="classifyName">分类名称:</label>
        <input onblur="restoreValue(this, '${editClassify.name}')"  type="text" id="classifyName" name="classifyName" value="${editClassify.name}">
        <%-- 读取错误信息并展示 --%>
        <c:if test="${not empty errorMessage}">
            <div class="error-message-box">
        <span class="error-message">
                ${errorMessage}
        </span>
            </div>
            <%-- 清除错误信息，避免在后续请求中再次显示 --%>
            <c:set var="errorMessage" value="" scope="session"/>
        </c:if>
        <input style="margin-top: 20px" type="submit" value="提交">
    </form>
</div>
</body>
<script src="js/home.js"></script>
</html>
