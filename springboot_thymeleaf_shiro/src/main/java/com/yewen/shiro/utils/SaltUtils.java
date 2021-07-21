package com.yewen.shiro.utils;

import java.util.Random;

/**
 * @author ShadowStart
 * @create 2021-07-20 19:32
 */
public class SaltUtils {

    /**
     * 生成salt的静态方法
     * @param n 几位数的salt
     * @return
     */
    public static String getSalt(int n) {
        char[] chars = "ABCDEFJHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz!*^&@#$%".toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(SaltUtils.getSalt(8));
    }

}
