package com.mashibing.jmh.leetcodeArrayString;

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
 实现RandomizedSet 类：

 RandomizedSet() 初始化 RandomizedSet 对象
 bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
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
