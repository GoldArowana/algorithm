package com.king.learn.algorithm.expression_evaluation;

/**
 * Created by king on 2017/5/18.
 */
public class Calculate2 {
    int left, right;
    char tempopr;
    int data1, data2;
    int outcome;
    char[] expStr;

    Calculate2(char[] expStr) {
        this.expStr = expStr;
        data1 = expStr[0] - '0';
        data2 = expStr[2] - '0';
        tempopr = expStr[1];
    }

    public int calc() {
        for (int i = 3; i < expStr.length; i = i + 2) {
            if ((tempopr == '+' || tempopr == '-') && (expStr[i] == '*' || expStr[i] == '/')) {
                left = data2;
                right = expStr[i + 1] - '0';
                switch (expStr[i]) {
                    case '*':
                        data2 = left * right;
                        break;
                    case '/':
                        data2 = left / right;
                        break;
                }
            } else {
                left = data1;
                right = data2;
                switch (tempopr) {
                    case '+':
                        data1 = left + right;
                        break;
                    case '-':
                        data1 = left - right;
                        break;
                    case '*':
                        data1 = left * right;
                        break;
                    case '/':
                        data1 = left / right;
                        break;
                }
                data2 = expStr[i + 1] - '0';
                tempopr = expStr[i];
            }
        }
        left = data1;
        right = data2;
        switch (tempopr) {
            case '+':
                outcome = left + right;
                break;
            case '-':
                outcome = left - right;
                break;
            case '*':
                outcome = left * right;
                break;
            case '/':
                outcome = left / right;
                break;
        }
        return outcome;
    }
}
