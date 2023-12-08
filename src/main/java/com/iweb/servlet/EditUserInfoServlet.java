package com.iweb.servlet;

import com.iweb.entity.Goods;
import com.iweb.entity.User;
import com.iweb.service.GoodsService;
import com.iweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author Liu Xiong
 * @date 8/12/2023 下午6:08
 */
@WebServlet("/editUserInfo")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,    // 5 MB
        maxRequestSize = 1024 * 1024 * 5 * 5) // 25 MB
public class EditUserInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        if (!UserService.update(user, req)) {
            // 修改失败设置错误信息
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "修改失败,该用户名已存在!");
            resp.sendRedirect("/editUserInfo.jsp");
            return;
        }
        resp.sendRedirect("/userInfo.jsp");
    }
}
