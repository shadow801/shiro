package com.yewen.shiro;

import com.yewen.shiro.realms.CustomMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 使用自定义realm
 * @author ShadowStart
 * @create 2021-07-19 20:59
 */
public class TestCustomAuthenticator {

    public static void main(String[] args) {

        // 创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 设置自定义realm
        defaultSecurityManager.setRealm(new CustomMD5Realm());

        // SecurityUtils 给全局的安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();

        // 创建token
        UsernamePasswordToken token = new UsernamePasswordToken("ruby", "ruby123");

        // 用户认证
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
    }
}
