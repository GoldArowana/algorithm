package com.king.learn.algorithm.permutations;

/**
 * Created by king on 2017/6/2.
 * <p>
 * input:
 * 123
 * output:
 * 123
 * 132
 * 213
 * 231
 * 321
 * 312
 */
public class TestPermutation {
    public static void main(String[] args) {
        Permutation p = new Permutation("123");
        p.doPermutation();
    }
}
