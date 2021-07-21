package com.yewen.shiro.service;

import com.yewen.shiro.entities.Permission;

import java.util.List;

/**
 * @author ShadowStart
 * @create 2021-07-21 10:32
 */
public interface RoleService {

    // 根据角色id查询权限集合
    List<Permission> getPermissionByRoleId(Integer id);
}
