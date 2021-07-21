package com.yewen.shiro.dao;

import com.yewen.shiro.entities.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ShadowStart
 * @create 2021-07-21 10:08
 */
@Mapper
public interface RoleDao {

    // 根据角色id查询权限集合
    List<Permission> getPermissionByRoleId(Integer id);

}
