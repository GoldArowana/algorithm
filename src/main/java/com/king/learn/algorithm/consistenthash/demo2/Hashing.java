package com.king.learn.algorithm.consistenthash.demo2;

/**
 * Compute string/byte[] to long for hash usage. Implementor should be
 * threadsafe.
 *
 * @author wangyankai
 * 2017年8月16日
 */
public interface Hashing extends Provider {

    public long hash(String key);

    public long hash(byte[] key);
}