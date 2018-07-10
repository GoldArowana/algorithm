package com.king.learn.algorithm.consistenthash.demo2;

/**
 * @author wangyankai
 * 2017年8月16日
 */
public class ShardNode extends Node implements ShardInfo {
    private final String name;
    private final int weight;
    private final int hashCode; // pre-compute

    private ShardNode(String host, int port, int timeout, String password, int db, String name, int weight) {
        super(host, port, timeout, password, db);
        this.name = name;
        this.weight = weight;
        this.hashCode = computeHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        return String.format("%s:%s/%s*%s", host, port, db, getWeight());
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    private int computeHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + db;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + port;
        result = prime * result + timeout;
        result = prime * result + weight;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShardNode other = (ShardNode) obj;
        if (hashCode != other.hashCode) {
            return false;
        }
        if (db != other.db)
            return false;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (port != other.port)
            return false;
        if (timeout != other.timeout)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class Builder {

        public static final int DEFAULT_PORT = 6379;
        public static final int DEFAULT_SENTINEL_PORT = 26379;
        public static final int DEFAULT_TIMEOUT = 2000;
        public static final int DEFAULT_DATABASE = 0;
        private String host;
        private int port = DEFAULT_PORT;
        private int timeout = DEFAULT_TIMEOUT;
        private String password;
        private int db = DEFAULT_DATABASE;
        private String name;
        private int weight = 1;

        Builder() {
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder db(int db) {
            this.db = db;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public ShardNode build() {
            return new ShardNode(host, port, timeout, password, db, name, weight);
        }
    }
}
