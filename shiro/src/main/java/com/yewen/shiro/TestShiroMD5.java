package com.yewen.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author ShadowStart
 * @create 2021-07-19 21:48
 */
public class TestShiroMD5 {

    public static void main(String[] args) {
        // 创建一个md5算法
/*        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes("123".getBytes());
        String s = md5Hash.toHex();
        System.out.println(s);*/

        // 使用md5
        Md5Hash md5Hash = new Md5Hash("ruby123");
        System.out.println(md5Hash.toHex());

        // 使用md5 + salt处理
        Md5Hash md5Hash1 = new Md5Hash("ruby123", "YBW*COOL");
        System.out.println(md5Hash1.toHex());

        // 使用md5 + salt + hash散列
        Md5Hash md5Hash2 = new Md5Hash("ruby123", "YBW*COOL", 1024);
        System.out.println(md5Hash2);

    }
}
