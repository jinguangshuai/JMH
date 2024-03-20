package com.mashibing.jmh.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/19 - 03 - 19 - 11:22
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请不要要使用除法，且在 O(n) 时间复杂度内完成此题。
 */
public class test12_RandomizedSet {

    public static HashMap<Integer,Integer> map = new HashMap();
    public static List<Integer> list = new ArrayList();
    public static int end = 0;

    public test12_RandomizedSet() {

    }

    public static boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            list.add(val);
            map.put(val, end);
            end++;
            return true;
        }

    }

    public static boolean remove(int val) {
        if (map.containsKey(val)) {
            int m = map.get(val);
            if(m != end-1){
                int temp = list.get(m);
                list.set(m, list.get(end-1));
                list.set(end-1, temp);
                map.put(list.get(m),m);
            }
            list.remove(end-1);
            map.remove(val);
            if (end > 0) {
                end--;
            }
            return true;
        } else {
            return false;
        }
    }

    public static int getRandom() {
        int m = list.get((int) (Math.random() * (end)));
        return m;
    }

    public static void main(String[] args) {
        System.out.println(insert(0));
        System.out.println(insert(1));
        System.out.println(remove(0));
        System.out.println(insert(2));
        System.out.println(remove(1));
        System.out.println(getRandom());
    }

}
