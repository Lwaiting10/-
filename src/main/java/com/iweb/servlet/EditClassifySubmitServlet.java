package com.iweb.servlet;

import com.iweb.entity.Classify;
import com.iweb.service.ClassifyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午11:51
 */
@WebServlet("/editClassifySubmit")
public class EditClassifySubmitServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Classify classify = (Classify) req.getSession().getAttribute("editClassify");
        String newName = req.getParameter("classifyName");
        if (!ClassifyService.update(classify, newName)) {
            // 修改失败设置错误信息
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "修改失败,存在同名分类!");
            resp.sendRedirect("/editClassify.jsp");
            return;
        }
        // 更新全局数据
        req.getServletContext().setAttribute("classify", ClassifyService.findAll());
        // 跳转主页
        resp.sendRedirect("/home.jsp");
    }
}
