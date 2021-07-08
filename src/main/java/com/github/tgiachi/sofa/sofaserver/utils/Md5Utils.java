package com.github.tgiachi.sofa.sofaserver.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Md5Utils {

    public static String md5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (Exception ex) {
            return text;
        }
    }
}
