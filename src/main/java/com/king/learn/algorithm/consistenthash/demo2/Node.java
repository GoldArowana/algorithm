package com.king.learn.algorithm.consistenthash.demo2;

/**
 * @author wangyankai
 * 2017年8月16日
 */
public abstract class Node {
    protected final String host;
    protected final int port;
    protected final int timeout;
    protected final String password;
    protected final int db;

    Node(String host, int port, int timeout, String password, int db) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.password = password;
        this.db = db;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getDb() {
        return db;
    }

    public String getPassword() {
        return password;
    }

}
