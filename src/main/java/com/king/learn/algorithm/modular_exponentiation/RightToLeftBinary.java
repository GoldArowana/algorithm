package com.king.learn.algorithm.modular_exponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 将指数的二进制 从右往左看
 * <p>
 * 蒙哥马利幂模运算
 * Montgomery
 */
public class RightToLeftBinary {

    private int a;
    private int b;
    private int c;

    public RightToLeftBinary(int a, int b, int c) {
        this.a = a % c;
        this.b = b;
        this.c = c;
    }

    //测试
    public static void main(String[] args) {
        RightToLeftBinary d = new RightToLeftBinary(4, 13, 497);
        d.go();
    }

    public int fun() {
        int r = 1;
        if (c == 0) return 0;
        while (b > 0) {
            if ((b & 1) == 1) r = (r * a) % c;
            b = b >> 1;
            a = (a * a) % c;
        }
        return r;
    }

    public void go() {
        int answer = fun();
        System.out.println(answer);
    }
}
