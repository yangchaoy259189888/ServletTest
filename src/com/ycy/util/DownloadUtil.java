package com.ycy.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: DownloadUtil
 * @Description:
 * @Author:
 * @Date: 2018/12/3 1:27
 * @Version: V1.0
 **/
public class DownloadUtil {
    public static String base64EncodeFileName(String fileName) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            return "=?UTF-8?B?"
                    + new String(base64Encoder.encode(fileName
                    .getBytes("UTF-8"))) + "?=";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
