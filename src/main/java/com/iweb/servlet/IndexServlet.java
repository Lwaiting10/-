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
import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午12:43
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 将所有信息存储在application
        ServletContext context = getServletContext();
        context.setAttribute("classify", ClassifyService.findAll());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 检查是否存在记住我 cookie
        Cookie[] cookies = req.getCookies();
        Cookie cookie = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (DataUtil.COOKIE_NAME.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }

        if (cookie != null) {
            Object object = req.getServletContext().getAttribute(DataUtil.APPLICATION_COOKIE_NAME);
            if (object != null) {
                List<Cookie> userCookies = (List<Cookie>) object;
                for (Cookie c : userCookies) {
                    if (c.getValue().equals(cookie.getValue())) {
                        // 根据cookie从COOKIE_USER_MAP提取用户信息
                        User user = DataUtil.getUserByCookie(cookie);
                        if (user != null) {
                            // 用户存在，表示自动登录成功
                            HttpSession session = req.getSession();
                            session.setAttribute("user", user);
                            // 跳转主页
                            resp.sendRedirect("home.jsp");
                            return;
                        }
                    }
                }
            }
        }
        // 没有有效的用户标识信息，需要用户手动登录
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
