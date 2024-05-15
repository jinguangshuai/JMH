package com.jgs.jmh.leetCode14_backtrack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/14 - 05 - 14 - 17:00
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 */
public class test104_combinationSum2 {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == candidates || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        process(0, target, candidates, result, new ArrayList<>());
        return result;
    }

    public static void process(int index, int target, int[] candidates, List<List<Integer>> result, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        //剪枝
        for (int i = index; i < candidates.length && target - candidates[i] >= 0; i++) {
            if(i > index && candidates[i-1] == candidates[i]){
                continue;
            }
            list.add(candidates[i]);
            process(i + 1, target - candidates[i], candidates, result, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        List<List<Integer>> list = combinationSum2(candidates, target);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
