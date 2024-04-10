package com.jgs.jmh.leetCode04_Hash;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/10 - 04 - 10 - 8:59
 * @Description:com.jgs.jmh.leetCode04_Hash
 * @version:1.0
 */

import java.util.HashSet;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」 定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 */
public class test45_isHappy {
    //利用各个位数的结果存储在hashset中，判断计算结果是否重复
    public static boolean isHappy(int n) {
        int init = n;
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        while (true) {
            String s = Integer.toString(init);
            for (char c : s.toCharArray()) {
                sum += Character.getNumericValue(c) * Character.getNumericValue(c);
            }
            init = sum;
            if (!set.contains(sum)) {
                set.add(sum);
                sum = 0;
            } else {
                break;
            }
        }
        if (set.contains(1)) {
            return true;
        }
        return false;
    }

    //优化版 利用各个位数的结果存储在hashset中，判断计算结果是否重复
    public static boolean isHappy1(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        while (true) {
            while (n != 0) {
                int i = n % 10;
                sum += i * i;
                n = n / 10;
            }
            if (!set.contains(sum)) {
                if (sum == 1) return true;
                set.add(sum);
                n = sum;
                sum = 0;
            } else {
                break;
            }
        }
        return false;
    }


    //快慢指针  优化
    public static boolean isHappy2(int n) {
        HashSet<Integer> set = new HashSet<>();
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    public static int getNext(int n) {
        int sum = 0;
        while (n != 0) {
            int i = n % 10;
            n = n / 10;
            sum += i * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy(n));
        System.out.println(isHappy1(n));
        System.out.println(isHappy2(n));
        System.out.println("-------");
    }


}
