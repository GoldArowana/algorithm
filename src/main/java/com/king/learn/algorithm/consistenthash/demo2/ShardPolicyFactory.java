package com.king.learn.algorithm.consistenthash.demo2;

import java.util.List;

/**
 * @author wangyankai
 * 2017年8月16日
 */
public interface ShardPolicyFactory extends Provider {
    <T extends ShardInfo> ShardPolicy<T> createShardPolicy(List<T> shards, Hashing algo);
}