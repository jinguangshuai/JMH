package com.jgs.jmh.leetCode.leetCode14_backtrack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/14 - 05 - 14 - 9:28
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 */
public class test102_combine {
    //标准回溯
    public static List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || k < 0) {
            return result;
        }
        process1(1, n, k, new ArrayList<>(), result);
        return result;
    }

    public static void process1(int index, int n, int k, List<Integer> element, List<List<Integer>> result) {
        if (element.size() == k) {
            result.add(new ArrayList<>(element));
            return;
        }
        //经典递归模板
        for (int i = index; i <= n; i++) {
            element.add(i);
            //经典递归模板 主要下次的为 i+1
            process1(i + 1, n, k, element, result);
            element.remove(element.size() - 1);
        }
    }

    //回溯+剪枝
    //回溯 + 剪枝
    //当前数字
    //（1）加入list（如果list的长度等于k，则加入list，并回溯，list.remove(list.size()-1)）  回溯
    //（2）不加入list（如果list的长度加上剩余数字的长度小于k,直接return）    剪枝
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1 || k < 0) {
            return result;
        }
        process2(1, n, k, result, new ArrayList<>());
        return result;
    }

    public void process2(int cur, int n, int k, List<List<Integer>> result, List<Integer> list) {
        //剪枝
        if (list.size() + (n - cur + 1) < k) {
            return;
        }
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        // 考虑当前位置  回溯
        list.add(cur);
        process2(cur + 1, n, k, result, list);
        list.remove(list.size() - 1);
        // 不考虑当前位置
        process2(cur + 1, n, k, result, list);
    }


    //非递归（字典序法）实现组合型枚举
    public static List<List<Integer>> combine3(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //初始化
        //将list中[0,k-1]每个位置i设置为i+1，即[0,k-1]存储[1,k]
        //末尾添加一位n+1作为哨兵
        for (int i = 1; i <= k; i++) {
            list.add(i);
        }
        list.add(n + 1);
        int j = 0;
        while (j < k) {
            result.add(new ArrayList<>(list.subList(0, k)));
            j = 0;
            //寻找第一个 temp[j]+1 != temp[j+1] 的位置t
            //我们需要把[0,t-1]区间内的每个位置重置为[1,t]
            while (j < k && list.get(j) + 1 == list.get(j + 1)) {
                list.set(j, j + 1);
                j++;
            }
            //j是第一个list[j] + 1 != list[j+1]的位置
            list.set(j, list.get(j) + 1);
        }
        return result;
    }


    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        List<List<Integer>> combine = combine1(n, k);
        for (int i = 0; i < combine.size(); i++) {
            List<Integer> list = combine.get(i);
            System.out.println("-----------");
            for (int num : list) {
                System.out.println(num);
            }
        }
    }
}
