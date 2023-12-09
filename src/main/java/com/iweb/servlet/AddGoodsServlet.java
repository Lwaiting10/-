package com.iweb.servlet;

import com.iweb.entity.Goods;
import com.iweb.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午6:28
 */
@WebServlet("/addGoods")
public class AddGoodsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        int cid = Integer.parseInt((String) session.getAttribute("cid"));
        String name = req.getParameter("goodsName");
        Goods goods = new Goods(cid, name);
        if (!GoodsService.save(goods)) {
            // 添加失败，设置错误信息
            session.setAttribute("errorMessage", "存在同名商品!");
        }
        // 跳转商品页面
        resp.sendRedirect("/goods?id=" + req.getSession().getAttribute("cid"));
    }
}
