package com.yewen.shiro.service.impl;

import com.yewen.shiro.dao.RoleDao;
import com.yewen.shiro.entities.Permission;
import com.yewen.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ShadowStart
 * @create 2021-07-21 10:33
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Permission> getPermissionByRoleId(Integer id) {
        return roleDao.getPermissionByRoleId(id);
    }

}
