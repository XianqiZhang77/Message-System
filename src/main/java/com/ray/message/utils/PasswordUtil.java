package com.ray.message.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static final String SALT = "dktfjeklw";

    public static String encrypt(String password) {

        return md5(SALT + password);

    }

    private static String md5(String s) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(s.getBytes());
            byte[] digest = md5.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            return myHash;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
