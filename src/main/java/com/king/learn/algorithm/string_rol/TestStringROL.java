package com.king.learn.algorithm.string_rol;

/**
 * Created by king on 2017/6/2.
 * <p>
 * abcdef循环循环左移两位后cdefab
 */
public class TestStringROL {
    public static void main(String[] args) {
        StringROL sr = new StringROL("abcdef");
        System.out.println(sr.ROL(2));
    }
}
