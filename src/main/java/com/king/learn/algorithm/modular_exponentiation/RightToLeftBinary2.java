package com.king.learn.algorithm.modular_exponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 用快速幂求模幂 ， 再用快速乘法优化。
 */
public class RightToLeftBinary2 {
    private int a;
    private int b;
    private int c;

    public RightToLeftBinary2(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //测试
    public static void main(String[] args) {
        RightToLeftBinary2 d = new RightToLeftBinary2(4, 13, 497);
        d.go();
    }

    //快速乘
    public int quick_mul(int m1, int m2, int c) {//  m1 * m2
        int res = 0;
        while (m2 > 0) {
            if ((m2 & 1) == 1) res = (res + m1) % c;
            m2 >>= 1;
            m1 = (m1 + m1) % c;
        }
        return res;
    }

    //快速幂
    public int quick_pow() {
        int r = 1;
        while (b > 0) {
            if ((b & 1) == 1) r = quick_mul(r, a, c);//(r * a) % c
            b >>= 1;
            a = (a * a) % c;
        }
        return r;
    }

    public void go() {
        int answer = quick_pow();
        System.out.println(answer);
    }
}
