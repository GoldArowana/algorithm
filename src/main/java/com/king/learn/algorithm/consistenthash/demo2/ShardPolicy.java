package com.king.learn.algorithm.consistenthash.demo2;

import java.util.List;


/**
 * Policy that distribute a key in the shards.
 * <p>
 * Some policy may permit update in runtime, {{@link #update(List)} should
 * implement optionally.
 *
 * @param <T> type of shard info
 * @author wangyankai
 */
public interface ShardPolicy<T extends ShardInfo> {
    /**
     * Update policy with new shards. Some implementor may not support this
     * operation.
     *
     * @param updatingShards
     * @return updated old shards
     * @throws UnsupportedOperationException If the <tt>update</tt> operation is not supported by this
     *                                       policy.
     * @throws IllegalArgumentException      If updating shards not satisfy the policy, ie: some policy
     *                                       require the same size of updating shards with initial shards.
     */
    List<T> update(List<T> updatingShards);

    T getShardInfo(byte[] key);

    /**
     * @return shards that current used.
     */
    List<T> getShards();
}