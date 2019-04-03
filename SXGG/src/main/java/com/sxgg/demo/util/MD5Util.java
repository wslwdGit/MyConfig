package com.sxgg.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.util.DigestUtils;

public class MD5Util {
	public static String encodeByMd5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }
}
