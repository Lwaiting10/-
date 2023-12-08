<%@ page import="com.iweb.util.ImageUtil" %>
<%@ page import="com.iweb.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        body {
            margin: 0;
            padding: 20px;
            font-family: Arial, sans-serif;
        }

        #header {
            /*background-color: #b2b2b2;*/
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 60px; /* 调整 header 的高度 */
            background-color: #ffffff49;
            border-radius: 20px;
            box-shadow: 0 0 30px rgba(255, 255, 255, 0.5) inset;
        }

        #user-info {
            display: flex;
            align-items: center;
        }

        #user-avatar {
            width: 60px; /* 调整头像的大小 */
            height: 60px; /* 调整头像的大小 */
            border-radius: 50%;
            margin-right: 10px;
            overflow: hidden;
            background-color: skyblue;
        }

        #user-avatar span {
            margin-left: 8px;
            width: 100%;
            height: 100%;
            object-fit: cover;
            font-size: 20px; /* 调整头像中文字的大小 */
        }

        #user-avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            color: skyblue;
        }

        #welcome-msg {
            font-size: 20px; /* 调整欢迎信息的字体大小 */
            color: darkblue;
        }

        #user-link {
            font-size: 20px;
        }

        #logout-link {
            cursor: pointer;
            color: #fff;
        }

        #page-name {
            font-size: 30px; /* 调整页面名称的字体大小 */
            font-weight: bold;
            padding-right: 90px;
            color: darkblue;
        }

        a {
            color: #3579f6;
            text-decoration: none;
        }

        a:link {
            color: #007bff;
        }

        a:hover {
            color: #3822ff;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div id="header">
    <div id="user-info">
        <div id="user-avatar">
            <%
                User user = (User) session.getAttribute("user");
            %>
            <c:if test="${empty user.headSculpture}">
                <span>无</span>
            </c:if>
            <c:if test="${!(empty user.headSculpture)}">
                <img src="data:image/png;base64,<%= ImageUtil.encodeImage(user.getHeadSculpture()) %>"
                     alt="User Avatar">
            </c:if>
        </div>
        <div>
            <span id="welcome-msg">
                <c:if test="${empty user}">
                    <a href="login.jsp">请登录</a>
                </c:if>
                <c:if test="${not empty user}">
                    欢迎你,
                </c:if>
            </span>
            <a href="userInfo.jsp" id="user-link">${user.username}</a>
        </div>
    </div>
    <div id="page-name" onclick="function skipHome() {
        window.location.href ='home.jsp';
    }
    skipHome()">简易商家后台管理系统
    </div>
    <div id="logout-link">
        <a href="logOut">退出登录</a>
    </div>
</div>

</body>
</html>
