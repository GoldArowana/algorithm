package com.king.learn.algorithm.consistenthash.demo2;

/**
 * Basic spec of shard info. Implementor should be threadsafe.
 *
 * @author wangyankai
 */
public interface ShardInfo {
    int getWeight();

    String getName();
}