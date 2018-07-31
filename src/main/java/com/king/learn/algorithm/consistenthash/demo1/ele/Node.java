package com.king.learn.algorithm.consistenthash.demo1.ele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 构造器(String ip, String name)
public class Node<T> {
    private String ip;// IP地址
    private String name;// 节点名称

    /**
     * 重写toString方法，使用节点IP当做hash的KEY
     */
    @Override
    public String toString() {
        return ip;
    }
}