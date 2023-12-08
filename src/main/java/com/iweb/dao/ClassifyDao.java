package com.iweb.dao;

import com.iweb.entity.Classify;

import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午10:28
 */
public interface ClassifyDao {
    /**
     * 保存分类对象
     *
     * @param classify 要保存的分类对象
     */
    void save(Classify classify);
    /**
     * 根据ID查找对应的分类
     *
     * @param id ID值
     * @return 对应的分类对象，如果不存在则返回null
     */
    Classify findById(int id);

    Classify findByName(String name);
    /**
     * 查找所有分类
     *
     * @return 返回一个包含所有分类的列表
     */
    List<Classify> findAll();
    /**
     * update方法
     *
     * @param classify - 需要更新的分类对象
     */
    void update(Classify classify);
    /**
     * delete方法
     *
     * @param id 要删除的对象的ID
     */
    void delete(int id);
}
