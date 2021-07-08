package com.github.tgiachi.sofa.sofaserver.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static String md5File(Path file) throws Exception {
        InputStream is = Files.newInputStream(file);
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
    }
}
