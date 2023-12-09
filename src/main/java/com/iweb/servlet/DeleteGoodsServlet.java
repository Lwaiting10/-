package com.iweb.servlet;

import com.iweb.service.ClassifyService;
import com.iweb.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 8/12/2023 下午12:18
 */
@WebServlet("/deleteGoods")
public class DeleteGoodsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        GoodsService.delete(id);
        resp.sendRedirect("/goods?id=" + req.getSession().getAttribute("cid"));
    }
}
