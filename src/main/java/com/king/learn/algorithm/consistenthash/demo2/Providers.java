package com.king.learn.algorithm.consistenthash.demo2;

import java.util.ServiceLoader;

/**
 * @author wangyankai
 * 2017年8月16日
 */
public class Providers {
    static final ServiceLoader<ShardPolicyFactory> ShardPolicyFactoryProvider = ServiceLoader.load(ShardPolicyFactory.class);
    static final ServiceLoader<Hashing> HashingProvider = ServiceLoader.load(Hashing.class);

    public static ShardPolicyFactory locateShardPolicyFactory(String name) {
        synchronized (ShardPolicyFactoryProvider) {
            for (ShardPolicyFactory factory : ShardPolicyFactoryProvider) {
                if (name.equals(factory.getName())) {
                    return factory;
                }
            }
        }
        return null;
    }

    public static Hashing locateHashing(String name) {
        synchronized (HashingProvider) {
            for (Hashing encoder : HashingProvider) {
                if (name.equals(encoder.getName())) {
                    return encoder;
                }
            }
        }
        return null;
    }
}