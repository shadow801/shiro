package com.yewen.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 使用自定义realm 加入md5 + salt + hash
 * @author ShadowStart
 * @create 2021-07-19 22:00
 */
public class CustomMD5Realm extends AuthorizingRealm {

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取身份信息
        String username = (String) token.getPrincipal();
        // 根据用户名查询数据库
        if ("ruby".equals(username)) {
            // 参数1：数据库用户名   参数2：数据库md5 + salt之后的密码  参数3：注册时的随机盐     参数4：realm的名字
            return new SimpleAuthenticationInfo(username, "be0e8c4b21c3d8df6eee0c86c2cb4e21", ByteSource.Util.bytes("YBW*COOL"), this.getName());
        }

        return null;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("-------------------");
        String username = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息: " + username);

        // 根据身份信息 用户名 获取当前用户的角色信息，以及权限信息 ruby   admin   user
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 将数据库中的查询角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        // 将数据库中查询权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*");
        simpleAuthorizationInfo.addStringPermission("product:*");

        return simpleAuthorizationInfo;
    }

}
