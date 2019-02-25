package com.taihe.databasedemo.utils;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String encodeByMd5(String beforeEncoding) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64 = new BASE64Encoder();
        return base64.encode(md5.digest(beforeEncoding.getBytes(StandardCharsets.UTF_8)));
    }
}

