package com.king.learn.algorithm.expression_evaluation;

/**
 * Created by king on 2017/5/15.
 */
public class Test {
    public static void main(String[] args) {
        Calculate1 calculate1 = new Calculate1("1+4*5-8/3*2*2*2".toCharArray());
        System.out.println(calculate1.calc());
    }
}
