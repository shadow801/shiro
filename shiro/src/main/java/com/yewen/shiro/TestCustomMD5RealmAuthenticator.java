package com.yewen.shiro;

import com.yewen.shiro.realms.CustomMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @author ShadowStart
 * @create 2021-07-19 22:04
 */
public class TestCustomMD5RealmAuthenticator {

    public static void main(String[] args) {

        // 创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 自定义realm
        CustomMD5Realm realm = new CustomMD5Realm();

        // 创建hash凭证器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置md5加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        // 设置realm使用hash凭证匹配器
        realm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 注入自定义realm
        defaultSecurityManager.setRealm(realm);

        // 设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 获取subject
        Subject subject = SecurityUtils.getSubject();

        // 创建token
        UsernamePasswordToken token = new UsernamePasswordToken("ruby", "ruby123");

        try {
            subject.login(token);
            System.out.println("认证状态: " + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败: 用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败: 密码错误");
        }


        // 认证的用户实现授权
        if (subject.isAuthenticated()) {
            // 基于角色的权限控制
            System.out.println(subject.hasRole("user"));

            // 基于多角色的权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));

            // 是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "super", "user"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }

            System.out.println("==========================================");
            // 基于权限字符串的访问控制     资源标识符:操作:资源类型
            System.out.println("权限: " + subject.isPermitted("user:create:01"));

            // 分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:01", "order:*:10");
            for (boolean b : permitted) {
                System.out.println(b);
            }

            // 同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("user:*:01", "product:*:01");
            System.out.println(permittedAll);


        }
    }
}
