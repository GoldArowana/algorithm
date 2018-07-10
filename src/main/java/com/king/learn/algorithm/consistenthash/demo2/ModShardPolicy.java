package com.king.learn.algorithm.consistenthash.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Mod Hashing.
 *
 * @param <T> type of shard info.
 * @author wangyankai
 * @see redis.clients.util.Sharded
 */
public class ModShardPolicy<T extends ShardInfo> implements ShardPolicy<T> {
    protected final Hashing algo;
    protected final List<AtomicReference<T>> originals;
    protected final int shardsSize;

    public ModShardPolicy(List<T> shards, Hashing algo) {
        if (shards == null || shards.isEmpty()) {
            throw new IllegalArgumentException("shards is null or empty.");
        }
        this.algo = algo;
        this.shardsSize = shards.size();
        this.originals = new ArrayList<AtomicReference<T>>(this.shardsSize);
        for (int i = 0; i < shards.size(); i++) {
            final T shardInfo = shards.get(i);
            if (shardInfo == null) {
                throw new IllegalArgumentException("shard element #" + i + " is null.");
            }
            final AtomicReference<T> wrapper = new AtomicReference<T>(shardInfo);
            this.originals.add(wrapper);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return updated and different old shards.
     */
    @Override
    public synchronized List<T> update(List<T> updatingShards) {
        if (updatingShards == null || updatingShards.isEmpty()) {
            throw new IllegalArgumentException("shards is null or empty.");
        }
        if (this.shardsSize != updatingShards.size()) {
            throw new IllegalArgumentException("different size of updating and orignal shards.");
        }
        List<T> evictedShards = new ArrayList<T>();
        for (int i = 0; i < originals.size(); i++) {
            T updating = updatingShards.get(i);
            if (updating == null) {
                throw new IllegalArgumentException("updating element #" + i + " is null.");
            }
            if (!originals.get(i).get().equals(updating)) {
                evictedShards.add(originals.get(i).getAndSet(updating));
            }
        }
        return evictedShards;
    }

    @Override
    public final T getShardInfo(byte[] key) {
        int index = (int) (this.algo.hash(key) % this.shardsSize);
        if (index < 0) {
            index += this.shardsSize;
        }
        return originals.get(index).get();
    }

    @Override
    public List<T> getShards() {
        List<T> shards = new ArrayList<T>(this.shardsSize);
        for (int i = 0; i < this.shardsSize; i++) {
            shards.add(originals.get(i).get());
        }
        return shards;
    }

}
