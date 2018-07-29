package com.king.learn.algorithm.consistenthash.demo1.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<T> {
    private String ip;// IP
    private String name;// 名称

    public Node(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    /**
     * 复写toString方法，使用节点IP当做hash的KEY
     */
    @Override
    public String toString() {
        return ip;
    }
}