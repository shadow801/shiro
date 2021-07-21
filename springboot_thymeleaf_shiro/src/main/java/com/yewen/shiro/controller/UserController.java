package com.yewen.shiro.controller;

import com.yewen.shiro.entities.User;
import com.yewen.shiro.service.UserService;
import com.yewen.shiro.utils.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author ShadowStart
 * @create 2021-07-20 18:01
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/loginView")
    public String loginView() {
        return "login";
    }

    @GetMapping("/registerView")
    public String registerView() {
        return "register";
    }

    /**
     * 验证码方法
     */
    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 验证码放入session中
        session.setAttribute("code", code);
        // 验证码存入图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220, 60, outputStream, code);

    }

    /**
     * 用户认证
     */
    @PostMapping("/register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/user/loginView";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/user/registerView";
        }
    }


    /**
     * 用来处理身份认证
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("/login")
    public String login(String username, String password,String code, HttpSession session) {
        // 比较验证码
        String codes = (String) session.getAttribute("code");
        try {
            if (codes.equalsIgnoreCase(code)) {
                /*
                 * 在web环境当中，只要我们创建了web管理器，就会自动给shiro的安全工具类注入安全管理器
                 *
                 * 获取主题对象
                 */
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(username, password));
                return "redirect:/index";
            } else {
                throw new RuntimeException("验证码错误");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "redirect:/user/loginView";
    }


    /**
     *
     * @return 重定向回login.jsp页面
     */
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); // 退出用户
        return "redirect:/user/loginView";
    }
}
