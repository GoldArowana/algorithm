package com.king.learn.algorithm.modular_exponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 将指数的二进制 从左往右看
 */
public class LeftToRightBinary {

    private int a;
    private int b;
    private int c;

    //指数b的二进制形式的位数
    private int b_bitSize;

    public LeftToRightBinary(int a, int b, int c) {
        this.a = a % c;
        this.b = b;
        this.c = c;

        for (int i = 0; (1 << i) <= b; i++) b_bitSize = i + 1;
    }

    //测试
    public static void main(String[] args) {
        LeftToRightBinary d = new LeftToRightBinary(4, 13, 497);
        d.go();
    }

    public int fun() {
        int r = 1;
        for (int i = b_bitSize - 1; i >= 0; i--) {
            r = (r * r) % c;
            if (((1 << i) & b) == (1 << i)) r = (r * a) % c;
        }
        return r;
    }

    public void go() {
        int answer = fun();
        System.out.println(answer);
    }
}
