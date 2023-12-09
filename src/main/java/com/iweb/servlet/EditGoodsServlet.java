package com.iweb.servlet;

import com.iweb.entity.Classify;
import com.iweb.entity.Goods;
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
 * @date 8/12/2023 下午4:01
 */
@WebServlet("/editGoods")
public class EditGoodsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Goods goods = GoodsService.findById(id);
        req.getSession().setAttribute("editGoods", goods);
        resp.sendRedirect("editGoods.jsp");
    }
}
