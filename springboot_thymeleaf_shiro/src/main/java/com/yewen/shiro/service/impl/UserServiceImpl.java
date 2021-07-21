package com.yewen.shiro.service.impl;

import com.yewen.shiro.dao.UserDao;
import com.yewen.shiro.entities.User;
import com.yewen.shiro.service.UserService;
import com.yewen.shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ShadowStart
 * @create 2021-07-20 19:29
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 生成随机盐 并将它设置进salt当中
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        // 明文密码进行md5 + salt + hash
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);

        // 将密码设置为加密后的密码
        user.setPassword(md5Hash.toHex());

        userDao.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public User getRolesByUsername(String username) {
        return userDao.getRolesByUsername(username);
    }

}
