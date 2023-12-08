package com.iweb.dao;

import com.iweb.entity.Goods;

import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午10:40
 */
public interface GoodsDao {
    /**
     * 保存商品信息
     *
     * @param goods 要保存的商品对象
     */
    void save(Goods goods);

    /**
     * 根据商品ID查找商品信息
     *
     * @param id 商品ID
     * @return 找到的商品信息，如果未找到返回null
     */
    Goods findById(int id);

    /**
     * 根据商品名称查找商品
     * @param name 商品名称
     * @return 找到的商品对象，如果未找到则返回null
     */
    Goods findByName(String name);

    /**
     * 查找所有的Goods对象
     *
     * @return 返回一个包含所有Goods对象的列表
     */
    List<Goods> findAll();

    /**
     * update方法
     *
     * @param goods 要更新的商品对象
     */
    void update(Goods goods);

    /**
     * delete方法
     *
     * @param id 要删除的对象的ID
     */
    void delete(int id);

    /**
     * 根据分类ID查找商品列表
     *
     * @param classifyId 分类ID
     * @return 商品列表，如果不存在则返回null
     */
    List<Goods> findByClassifyId(int classifyId);
}
