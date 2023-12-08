package com.iweb.dao;

import com.iweb.entity.User;

import java.util.List;

/**
 * @author Liu Xiong
 * @date 7/12/2023 上午9:46
 */
public interface UserDao {
    /**
     * 保存用户信息
     *
     * @param user 要保存的用户对象
     */
    void save(User user);

    /**
     * 根据给定的id查找用户
     *
     * @param id 用户id
     * @return 返回找到的用户对象，如果未找到返回null
     */
    User findById(Integer id);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户对象，如果未找到则返回null
     */
    User findByUsername(String username);

    /**
     * 查找所有的用户信息
     *
     * @return 返回一个包含所有用户信息的列表，如果无用户信息则返回null
     */
    List<User> findAll();

    /**
     * 更新用户信息
     *
     * @param user 要更新的用户对象
     */
    void update(User user);

    /**
     * delete方法
     *
     * @param id 要删除的对象的ID
     */
    void delete(Integer id);
}
