package com.iweb.service;

import com.iweb.dao.ClassifyDao;
import com.iweb.dao.impl.ClassifyDaoImpl;
import com.iweb.entity.Classify;
import com.iweb.entity.Goods;

import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 下午4:07
 */
public class ClassifyService {
    private final static ClassifyDao CLASSIFY_DAO = new ClassifyDaoImpl();

    public static Classify findById(int id) {
        return CLASSIFY_DAO.findById(id);
    }

    public static Classify findByName(String name) {
        return CLASSIFY_DAO.findByName(name);
    }

    public static List<Classify> findAll() {
        return CLASSIFY_DAO.findAll();
    }

    public static boolean save(Classify classify) {
        if (findByName(classify.getName()) != null) {
            return false;
        }
        CLASSIFY_DAO.save(classify);
        return true;
    }

    public static void delete(int id) {
        CLASSIFY_DAO.delete(id);
        List<Goods> goods = GoodsService.findByClassifyId(id);
        if (goods != null) {
            // 其对应的商品也将删除
            for (Goods g : goods) {
                GoodsService.delete(g.getId());
            }
        }
    }

    public static boolean update(Classify classify, String newName) {
        Classify c = CLASSIFY_DAO.findByName(newName);
        if (c != null && c.getId() != classify.getId()) {
            return false;
        }
        classify.setName(newName);
        CLASSIFY_DAO.update(classify);
        return true;
    }
}
