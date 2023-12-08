package com.iweb.service;

import com.iweb.dao.GoodsDao;
import com.iweb.dao.impl.GoodsDaoImpl;
import com.iweb.entity.Classify;
import com.iweb.entity.Goods;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午4:44
 */
public class GoodsService {
    private final static GoodsDao GOODS_DAO = new GoodsDaoImpl();

    public static Goods findById(int id) {
        return GOODS_DAO.findById(id);
    }

    public static Goods findByName(String name) {
        return GOODS_DAO.findByName(name);
    }

    public static List<Goods> findAll() {
        return GOODS_DAO.findAll();
    }

    public static List<Goods> findByClassifyId(int classifyId) {
        return GOODS_DAO.findByClassifyId(classifyId);
    }

    public static boolean save(Goods goods) {
        if (findByName(goods.getName()) != null) {
            return false;
        }
        GOODS_DAO.save(goods);
        return true;
    }

    public static void delete(int id) {
        GOODS_DAO.delete(id);
    }

    public static boolean update(Goods goods, Map<String, String[]> newGoods) {
        Goods g = findByName(newGoods.get("name")[0]);
        if (g != null && g.getId() != goods.getId()) {
            return false;
        }
        goods.setName(newGoods.get("name")[0]);
        goods.setPrice(new BigDecimal(newGoods.get("price")[0]));
        goods.setStock(Integer.parseInt(newGoods.get("stock")[0]));
        goods.setInfo(newGoods.get("info")[0]);
        GOODS_DAO.update(goods);
        return true;
    }
}
