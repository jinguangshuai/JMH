package com.mashibing.jmh.class11;

/**
 * @Auther：jinguangshuai
 * @Data：2024/2/21 - 02 - 21 - 14:37
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class NTest01 {

    public static void fun(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }
    }

    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1" + " from " + from + " to " + to);
        }
        else {
            func(n - 1, from, other, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            func(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        fun(3);
    }
}
