package com.king.learn.algorithm.consistenthash.demo2;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Jedis ({@link redis.clients.util.Sharded}) compatible Consistent Hashing.
 *
 * @param <T> type of shard info.
 * @author wangyankai
 * @see redis.clients.util.Sharded
 */
public class KetamaHashing<T extends ShardInfo> implements ShardPolicy<T> {
    public static final int DEFAULT_WEIGHT = 1;
    protected final TreeMap<Long, AtomicReference<T>> nodes;
    protected final Hashing algo;
    protected final List<AtomicReference<T>> originals;

    public KetamaHashing(List<T> shards, Hashing algo) {
        if (shards == null || shards.isEmpty()) {
            throw new IllegalArgumentException("shards is null or empty.");
        }
        this.algo = algo;
        this.nodes = new TreeMap<Long, AtomicReference<T>>();
        this.originals = new ArrayList<AtomicReference<T>>(shards.size());
        this.initialize(shards);
    }

    protected void initialize(List<T> shards) {
        Charset charset = Charset.forName("utf-8");
        String key = null;
        for (int i = 0; i < shards.size(); i++) {
            final T shardInfo = shards.get(i);
            if (shardInfo == null) {
                throw new IllegalArgumentException("shard element #" + i + " is null.");
            }
            final AtomicReference<T> wrapper = new AtomicReference<T>(shardInfo);
            this.originals.add(wrapper);
            for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
                if (shardInfo.getName() == null) {
                    key = "SHARD-" + i + "-NODE-" + n;
                } else {
                    key = shardInfo.getName() + "*" + shardInfo.getWeight() + n;
                }
                nodes.put(this.algo.hash(key.getBytes(charset)), wrapper);
            }
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
        if (originals.size() != updatingShards.size()) {
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
        Long lkey = nodes.ceilingKey(algo.hash(key));
        if (lkey == null) {
            lkey = nodes.firstKey();
        }
        return nodes.get(lkey).get();
    }

    /**
     * jdk6
     *
     * @param key
     * @return
     */
    final T getShardInfo1(byte[] key) {
        Long lkey = nodes.ceilingKey(algo.hash(key));
        if (lkey == null) {
            lkey = nodes.firstKey();
        }
        return nodes.get(lkey).get();
    }

    /**
     * pre jdk6
     *
     * @param key
     * @return
     */
    final T getShardInfo2(byte[] key) {
        SortedMap<Long, AtomicReference<T>> tail = nodes.tailMap(algo.hash(key));
        if (tail.size() == 0) {
            return nodes.get(nodes.firstKey()).get();
        }
        return tail.get(tail.firstKey()).get();
    }

    @Override
    public List<T> getShards() {
        List<T> shards = new ArrayList<T>(originals.size());
        for (int i = 0; i < originals.size(); i++) {
            shards.add(originals.get(i).get());
        }
        return shards;
    }

}
