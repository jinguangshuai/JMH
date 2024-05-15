package com.jgs.jmh.leetCode12_GraphBroad;

import java.util.*;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/10 - 05 - 10 - 14:23
 * @Description:com.jgs.jmh.leetCode12_GraphBroad
 * @version:1.0
 */

/**
 * * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * <p>
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 */
public class test97_ladderLength {
    //单词预处理的广度优先遍历
    //基本思路，
    //（1）预处理单词集的每个单词与其他单词的相差次数，记录每个单词相差一次的单词下标存入数组，并记录终止单词的位置
    //（2）找到单词集中与起始单词的相差一次的单词
    //（3）广度优先遍历，每次单词变化一次，记录变化的单词，直至找到结束单词
    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 2;
        if (null == wordList || wordList.size() == 0 || !wordList.contains(endWord)) return 0;
        int wordLength = beginWord.length();
        int listSize = wordList.size();
        List<Integer>[] arr = new List[listSize];
        for (int i = 0; i < listSize; i++) {
            arr[i] = new ArrayList<>();
        }
        int endIndex = -1;
        //记录单词与剩余单词相差一次的集合
        for (int i = 0; i < listSize; i++) {
            if (wordList.get(i).equals(endWord)) {
                endIndex = i;
            }
            if (wordList.get(i).equals(beginWord)) continue;
            for (int j = i + 1; j < listSize; j++) {
                int differ = 0;
                for (int k = 0; k < wordLength; k++) {
                    if (wordList.get(i).charAt(k) != wordList.get(j).charAt(k)) {
                        differ++;
                    }
                    if (differ > 1) break;
                }
                if (differ == 1) {
                    arr[i].add(j);
                    arr[j].add(i);
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[listSize];
        //获取单词集合中与起始单词相差一次的单词
        for (int i = 0; i < listSize; i++) {
            int differ = 0;
            for (int j = 0; j < wordLength; j++) {
                if (beginWord.charAt(j) != wordList.get(i).charAt(j)) {
                    differ++;
                }
                if (differ > 1) break;
            }
            if (differ == 1) {
                queue.add(i);
                visited[i] = true;
            }
        }
        //广度优先遍历
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                //result为变换的次数，总共的单词数为 变化次数+1
                if (current == endIndex) return result + 1;
                for (int num : arr[current]) {
                    if (visited[num]) continue;
                    queue.add(num);
                    visited[num] = true;
                }
            }
            result++;
        }
        return 0;
    }

    //广度优先遍历+优先成图
    //基本思路
    //（1）利用图的构建方式，构建每个单词与虚拟单词的联系。例如hit的虚拟单词（*it h*t hi*）
    //（2）新建dis数组，记录从起始位置（beginId）到终止位置单词(endId)的长度
    //（3）由于添加虚拟节点，故长度需要除以2，由于未添加起始单词的贡献，因为我们需添加起始单词
    static Map<String, Integer> map = new HashMap<>();
    static List<List<Integer>> edges = new ArrayList<>();
    static int nodeNum = 0;
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!map.containsKey(endWord)) return 0;
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = map.get(beginWord), endId = map.get(endWord);
        dis[beginId] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(beginId);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            //因为添加了虚拟节点，所以我们得到的距离为实际最短路径长度的两倍。
            //同时我们并未计算起点对答案的贡献，所以我们应当返回距离的一半再加一的结果。
            if (x == endId) return dis[endId] / 2 + 1;
            for (int it : edges.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    queue.add(it);
                }
            }
        }
        return 0;
    }

    public static void addEdge(String word) {
        addWord(word);
        int id1 = map.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            char temp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = map.get(newWord);
            edges.get(id1).add(id2);
            edges.get(id2).add(id1);
            array[i] = temp;
        }
    }

    public static void addWord(String word) {
        if (!map.containsKey(word)) {
            map.put(word, nodeNum++);
            edges.add(new ArrayList<>());
        }
    }

    //双向广度优先遍历
    //广度优先遍历+优先成图
    //基本思路
    //（1）利用图的构建方式，构建每个单词与虚拟单词的联系。例如hit的虚拟单词（*it h*t hi*）
    //（2）新建disBegin数组，disEnd数组，
    // 记录从disBegin数组起始位置（beginId）到终止位置单词(endId)的长度
    // 记录从disEnd数组起始位置（endId）到终止位置单词(beginId)的长度
    //一旦两者相遇，即disBegin[nodeId] != Integer.MAX_VALUE 并且 disEnd[nodeId] != Integer.MAX_VALUE
    //（3）由于添加虚拟节点，长度为（disBegin[nodeId] + disEnd[nodeId]）/2 +1 或者
    //（disEnd[nodeId] + disBegin[nodeId]）/2 +1
    // 由于未添加起始单词的贡献，因为我们需添加起始单词
    static Map<String, Integer> newMap = new HashMap<>();
    static List<List<Integer>> newEdges = new ArrayList<>();
    static int newNodeNum = 0;
    public static int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            newAddEdges(word);
        }
        newAddEdges(beginWord);
        if (!newMap.containsKey(endWord)) return 0;

        //起始队列
        int[] disBegin = new int[newNodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = newMap.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queueBegin = new LinkedList<>();
        queueBegin.add(beginId);

        //终止队列
        int[] disEnd = new int[newNodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = newMap.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queueEnd = new LinkedList<>();
        queueEnd.add(endId);

        while (!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
            //起始队列
            int queueBeginSize = queueBegin.size();
            //横向遍历
            for (int i = 0; i < queueBeginSize; i++) {
                int node = queueBegin.poll();
                //当发现某一时刻两边都访问过同一顶点时就停止搜索
                //如果结束队列已经遍历到开始节点，则说明已经遍历完毕，结果为两个队列的开始值之和除以2 + 1
                if (disEnd[node] != Integer.MAX_VALUE) {
                    return (disBegin[node] + disEnd[node]) / 2 + 1;
                }
                for (int it : newEdges.get(node)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[node] + 1;
                        queueBegin.add(it);
                    }
                }
            }
            //反向队列，从结束位置开始遍历到开始位置
            int queueEndSize = queueEnd.size();
            for (int i = 0; i < queueEndSize; i++) {
                int node = queueEnd.poll();
                //当发现某一时刻两边都访问过同一顶点时就停止搜索
                //如果开始队列已经遍历到终止节点，则说明已经遍历完毕，结果为两个队列的结束值之和除以2 + 1
                if (disBegin[node] != Integer.MAX_VALUE) {
                    return (disBegin[node] + disEnd[node]) / 2 + 1;
                }
                for(int it : newEdges.get(node)){
                    if(disEnd[it] == Integer.MAX_VALUE){
                        disEnd[it] = disEnd[node] + 1;
                        queueEnd.add(it);
                    }
                }
            }
        }
        return 0;
    }

    public static void newAddEdges(String word) {
        newAddWord(word);
        int id1 = newMap.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            char temp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            newAddWord(newWord);
            int id2 = newMap.get(newWord);
            newEdges.get(id1).add(id2);
            newEdges.get(id2).add(id1);
            array[i] = temp;
        }
    }

    public static void newAddWord(String word) {
        if (!newMap.containsKey(word)) {
            newMap.put(word, newNodeNum++);
            newEdges.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(ladderLength3(beginWord, endWord, wordList));
    }
}
