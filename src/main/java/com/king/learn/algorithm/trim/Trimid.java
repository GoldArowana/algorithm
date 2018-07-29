package com.king.learn.algorithm.trim;


/**
 * Created by king on 2017/5/8.
 * 功能：去掉中间的空格，保留两边的空格。
 */
public class Trimid {
    public static void main(String[] args) {
        String a1 = "   asd  fa  sdf   ";
        String a2 = "   asd  fa  sdf";
        String a3 = "asd  fa  sdf   ";
        String a4 = "   asdfasdf   ";
        String a5 = "asdfasdf";
        String a6 = "       ";
        System.out.println("--->" + trimid(a1) + "<---");
        System.out.println("--->" + trimid(a2) + "<---");
        System.out.println("--->" + trimid(a3) + "<---");
        System.out.println("--->" + trimid(a4) + "<---");
        System.out.println("--->" + trimid(a5) + "<---");
        System.out.println("--->" + trimid(a6) + "<---");
    }

    private static String trimid(String a) {
        int start = 0;
        int end = a.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (start < end && a.charAt(start) == ' ') start++;
        while (start < end && a.charAt(end) == ' ') end--;
        int i = start;
        int j = start;
        for (; i < end; i++) {
            if (a.charAt(i) != ' ' && a.charAt(i + 1) == ' ') {
                sb.append(a.substring(start, i + 1));
            }
            if (a.charAt(i) == ' ' && a.charAt(i + 1) != ' ') {
                start = i + 1;
            }
        }
        sb.append(a.substring(start, a.length())).insert(0, a.substring(0, j));
        return sb.toString();
    }
}
