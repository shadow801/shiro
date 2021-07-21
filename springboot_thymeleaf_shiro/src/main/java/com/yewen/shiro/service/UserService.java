package com.yewen.shiro.service;

import com.yewen.shiro.entities.User;

/**
 * @author ShadowStart
 * @create 2021-07-20 19:24
 */
public interface UserService {

    /**
     * 注册用户
     * @param user 用户
     */
    void register(User user);

    /**
     * 根据用户名查询用户
     * @param user
     * @return
     */
    User getUserByName(String username);

    User getRolesByUsername(String username);

}
