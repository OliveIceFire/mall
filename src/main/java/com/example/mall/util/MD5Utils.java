package com.example.mall.util;

import com.example.mall.common.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(md5.digest((strValue+ Constant.SALT).getBytes()));
    }

    public static void main(String[] args){
        String md5;
        try {
            md5 = getMD5Str("123456");
            System.out.println(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
