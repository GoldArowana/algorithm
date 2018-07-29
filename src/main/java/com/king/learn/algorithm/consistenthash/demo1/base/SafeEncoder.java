package com.king.learn.algorithm.consistenthash.demo1.base;

import java.io.UnsupportedEncodingException;

public class SafeEncoder {

    public static byte[] encode(final String str) {
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException ignored) {
        }
        return null;
    }

    public static String encode(final byte[] data) {
        try {
            return new String(data, "utf-8");
        } catch (UnsupportedEncodingException ignored) {
        }
        return null;
    }
}
