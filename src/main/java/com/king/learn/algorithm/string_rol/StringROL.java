package com.king.learn.algorithm.string_rol;

/**
 * Created by king on 2017/6/2.
 */
public class StringROL {
    private char[] ch;

    public StringROL() {
    }

    public StringROL(char[] ch) {
        this.ch = ch;
    }

    public StringROL(String st) {
        this.ch = st.toCharArray();
    }

    /**
     * @param from ��ʼλ��
     * @param to   ����λ��
     *             <p>���ܣ������鵹��(��ת)</p>
     */
    private void reverse(int from, int to) {
        while (from < to) {
            char tmp = ch[from];
            ch[from++] = ch[to];
            ch[to--] = tmp;
        }
    }

    /**
     * @param m ѭ������mλ
     * @return ѭ�����ƺ������ch
     */
    public char[] ROL(int m) {
        int n = ch.length;
        m %= n;
        reverse(0, m - 1);
        reverse(m, n - 1);
        reverse(0, n - 1);
        return this.ch;
    }

    @Override
    public String toString() {
        return new String(ch);
    }

}
