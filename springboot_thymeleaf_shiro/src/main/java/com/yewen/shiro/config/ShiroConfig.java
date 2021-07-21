package com.yewen.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yewen.shiro.cache.RedisCacheManager;
import com.yewen.shiro.realms.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro框架相关的配置类
 * @author ShadowStart
 * @create 2021-07-20 16:56
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


    // 1 创建ShiroFilter 负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);


        // 配置系统受限资源     配置系统公共资源
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/login.html", "anon"); // anon: 相当于系统的公认资源，所有用户都可以访问，无需认证授权
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/getImage", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/registerView", "anon");
        filterChainDefinitionMap.put("/register.html", "anon");


        filterChainDefinitionMap.put("/**", "authc"); // authc: 请求这个资源需要认证和授权

        // 默认的认证界面的路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginView");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return shiroFilterFactoryBean;
    }

    // 2 创建安全管理器 原来的SecurityManager不具备web功能，所以要用DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 注入realm给安全管理器
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    // 3 创建 自定义realm
    @Bean
    public Realm realm() {
        CustomRealm customRealm = new CustomRealm();

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法为md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        // 修改凭证效验匹配器
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 开启缓存管理
        customRealm.setCacheManager(new RedisCacheManager());
        customRealm.setCachingEnabled(true);
        customRealm.setAuthenticationCachingEnabled(true); // 认证缓存
        customRealm.setAuthenticationCacheName("authenticationCache");
        customRealm.setAuthorizationCachingEnabled(true); // 授权缓存
        customRealm.setAuthorizationCacheName("authorizationCache");

        return customRealm;
    }
}
