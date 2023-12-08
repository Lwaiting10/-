<%--
  Created by IntelliJ IDEA.
  User: 刘xiong
  Date: 7/12/2023
  Time: 下午4:39
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
<h2 class="subtitle">商品管理-${cname}</h2>
<div class="content-container">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>price</th>
            <th>stock</th>
            <th>info</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="goods" items="${goods}">
            <tr>
                <td>${goods.id}</td>
                <td>${goods.name}</td>
                <td>${goods.price==null?"暂无":goods.price}</td>
                <td>${goods.stock}</td>
                <td>${goods.info==null?"暂无":goods.info}</td>
                <td><a href="editGoods?id=${goods.id}">编辑</a></td>
                <td><a href="#" onclick="confirmDelete(${goods.id})">删除</a></td>
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
    <form action="addGoods?" method="post" onsubmit="return validateForm()">
        <input type="text" id="goodsName" name="goodsName" placeholder="商品名称">
        <input type="submit" value="商品增加">
    </form>
</div>
</body>
<script src="js/goods.js"></script>
</html>
