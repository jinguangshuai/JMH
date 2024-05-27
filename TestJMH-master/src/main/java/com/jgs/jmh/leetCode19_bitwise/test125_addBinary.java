package com.jgs.jmh.leetCode19_bitwise;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/24 - 05 - 24 - 10:04
 * @Description:com.jgs.jmh.leetCode19_bitwise
 * @version:1.0
 */

/**
 * * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * * 二进制本身进行求和
 */
public class test125_addBinary {

    public static String addBinary1(String a, String b) {
        if (a.length() > b.length()) {
            return addBinary1(b, a);
        }
        String sum = "";
        int aLength = a.length();
        int bLength = b.length();
        for (int i = aLength - 1; i >= 0; i--) {
            sum = (a.charAt(i) - '0' + b.charAt(--bLength) - '0') + sum;
        }
        for (int i = b.length() - a.length() - 1; i >= 0; i--) {
            sum = b.charAt(i) + sum;
        }
        StringBuilder sb = new StringBuilder(sum);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) >= '2') {
                sb.setCharAt(i, (char) (sb.charAt(i) - 2));
                if (i - 1 >= 0) {
                    sb.setCharAt(i - 1, (char) (sb.charAt(i - 1) + 1));
                } else {
                    return "1" + sb.toString();
                }
            }
        }
        return sb.toString();
    }

    //优化版本   最优版本
    //每个单词从末尾开始进行拼接相加，相加和如果大于2，carry % 2 + '0'
    //则向前一位进位, carry / 2
    public static String addBinary11(String a, String b) {
        if (a.length() < b.length()) {
            return addBinary11(b, a);
        }
        StringBuilder sb = new StringBuilder(a);
        int m = a.length(), n = b.length(), carry = 0;
        for (int i = 0; i < m; i++) {
            carry += i < m ? a.charAt(m - 1 - i) - '0' : 0;
            carry += i < n ? b.charAt(n - 1 - i) - '0' : 0;
            sb.setCharAt(m - 1 - i, (char) ('0' + (carry % 2)));
            carry /= 2;
        }
        return carry == 0 ? sb.toString() : "1" + sb.toString();
    }


    //每个单词从末尾开始进行拼接相加，相加和如果大于2，carry % 2 + '0'
    //则向前一位进位, carry / 2
    public static String addBinary2(String a, String b) {
        StringBuffer ans = new StringBuffer();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            //如果当前值小于a的长度，那么取当前字符串的倒数第i位，获取具体值
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            //当前位的答案为 (ai + bi + carry) % 2
            ans.append((char) (carry % 2 + '0'));
            //下一位的进位为 (ai + bi + carry) / 2
            carry /= 2;
        }
        //大于0说明二进制的第一位为2或者3，需要进行进位处理
        if (carry > 0) ans.append('1');
        ans.reverse();
        return ans.toString();
    }

    //位运算  异或进行相加，与运算在左移一位进行进位运算
    public static String addBinary3(String a, String b) {
        int x = bitwiseTransferNum(a);
        int y = bitwiseTransferNum(b);
        while (y != 0) {
            // x ^ y 计算x与y的无进位相加结果  1 ^ 1 = 0
            int answer = x ^ y;
            // 计算x 和y的进位  1 & 1 = 1 << 1 = 10
            int carry = (x & y) << 1;
            x = answer;
            y = carry;
        }
        return Integer.toBinaryString(x);
    }

    public static int bitwiseTransferNum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += (s.charAt(i) - '0') * Math.pow(2, s.length() - 1 - i);
        }
        return sum;
    }


    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        String c = "";
        System.out.println(addBinary2(a, b));
    }
}
