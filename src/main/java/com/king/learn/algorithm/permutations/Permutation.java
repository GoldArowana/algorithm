package com.king.learn.algorithm.permutations;

/**
 * Created by king on 2017/6/2.
 * 求全排列
 * <p>
 * 例：abc的全排列是  abc、acb、bac、bca、cba、cab
 */
public class Permutation {
    private char[] ch;

    public Permutation() {
    }

    public Permutation(char[] ch) {
        this.ch = ch;
    }

    public Permutation(String st) {
        this.ch = st.toCharArray();
    }

    private void swap(int i, int j) {
        char tmp = ch[i];
        ch[i] = ch[j];
        ch[j] = tmp;
    }


    public void showPermutation(int from, int to) {
        if (from == to) {
            System.out.println(this.toString());
            return;
        }
        for (int i = from; i < to; i++) {
            swap(i, from);
            showPermutation(from + 1, to);
            swap(i, from);
        }
    }

    public void doPermutation() {
        showPermutation(0, ch.length);
    }

    @Override
    public String toString() {
        return new String(ch);
    }
}
