package com.iweb.servlet;

import com.iweb.entity.Classify;
import com.iweb.service.ClassifyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午11:40
 */
@WebServlet("/editClassify")
public class EditClassifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Classify classify = ClassifyService.findById(id);
        req.getSession().setAttribute("editClassify", classify);
        resp.sendRedirect("editClassify.jsp");
    }
}
