package com.jgs.jmh.test;

/**
 * @Auther：jgs
 * @Data：2024/6/25 - 06 - 25 - 10:13
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test09 {


    public static String addBinary(String a, String b) {
        if (null == a || a.length() == 0 || null == b || b.length() == 0) {
            return a + b;
        }
        StringBuilder sb = new StringBuilder();
        int m = a.length(), n = b.length();
        int carry = 0;
        for (int i = 0; i < Math.max(m, n); i++) {
            carry += i >= m ? 0 : a.charAt(i) - '0';
            carry += i >= n ? 0 : b.charAt(i) - '0';
            sb.append(carry % 2);
            carry /= 2;
        }
        if (carry > 0) sb.append(1);
        sb.reverse();
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(addBinary("101", "11"));
        String str = "1234";
        str = "welcome";
        System.out.println(str);
    }
}
