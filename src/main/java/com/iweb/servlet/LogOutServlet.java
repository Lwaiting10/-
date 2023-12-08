package com.iweb.servlet;

import com.iweb.util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午3:27
 */
@WebServlet("/logOut")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果用户未登录，则跳转至登录页面
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        // 移除用户会话中的"user"属性
        req.getSession().removeAttribute("user");
        // 遍历请求中的所有Cookie
        for (Cookie cookie : req.getCookies()) {
            // 如果Cookie的名称与定义的COOKIE_NAME相同
            if (cookie.getName().equals(DataUtil.COOKIE_NAME)) {
                // 根据Cookie的值删除用户
                DataUtil.removeUserByCookie(cookie);
                Object userCookies = req.getServletContext().getAttribute(DataUtil.APPLICATION_COOKIE_NAME);
                List<Cookie> cookies;
                if (userCookies != null) {
                    cookies = (List<Cookie>) userCookies;
                    // 遍历用户Cookie列表
                    Iterator<Cookie> iterator = cookies.iterator();
                    while (iterator.hasNext()) {
                        Cookie c = iterator.next();
                        if (c.getValue().equals(cookie.getValue())) {
                            iterator.remove();
                            break;
                        }
                    }
                }
                break;
            }
        }
        // 跳转至登录页面
        resp.sendRedirect("login.jsp");
    }

}
