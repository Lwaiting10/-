package com.iweb.servlet;

import com.iweb.service.ClassifyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 8/12/2023 上午11:58
 */
@WebServlet("/deleteClassify")
public class DeleteClassifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ClassifyService.delete(id);
        // 更新全局数据
        req.getServletContext().setAttribute("classify", ClassifyService.findAll());
        // 跳转主页
        resp.sendRedirect("/home.jsp");
    }
}
