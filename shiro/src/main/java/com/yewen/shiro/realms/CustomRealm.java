package com.yewen.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm实现 将认证、授权的来源转为数据库的实现
 * @author ShadowStart
 * @create 2021-07-19 20:56
 */
public class CustomRealm extends AuthorizingRealm {

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token中获取用户名
        String username = (String) token.getPrincipal();
        System.out.println(username);
        // 这样就可以根据身份信息使用jdbc mybatis查询数据库
        if ("ruby".equals(username)) {
            // 参数1代表返回数据库中正确的用户名    参数2是返回数据库中正确的密码     参数3提供当前realm的名字 this.getName()
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, "ruby123", this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
