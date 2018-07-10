package com.king.learn.algorithm.expression_evaluation;
/**
 * @version 2.0
 * @author King
 * @��Ȩ ��ԭ��
 * @function �����㷨�ַ������ʽ�ļ��㡣
 * @algorithm �����㷨
 */

/***********ԭ����ע�͡�***************/
//����㷨�����˶���Ŀռ䡣
//��������������֪�����ַ�������len��������0�����ǣ�
//���ֵĸ�����len/2+1,���ŵĸ���Ϊlen/2��Ȼ�󽫷��ź����ִ洢����
//��һ���������еĳ˳�������ʱ��һ���ص�������������
//�������г˳�����ʱ�����Ҫ��ǰ�洢�����Ϊ�˷������ļӼ������㣩
//�㷨���Ӷ�O(n)

/*****************************************/

public class Calculate1 {
    private int answer;//��󽫽����������
    private char[] expStr = null;//��Ҫ����ı��ʽ
    private int[] num = null;//��������ȡ���������������
    private char exp[] = null;//���������ȡ���������������

    Calculate1(char[] expStr) {
        this.expStr = expStr;
        num = new int[expStr.length / 2 + 1];
        exp = new char[expStr.length / 2];
    }

    public int calc() {
        separate();//��expStr��������ֺ�������ֿ����ֱ����num��exp��

        multiply_divide();//����˷��ͳ���
        add_sub();//����Ӽ���
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
        for (int i = 0, j = 0, k = 0; i < expStr.length; i++) {        //�����ź����ַֿ��洢
            if (i % 2 == 0) {
                num[j++] = expStr[i] - '0';
            } else {
                exp[k++] = expStr[i];
            }
        }
    }
}
