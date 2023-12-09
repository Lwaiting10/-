package com.iweb.servlet;

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

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午4:43
 */
@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("id");
        HttpSession session = req.getSession();
        List<Goods> goods = GoodsService.findByClassifyId(Integer.parseInt(cid));
        session.setAttribute("cid", cid);
        session.setAttribute("cname", ClassifyService.findById(Integer.parseInt(cid)).getName());
        session.setAttribute("goods", goods);
        resp.sendRedirect("goods.jsp");
    }
}
