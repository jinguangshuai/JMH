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
