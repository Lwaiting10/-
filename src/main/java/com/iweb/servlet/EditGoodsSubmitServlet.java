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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Xiong
 * @date 8/12/2023 下午4:01
 */
@WebServlet("/editGoodsSubmit")
public class EditGoodsSubmitServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        Goods goods = (Goods) req.getSession().getAttribute("editGoods");
        Map<String, String[]> newGoods = req.getParameterMap();
        if (!GoodsService.update(goods, newGoods)) {
            // 修改失败设置错误信息
            HttpSession session = req.getSession();
            session.setAttribute("errorMessage", "修改失败,存在同名商品!");
            resp.sendRedirect("/editGoods.jsp");
            return;
        }
        // 跳转商品页面
        resp.sendRedirect("/goods?id=" + req.getSession().getAttribute("cid"));
    }
}
