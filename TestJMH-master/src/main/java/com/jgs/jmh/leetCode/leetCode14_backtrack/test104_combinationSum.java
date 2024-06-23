package com.jgs.jmh.leetCode.leetCode14_backtrack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/14 - 05 - 14 - 15:23
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class test104_combinationSum {
    //探索回溯
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == candidates || candidates.length == 0) {
            return result;
        }
        process(0, target, candidates, result, new ArrayList<>());
        return result;
    }
    public static void process(int index, int target, int[] candidates, List<List<Integer>> result, List<Integer> list) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        //不使用当前数字
        process(index + 1, target, candidates, result, list);
        //使用当前数字
        if (target - candidates[index] >= 0) {
            list.add(candidates[index]);
            process(index, target - candidates[index], candidates, result, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> list = combinationSum(candidates, target);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            System.out.println("-------------");
        }
    }
}
