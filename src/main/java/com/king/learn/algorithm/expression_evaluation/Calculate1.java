package com.king.learn.algorithm.expression_evaluation;
/**
 * @version 2.0
 * @author King
 * @版权 非原创
 * @function 朴素算法字符串表达式的计算。
 * @algorithm 朴素算法
 */

/***********原作者注释。***************/
//这个算法开辟了额外的空间。
//分析这个问题可以知道，字符串长度len是奇数（0不考虑）
//数字的个数是len/2+1,符号的个数为len/2，然后将符号和数字存储起来
//第一步处理所有的乘除法，此时有一个特点就是如果几个数
//连续进行乘除法的时候就需要向前存储结果（为了方便后面的加减法运算）
//算法复杂度O(n)

/*****************************************/

public class Calculate1 {
    private int answer;//最后将结果存在这里
    private char[] expStr = null;//将要计算的表达式
    private int[] num = null;//将数字提取出来，存放在这里
    private char exp[] = null;//将运算符提取出来，存放在这里

    Calculate1(char[] expStr) {
        this.expStr = expStr;
        num = new int[expStr.length / 2 + 1];
        exp = new char[expStr.length / 2];
    }

    public int calc() {
        separate();//将expStr串里的数字和运算符分开，分别存在num和exp中

        multiply_divide();//处理乘法和除法
        add_sub();//处理加减法
        return this.answer;
    }

    private void add_sub() {
        this.answer = num[0];
        for (int i = 0, j = 1; i < exp.length; i++) {
            if (exp[i] == '+')
                this.answer += num[i + 1];
            else if (exp[i] == '-')
                this.answer -= num[i + 1];
        }
    }

    private void multiply_divide() {
        for (int i = 0, j = 0; i < exp.length; i++) {
            if (exp[i] != '*' && exp[i] != '/') {
                j = 0;
                continue;
            }
            if (exp[i] == '*') {
                num[i - j] *= num[i + 1];
            } else if (exp[i] == '/') {
                num[i - j] /= num[i + 1];
            }
            show();
            j++;
        }
        this.answer = num[0];
    }

    private void show() {
        for (int ppp = 0; ppp < num.length; ppp++) {
            System.out.printf("%d  ", num[ppp]);
        }
        System.out.printf("\n");
    }

    private void separate() {
        for (int i = 0, j = 0, k = 0; i < expStr.length; i++) {        //将符号和数字分开存储
            if (i % 2 == 0) {
                num[j++] = expStr[i] - '0';
            } else {
                exp[k++] = expStr[i];
            }
        }
    }
}
