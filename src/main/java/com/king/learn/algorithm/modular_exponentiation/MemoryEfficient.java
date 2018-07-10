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
public class MemoryEfficient {//内存高效法

    private int a;
    private int b;
    private int c;

    public MemoryEfficient(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //测试
    public static void main(String[] args) {
        MemoryEfficient m = new MemoryEfficient(4, 13, 497);// 4 ^ 13 % 497
        m.go();//计算并输出
    }

    public void go() {
        int t = 1;
        for (int i = 0; i < b; i++) {
            t = (t * a) % c;
        }

        // 输出结果 t
        System.out.println(t);
    }
}
