package com.king.learn.algorithm.prime_number;

/**
 * Created by king on 2017/6/10.
 * <p>
 * sieve of Eratosthenes
 * 埃拉托斯特尼筛法
 * <p>
 * 要得到自然数n以内的全部素数，
 * 必须把不大于根号n的所有素数的倍数剔除，
 * 剩下的就是素数。
 * <a href="http://open.163.com/movie/2012/10/0/6/M99VJKUHC_M9ENDUB06.html">
 * 可汗公开课</a>
 */


public class Eratosthenes {
    int n;//求n以内全部素数
    boolean[] arr;//空间为n的bool[]

    //初始化
    public Eratosthenes(int n) {
        this.n = n;
        this.arr = new boolean[n + 1];

        for (int i = 2; i < arr.length; ++i) {
            arr[i] = true;//true代表“暂时当作素数”
        }
    }

    public static void main(String[] args) {
        Eratosthenes e = new Eratosthenes(100);//100以内的素数
        e.go();//运行
    }

    //实现算法
    public void go() {
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (arr[i] == true) {
                for (int j = i * i; j <= n; j += i) {
                    arr[j] = false;
                }
            }
        }

        showArr();//打印结果
    }

    public void showArr() {
        for (int i = 0; i <= n; ++i) {
            if (arr[i]) {
                System.out.println(i);
            }
        }
    }
}
