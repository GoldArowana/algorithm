package com.king.learn.algorithm.consistenthash.demo2;

public interface Hashing extends Provider {

    public long hash(String key);

    public long hash(byte[] key);
}