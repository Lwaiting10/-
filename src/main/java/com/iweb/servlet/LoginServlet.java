package com.iweb.servlet;

import com.iweb.entity.User;
import com.iweb.service.ClassifyService;
import com.iweb.service.UserService;
import com.iweb.util.DataUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午10:59
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 将所有信息存储在application
        ServletContext context = getServletContext();
        context.setAttribute("classify", ClassifyService.findAll());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 检查用户名和密码
        User user = UserService.login(username, password);
        if (user == null) {
            // 登录失败，设置错误信息
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "用户名或密码错误");
            // 转到登录页面
            resp.sendRedirect("/login.jsp");
            return;
        }
        // 获取用户输入的验证码值
        String userEnteredCaptcha = req.getParameter("captcha");
        // 从会话中获取存储的验证码值
        HttpSession session = req.getSession();
        String storedCaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        // 验证验证码
        if (userEnteredCaptcha != null && userEnteredCaptcha.equals(storedCaptcha)) {
            // 验证码有效
            if (req.getParameter("rememberMe") != null) {
                // 用户选择了记住账号
                // 将用户信息存储在 Cookie 中
                Cookie c = new Cookie(DataUtil.COOKIE_NAME, generateCookieValue());
                c.setMaxAge(24 * 60 * 60);
                resp.addCookie(c);
                // cookie与用户的绑定信息存入COOKIE_USER_MAP
                DataUtil.add(c, user);
                // cookie存入application中
                Object userCookies = req.getServletContext().getAttribute(DataUtil.APPLICATION_COOKIE_NAME);
                List<Cookie> cookies;
                if (userCookies == null) {
                    cookies = new ArrayList<>();
                } else {
                    cookies = (List<Cookie>) userCookies;
                }
                cookies.add(c);
                req.getServletContext().setAttribute(DataUtil.APPLICATION_COOKIE_NAME, cookies);
            }
            // 用户对象存入session中
            session.setAttribute("user", user);
            // 跳转主页
            resp.sendRedirect("home.jsp");
        } else {
            // 验证码无效，显示错误消息
            session.setAttribute("errorMessage", "验证码错误");
            resp.sendRedirect("login.jsp");
        }
    }

    private String generateCookieValue() {
        byte[] randomBytes = new byte[32]; // 32字节随机数
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
