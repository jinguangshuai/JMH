package com.jgs.jmh.leetCode12_GraphBroad;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/10 - 05 - 10 - 8:54
 * @Description:com.jgs.jmh.leetCode12_GraphBroad
 * @version:1.0
 */

import java.util.*;

/**
 * * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 */
public class test96_minMutation {

    //自己解法：广度优先遍历
    public static int minMutation1(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene) && (null == bank || bank.length == 0)) {
            return -1;
        }
        int m = bank.length;
        Set<Character> set = new HashSet<>();
        set.add('A');
        set.add('G');
        set.add('C');
        set.add('T');
        boolean[] visited = new boolean[m];
        int differ = getDiffer(startGene, endGene, set);
        if (differ == 0) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int count = 0; count < size; count++) {
                String start = queue.poll();
                for (int i = 0; i < m; i++) {
                    if (startGene.equals(bank[i])) {
                        visited[i] = true;
                    }
                    if (!visited[i] && getDiffer(start, bank[i], set) == 1) {
                        queue.add(bank[i]);
                        visited[i] = true;
                    }
                }
            }
            index++;
            if (index > m) {
                return -1;
            }
            if (index >= differ) {
                if (!queue.isEmpty()) {
                    for (int i = 0; i < queue.size(); i++) {
                        String gene = queue.poll();
                        if (gene.equals(endGene)) {
                            return index;
                        }
                        queue.add(gene);
                    }
                }
            }
        }
        return -1;
    }

    //获取两个基因的相差多少个
    public static int getDiffer(String startGene, String nextGene, Set<Character> set) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            if (startGene.charAt(i) != nextGene.charAt(i)) {
                //验证基因的合法性
                if (set.contains(nextGene.charAt(i))) {
                    result++;
                } else {
                    return -1;
                }
            }
        }
        return result;
    }

    //官方广度优先遍历解法
    //验证字符的合法性
    //每个单词变换最多有24种变化，判断数组是否包含这24种变化
    public static int minMutation2(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) return 0;
        Set<String> count = new HashSet<>();
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        for (String gene : bank) {
            count.add(gene);
        }
        if (!count.contains(endGene)) return -1;
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        visited.add(startGene);
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != current.charAt(j)) {
                            StringBuilder sb = new StringBuilder(current);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && count.contains(next)) {
                                if (next.equals(endGene)) {
                                    return result;
                                }
                                queue.add(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            result++;
        }
        return -1;
    }

    //单词预处理的广度优先遍历
    //基本思路，
    // （1）预处理基因库的每个基因与其他基因的突变次数，记录每个基因相差一次的突变次数，并记录终止基因的位置
    //（2）找到基因库中与起始基因的相差一次的基因
    //（3）广度优先遍历，每次突变一次，记录突变的基因，直至找到结束基因
    public static int minMutation3(String startGene, String endGene, String[] bank) {
        int geneLength = startGene.length();
        int bankLength = bank.length;
        //记录基因库的合法变换
        List<Integer>[] adj = new List[bankLength];
        for (int i = 0; i < bankLength; i++) {
            adj[i] = new ArrayList<>();
        }
        int endIndex = -1;
        //双重for循环，判断
        for (int i = 0; i < bankLength; i++) {
            if (endGene.equals(bank[i])) {
                endIndex = i;
            }
            //遍历基因库的后续字符
            for (int j = i + 1; j < bankLength; j++) {
                int differ = 0;
                //遍历字符的差别
                for (int k = 0; k < geneLength; k++) {
                    if (bank[i].charAt(k) != bank[j].charAt(k)) {
                        differ++;
                    }
                    if (differ > 1) {
                        break;
                    }
                }
                //例如 i位置的基因可通过一次突变变为j位置的基因
                //同理 j位置的基因可通过一次突变变为i位置的基因
                if (differ == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1) return -1;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[bankLength];
        int result = 1;
        //找到与起始基因相差一次的突变基因
        for (int i = 0; i < bankLength; i++) {
            int differ = 0;
            for (int j = 0; j < geneLength; j++) {
                if (startGene.charAt(j) != bank[i].charAt(j)) {
                    differ++;
                }
                if (differ > 1) break;
            }
            if (differ == 1) {
                queue.add(i);
                visited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == endIndex) return result;
                for (int next : adj[current]) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    queue.add(next);
                }
            }
            result++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String startGene = "AACCGGTT";
        String endGene = "AACCGCTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println(minMutation1(startGene, endGene, bank));
    }
}
