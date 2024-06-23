package com.jgs.jmh.leetCode.leetcode01_ArrayString;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/22 - 03 - 22 - 17:29
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 */
public class test22_convert {

    public static String convert(String s, int numRows) {
        if (s.length() <= numRows) {
            return s;
        }
        int n = s.length();
        String[][] str = new String[numRows][n];
        int i = 0;
        int col = 0;
        while (col < n) {
            int row = 0;
            while (row < numRows && i < n) {
                str[row][col] = String.valueOf(s.charAt(i));
                row++;
                i++;
            }
            if (row == numRows) {
                row = row - 2;
            }
            while (row > 0 && i < n) {
                col++;
                str[row][col] = String.valueOf(s.charAt(i));
                row--;
                i++;
            }
            col++;
        }
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            for (int k = 0; k < n; k++) {
                if (null != str[j][k]) {
                    result.append(str[j][k]);
                }
            }
        }
        return result.toString();
    }

    //官方二维数组遍历
    public static String convert2(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        // r为行
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        // x为行  y为列
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

    // 压缩矩阵空间
    public static String convert3(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        // 每个mat[i] 代表一行
        StringBuffer[] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }
        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert3(s, 4));
    }
}
