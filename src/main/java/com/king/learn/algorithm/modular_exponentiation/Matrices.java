package com.king.learn.algorithm.modular_exponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 利用矩阵快速幂
 * <p>
 * 递归实现
 * <p>
 * 公式：(a*b) % c = (a%c) * b % c
 */
public class Matrices {

    private int a;
    private int b;
    private int c;

    public Matrices(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //测试
    public static void main(String[] args) {
        Matrices m = new Matrices(4, 13, 497);
        m.go();
    }

    public int fun(int a, int b, int c) {
        if (b == 0) return 1;

        if ((b & 1) == 1) {//如果是奇数  b%2==1
            return fun(a, b - 1, c) * a % c;
        } else {       //如果是偶数  b%2==0
            return fun(a, b / 2, c) * fun(a, b / 2, c) % c;
        }
    }

    public void go() {
        int answer = fun(a, b, c);
        System.out.println(answer);
    }
}
