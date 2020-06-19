package com.imcode.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {
    private static  int i=5;
/*
* 加密工具类
*
* */
    public static String md5(String source, String salt){
        return new Md5Hash(source,salt,i).toString();
    }
}
