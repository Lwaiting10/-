<%--
  Created by IntelliJ IDEA.
  User: 刘xiong
  Date: 7/12/2023
  Time: 下午2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>简易商家后台管理系统</title>
    <style>
        body {
            background-image: url('./img/background2.webp');
        }

        .subtitle {
            text-align: center;
        }

        .content-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px; /* Adjust the top margin as needed */
        }

        #user-avatar1 {
            width: 100px; /* 调整头像的大小 */
            height: 100px; /* 调整头像的大小 */
            border-radius: 50%;
            overflow: hidden;
            background-color: skyblue;
        }

        #user-avatar1 span {
            margin-left: 40px;
            margin-top: 40px;
            width: 100%;
            height: 100%;
            object-fit: cover;
            font-size: 20px; /* 调整头像中文字的大小 */
            color: white;
        }


        .info-box {
            background-color: rgba(255, 255, 255, 0.5);
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            width: 30%; /* Adjust the width as needed */
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .info-box div {
            display: flex;
            justify-content: space-between;
            width: 40%; /* 调整宽度 */
            margin: 10px 0; /* 调整页边距 */
        }

        .info-box span {
            font-size: 18px;
        }

        .error-message-box {
            background-color: #ffcccc;
            padding: 10px;
            border: 1px solid #ff0000;
            margin-bottom: 20px;
            width: 80%; /* 调整宽度 */
        }

        .error-message {
            color: #ff0000;
        }

        .edit-button {
            background-color: #001f3f;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
        }

        .edit-button:hover {
            background-color: #003366;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>
<h2 class="subtitle">个人信息</h2>
<div class="content-container">
    <div class="info-box">
        <div id="user-avatar1">
            <c:if test="${empty user.headSculpture}">
                <span>无</span>
            </c:if>
            <c:if test="${!(empty user.headSculpture)}">
                <img src="data:image/png;base64,<%= ImageUtil.encodeImage(user.getHeadSculpture()) %>"
                     alt="User Avatar">
            </c:if>
        </div>
        <div>
            <span>用户名:</span><span>${user.username}</span>
        </div>
        <div>
            <span>邮箱:</span><span>${user.email == null ? "无" : user.email}</span>
        </div>
    </div>
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
    <a class="edit-button" href="editUserInfo.jsp">修改信息</a>
</div>
</body>
</html>
