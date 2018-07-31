package com.king.learn.algorithm.consistenthash.demo1.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Hashing {

    ThreadLocal<MessageDigest> md5Holder = new ThreadLocal<>();
    Hashing MD5 = new Hashing() {
        public long hash(String key) {
            return hash(SafeEncoder.encode(key));
        }

        public long hash(byte[] key) {
            try {
                if (md5Holder.get() == null) {
                    md5Holder.set(MessageDigest.getInstance("MD5"));
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("++++ no md5 algorythm found");
            }
            MessageDigest md5 = md5Holder.get();

            md5.reset();
            md5.update(key);
            byte[] bKey = md5.digest();
            return ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
        }
    };

    long hash(String key);

    long hash(byte[] key);

}