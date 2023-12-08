<%--
  Created by IntelliJ IDEA.
  User: 刘xiong
  Date: 7/12/2023
  Time: 下午1:15
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: 刘xiong
  Date: 7/12/2023
  Time: 下午2:55
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
<script src="js/home.js"></script>
<body style="background-image: url('./img/background3.webp')">
<%@include file="header.jsp" %>
<h2 class="subtitle">分类管理</h2>
<div class="content-container">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="classify" items="${classify}">
            <tr>
                <td>${classify.id}</td>
                <td><a href="goods?id=${classify.id}">${classify.name}</a></td>
                <td><a href="editClassify?id=${classify.id}">编辑</a></td>
                <td><a href="#" onclick="confirmDelete(${classify.id})">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
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
    <form action="addClassify" method="post" onsubmit="return validateForm()">
        <input type="text" id="classifyName" name="classifyName" placeholder="分类名称">
        <input type="submit" value="分类增加">
    </form>
</div>
</body>
</html>
