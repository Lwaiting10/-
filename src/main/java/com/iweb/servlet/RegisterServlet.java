package com.iweb.servlet;

import com.iweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午11:41
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        if (!UserService.register(username, password)) {
            // 注册失败，设置错误信息
            session.setAttribute("errorMessage", "该用户名已存在!");
        } else {
            session.setAttribute("errorMessage", "注册成功!");
        }
        resp.sendRedirect("login.jsp");
    }
}
