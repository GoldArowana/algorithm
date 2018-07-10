package com.king.learn.algorithm.modular_exponentiation;

/**
 * Created by king on 2017/6/11.
 * <p>
 * 模幂运算 a^b mod c
 * <p>
 * 公式：
 * c mod m = (a * b) mod m
 * c mod m = [(a mod m) * (b mod m)] mod m
 */
public class MemoryEfficient_recursion {//利用递归

    private int a;
    private int b;
    private int c;

    public MemoryEfficient_recursion(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //测试
    public static void main(String[] args) {
        MemoryEfficient_recursion m = new MemoryEfficient_recursion(4, 13, 497);
        m.go();
    }

    public void go() {
        int answer = fun(a, b, c);
        System.out.println(answer);
    }

    public int fun(int a, int b, int c) {
        if (b == 0) {
            return 1;
        }

        return fun(a, b - 1, c) * a % c;
    }
}
