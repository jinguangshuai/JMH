package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/30 - 05 - 30 - 16:02
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * * 给你一个字符串 s，找到 s 中最长的回文子串
 * * 子字符串是字符串中连续的非空字符序列。
 * 如果字符串向前和向后读都相同，则它满足回文性。
 */
public class test145_longestPalindrome {

    public static String longestPalindrome1(String s) {
        int result = 0;
        String ans = "";
        int n = s.length();
        if (n <= 1) return s;
        Set<String> set = new HashSet<>();
        process(s, 0, set);
        for (String str : set) {
            if (str.length() > result && judge(str)) {
                result = str.length();
                ans = str;
            }
        }
        return ans;
    }
    public static void process(String s, int index, Set<String> set) {
        if (index == s.length()) {
            return;
        }
        for (int i = index; i <= s.length(); i++) {
            set.add(s.substring(index, i));
        }
        process(s, index + 1, set);
    }

    public static boolean judge(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //动态规划简洁版本 只需根据动态规划对角线上方的值进行状态转移
    //从后往前进行枚举，判断字符串是否为回文串
    public static String longestPalindrome2(String s) {
        //记录左右边界和 右-左的长度
        int left = 0, right = 0, result = 0;
        int n = s.length();
        if (n <= 1) return s;
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            //j=i这步就将每个单个字符dp赋为了true
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i > result) {
                        result = j - i;
                        left = i;
                        right = j;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }

    //官方解法：（1）动态规划
    public static String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) return s;
        int maxLen = 1, begin = 0;
        boolean[][] dp = new boolean[len][len];
        //初始化 所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] chars = s.toCharArray();
        //L代表字段的长度
        for (int L = 2; L <= len; L++) {
            for (int left = 0; left < len; left++) {
                //由left和L长度可确定有边界 right - left + 1 = L
                int right = L + left - 1;
                //如果右边界越界，可以退出当前循环
                if (right >= len) break;
                if (chars[left] != chars[right]) {
                    dp[left][right] = false;
                } else {
                    if (left - right < 3) {
                        //字符的长度小于4，说明字符长度为3、2、1，且left = right（左右边界相同），则证明为回文串
                        dp[left][right] = true;
                    } else {
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    begin = left;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    //中心扩展算法
    //我们枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。
    // 我们对所有的长度求出最大值，即可得到最终的答案
    public static String longestPalindrome4(String s) {
        if (null == s || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //计算长度为奇数的字符串
            int len1 = expandAroundCenter(s, i, i);
            //计算长度为偶数的字符串
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            //对于长度为奇数来说，则说明字符串的中心为i i，起始长度为 i - len / 2 终止长度为 i + len / 2
            //对于长度为偶数来说，则说明字符串的中心为i i+1，起始长度为 i - (len-1) / 2 终止长度为 i + len / 2
            //可合并为 起始长度为 i - (len-1) / 2 终止长度为 i + len / 2
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //因为字符串的长度为right - left + 1，但是while结束的条件为s.charAt(left) ！= s.charAt(right)
        //所以真正符合条件的字符串边界为 left+1 ~ right-1，真正的长度为 right-1 - （left+1） + 1 = right - left - 1
        return right - left - 1;
    }

    //Manacher算法
    public static String longestPalindrome5(String s) {
        int start = 0, end = -1;
        //处理s字符串使其变为奇数长度的字符串
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); i++) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();
        List<Integer> list = new ArrayList<>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); i++) {
            //设置当前字符串的臂长
            int cur_arm_length;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(list.get(i_sym), right - i);
                cur_arm_length = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_length = expand(s, i, i);
            }
            list.add(cur_arm_length);
            if (i + cur_arm_length > right) {
                j = i;
                right = i + cur_arm_length;
            }
            if (cur_arm_length * 2 + 1 > end - start) {
                start = i - cur_arm_length;
                end = i + cur_arm_length;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //因为字符串的长度为right - left + 1，但是while结束的条件为s.charAt(left) ！= s.charAt(right)
        //所以真正符合条件的字符串边界为 left+1 ~ right-1，真正的臂长为 right-1 - （left+1）= right - left - 2
        //例如 d#a#b#a#c  对于b来说，它的臂长为 （8-0-2）/2 = 3
        return (right - left - 2) / 2;
    }

    // manachar方法：面试加分项
    public static String longestPalindrome6(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 将s转换为加了特殊字符#的字符数组，目的是统一奇偶数的回文中心差异性问题
        // 比如：s=”cabac“转化为cs=[#c#a#b#a#c#]
        char[] cs = manacherString(s, len);
        // pArr[i]是cs[i]每个位置的最大回文半径
        // 比如：cs=[#c#a#b#a#c#]，pArr=[1,2,1,2,1,6,1,2,1,2,1]
        int[] pArr = new int[cs.length];
        // pR是cs[i]每个位置的回文右边界的下一个位置
        // 比如：cs=[#c#a#b#a#c#]，pR=1,3,3,5,5,11(此时pR第一次遍历完cs，之后的pR可以不再更新),11,11,11,11,11
        int pR = -1;  //回文右边界
        // index是最近更新pR时的回文中心位置
        // 比如：cs=[#c#a#b#a#c#]，index=0,1,1,3,3,5(之后pR不再更新，index也不再更新),5,5,5,5,5
        int index = -1; //回文中心
        // max记录pArr[i]中最大值：pArr[i]最大值就能算出原字符串的最长回文子串长度
        int maxLen = Integer.MIN_VALUE;
        int centerIndex = Integer.MIN_VALUE;
        int begin = 0;
        for (int i = 0; i != cs.length; i++) {
            // 第一句代码:每轮循环时,i至少不需要验证的区域,先给到pArr[i],解释如下:
            // pR<=i:i超过了pR，无法优化，不用验证的区域就是pArr[i]本身=回文半径为1
            // pR>i:i没有超过pR，可以优化，至少不需要验的区域：Math.min(pArr[2 * index - i], pR - i)
            /**
             * 同时满足上面的两类情况
             * 1）如果 R < i，即 i 不在 R 里，那么半径至少为 1
             * 2）如果 R > i，即 i 在 R 里，那么
             *      p[Arr[2 * C - i]]：i 关于 C 的对称点 j 所对应的回文半径
             *      为什么取两者的较小值呢？
             *      首先三种情况：
             *          如果 j 回文子串完全在 C 回文子串里，那么 i 回文子串半径就是p[Arr[2 * C - i]]
             *          如果 j 回文子串在部分在 C 回文子串里，那么 i 回文子串半径就是 R - i
             *          如果 j 回文子串左边界正好等于 C 回文子串的左边界，那么 i 回文子串半径至少为 R - i
             *      因为 j 回文子串可能不全在 C 回文子串里，
             *          当 j 回文子串满足第一种情况时，此时 R - i >= p[Arr[2 * C - i]]， i 回文子串半径就是p[Arr[2 * C - i]]
             *          当 j 回文子串满足第二、第三种情况时， 此时 R - i <= p[Arr[2 * C - i]]，i 回文子串半径就是 R - i
             *      所以直接写成了 Math.min(pArr[2 * C - i], R - i)
             */
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            // 第二句代码:在i位置尝试往外扩最长回文半径长度pArr[i]:
            // 如果扩成功pArr[i]++;否则立刻停止扩的过程break
            /**
             *
             * 主要针对 R < i 和 j 回文子串左边界正好等于 C 回文子串的左边界这两种情况，回文子串需要扩充
             * pArr[i]存放了当前位置的最少的回文半径,charArr[i + pArr[i]]和charArr[i - pArr[i]分别是当前回文子串的后一个和前一个字符，相等则 pArr[i]++，否则break
             *
             * 虽然上面 j 回文子串完全在 C 回文子串里和 j 回文子串在部分在 C 回文子串里时，pArr[i] 已经是确定值，不需要再扩充，
             * 但为了代码方便，减少 if else 使用，直接全部扩充，不需要的扩充的仅需进行一次判断就 break了，对代码整体时间复杂度影响不大
             */
            while (i + pArr[i] < cs.length && i - pArr[i] >= 0) {//从最小回文半径开始往外扩张
                if (cs[i + pArr[i]] == cs[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            // 每轮循环,扩的长度超过回文右边界下一个位置，就更新pR和index
            if (i + pArr[i] > pR) {
                //更新回文长度
                pR = i + pArr[i];
                //更新中心点位置
                index = i;
            }
            // 找出cs中回文半径最大值maxLen和其对应的数组索引centerInter
            // 最长回文长度发生变化，记录最长中心位置和最长右边界
            if (pArr[i] > maxLen) {
                maxLen = pArr[i];
                centerIndex = i;
            }
        }
        // 找出cs中回文半径最大值maxLen和其对应的数组索引centerInter
        // 下面代码可以在上面的循环里取到，减少一次遍历时间
//        for (int i = 0; i < cs.length; i++) {
//            if (pArr[i] > maxLen) {
//                maxLen = pArr[i];
//                centerIndex = i;
//            }
//        }
        // 根据cs中回文半径和对应坐标算原字符串中的最大回文长度和最大回文中心
        // 原字符串最大回文长度：maxLen-1，比如#a#b#a#，b的回文半径=4，那么原aba的最长回文子串长度为3
        maxLen = maxLen - 1;
        // 原字符串最大回文串中心：(centerIndex - 1)/2，比如#a#b#a#，b的centerIndex=3；那么原aba的b的坐标为(3-1)/2
        centerIndex = (centerIndex - 1) / 2;
        // 根据centerIndex和maxLen算最大回文串begin下标
        // 奇数：centerIndex-maxLen/2
        // 偶数：centerIndex-maxLen/2+1
        // 统一：centerIndex-(maxLen-1)/2
        begin = centerIndex - (maxLen - 1) / 2;
        return s.substring(begin, begin + maxLen);
    }

    // 将str转换成带#号的字符数组:解决奇数、偶数中心往外扩的差异性
    public static char[] manacherString(String s, int n) {
        char[] charArr = s.toCharArray();
        int index = 0;// index遍历charArr
        // s:a -> res:#a#，长度1 -> 3，奇数位放#，偶数位放原字符
        // s:ab -> res:#a#b#，长度2 -> 5，奇数位放#，偶数位放原字符
        // s:aba -> res:#a#b#a#，长度3 -> 7，奇数位放#，偶数位放原字符
        // 长度变化规律:len -> len+len+1=len*2+1，奇数位放#，偶数位放原字符
        char[] res = new char[n * 2 + 1];
        for (int i = 0; i != res.length; i++) {
            // 偶数位放#，奇数位放原字符
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aacabdkacaa";
        System.out.println(longestPalindrome2(s));
        System.out.println("----------");
//        Set<String> set = new HashSet<>();
//        process(s, 0, set);
//        for (String str : set) {
//            System.out.println(str);
//        }
    }
}
