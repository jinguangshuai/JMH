package com.jgs.jmh.leetCodeDoublePointer;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/26 - 03 - 26 - 14:51
 * @Description:com.mashibing.jmh.leetCodeDoublePointer
 * @version:1.0
 */

/**
 * * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 *
 * 字母和数字都属于字母数字字符。
 *
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 */
public class test25_isPalindrome {

    /**
     * *
     * 在Java中，将大写字符转换为小写字符的方法主要有以下几种：
     *
     * 使用Character.toLowerCase()方法：这是Java标准库提供的方法，可以将任何大写字符转换为小写字符。
     *
     *    `  char lowerCaseChar = Character.toLowerCase('A'); // 返回 'a'`
     * 使用String.toLowerCase()方法：如果你有一个字符串，而不是单个的字符，你可以使用这个方法将字符串中的所有大写字符转换为小写字符。
     *
     *   `String lowerCaseString = "HELLO WORLD".toLowerCase(); // 返回 "hello world"`
     * 使用ASCII值进行转换：每个字符都有一个对应的ASCII值，大写字母和小写字母的ASCII值之间的差是32。所以，你可以通过添加32来将大写字符转换为小写字符。但是，这种方法只适用于英文字母。
     *
     *         `  char ch = 'A';
     *            ch = (char) (ch + 32); // 返回 'a'`
     *
     */
    //双指针
    public static boolean isPalindrome(String s) {
        if(null == s || " " == s){
            return true;
        }
        int m = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < m;i++){
            if(Character.isLetterOrDigit(s.charAt(i))){
                sb.append(s.charAt(i));
            }
        }
        int left = 0,right = sb.toString().length()-1;
        while(left < right){
            if(Character.toLowerCase(sb.charAt(left)) == Character.toLowerCase(sb.charAt(right))){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;
    }

    //双指针
    public static boolean isPalindrome2(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    //根据ASCII码值进行判断
    public static boolean isPalindrome3(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        while (l <= r) {
            char cl = 0;
            char cr = 0;
            while (l <= r) {
                char c = s.charAt(l);
                if (c >= 'A' && c <= 'Z') {
                    c += 32;
                }
                if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                    cl = c;
                    break;
                }
                l++;
            }
            while (l <= r) {
                char c = s.charAt(r);
                if (c >= 'A' && c <= 'Z') {
                    c += 32;
                }
                if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                    cr = c;
                    break;
                }
                r--;
            }
            if (cl != cr) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    //双指针节省空间做法
    public static boolean isPalindrome4(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));
        System.out.println(isPalindrome2("0P"));
        System.out.println(isPalindrome3("0P"));
        System.out.println(isPalindrome4("0P"));
    }
}
