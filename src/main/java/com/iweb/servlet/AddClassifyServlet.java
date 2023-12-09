package com.iweb.servlet;

import com.iweb.entity.Classify;
import com.iweb.service.ClassifyService;
import com.iweb.util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午4:03
 */
@WebServlet("/addClassify")
public class AddClassifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String classifyName = req.getParameter("classifyName");
        Classify classify = new Classify(classifyName);
        if (!ClassifyService.save(classify)) {
            // 添加失败，设置错误信息
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "存在同名分类!");
        }
        // 更新全局数据
        req.getServletContext().setAttribute("classify", ClassifyService.findAll());
        // 跳转主页
        resp.sendRedirect("/home.jsp");
    }
}
