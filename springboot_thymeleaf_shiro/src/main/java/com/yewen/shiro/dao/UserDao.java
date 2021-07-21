package com.yewen.shiro.dao;

import com.yewen.shiro.entities.User;

/**
 * @author ShadowStart
 * @create 2021-07-20 19:12
 */
public interface UserDao {

    void save(User users);

    User getUserByName(String username);

    // 根据用户名查询角色
    User getRolesByUsername(String username);

}
