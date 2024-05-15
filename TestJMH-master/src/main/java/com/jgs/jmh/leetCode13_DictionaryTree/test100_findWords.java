package com.jgs.jmh.leetCode13_DictionaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/11 - 05 - 11 - 16:58
 * @Description:com.jgs.jmh.leetCode13_DictionaryTree
 * @version:1.0
 */

import java.util.*;

/**
 * * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * * 同一个单元格内的字母在一个单词中不允许被重复使用。
 */
public class test100_findWords {
    //（1）根据待处理的字符串构建前缀树
    //（2）判断矩阵内是否存在前缀树，如果存在，进行记录
    //（3）返回结果
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static List<String> findWords1(char[][] board, String[] words) {
        Trie1 trie = new Trie1();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> ans = new HashSet<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                dfs3(row, col, board, trie, ans);
            }
        }
        return new ArrayList<>(ans);
    }
    //回溯+字典树
    public static void dfs1(int row, int col, char[][] board, Trie1 now, Set<String> ans) {
        if (!now.children.containsKey(board[row][col])) {
            return;
        }
        char ch = board[row][col];
        now = now.children.get(ch);
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }
        board[row][col] = '*';
        for (int[] ints : dirs) {
            int nextRow = row + ints[0];
            int nextCol = col + ints[1];
            if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length) {
                dfs1(nextRow, nextCol, board, now, ans);
            }
        }
        //回溯
        board[row][col] = ch;
    }

    //删除被匹配的单词
    //将匹配到的单词从前缀树移除，避免寻找重复的单词
    public static void dfs3(int row, int col, char[][] board, Trie1 now, Set<String> ans) {
        if (!now.children.containsKey(board[row][col])) {
            return;
        }
        char ch = board[row][col];
        Trie1 next = now.children.get(ch);
        if (!"".equals(next.word)) {
            ans.add(next.word);
            //将匹配到的单词从前缀树移除，避免寻找重复的单词
            next.word = "";
        }
        if(!next.children.isEmpty()){
            board[row][col] = '*';
            for (int[] ints : dirs) {
                int nextRow = row + ints[0];
                int nextCol = col + ints[1];
                if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length) {
                    dfs3(nextRow, nextCol, board, now, ans);
                }
            }
            //回溯
            board[row][col] = ch;
        }
        //如果节点没有接下来的值，删除父节点的值
        if(next.children.isEmpty()){
            now.children.remove(ch);
        }
    }

    public static class Trie1 {
        String word;
        Map<Character, Trie1> children;

        public Trie1() {
            this.word = "";
            this.children = new HashMap<>();
        }

        public void insert(String word) {
            Trie1 node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new Trie1());
                }
                node = node.children.get(ch);
            }
            //记录当前路径单词为word
            node.word = word;
        }
    }



    //（1）根据待处理的字符串构建前缀树
    //（2）判断矩阵内是否存在前缀树，如果存在，进行记录
    //（3）返回结果
    public static List<String> findWords2(char[][] board, String[] words) {
        Trie2 trie = new Trie2();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> ans = new HashSet<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                dfs2(row, col, board, trie, ans);
            }
        }
        return new ArrayList<>(ans);
    }

    public static void dfs2(int row, int col, char[][] board, Trie2 node, Set<String> ans) {
        char ch = board[row][col];
        if (ch == '*') return;
        int index = ch - 'a';
        if (null == node.children[index]) {
            return;
        }
        node = node.children[index];
        if (node.isEnd) {
            ans.add(node.word);
        }
        board[row][col] = '*';
        for (int[] ints : dirs) {
            int nextRow = row + ints[0];
            int nextCol = col + ints[1];
            if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length) {
                dfs2(nextRow, nextCol, board, node, ans);
            }
        }
        board[row][col] = ch;
    }

    public static class Trie2 {
        Trie2[] children;
        boolean isEnd;
        String word;

        public Trie2() {
            this.children = new Trie2[26];
            this.isEnd = false;
            this.word = "";
        }

        public void insert(String word) {
            Trie2 node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (null == node.children[index]) {
                    node.children[index] = new Trie2();
                }
                node = node.children[index];
            }
            node.isEnd = true;
            node.word = word;
        }
    }



    static Trie trie = new Trie();
    public static List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            trie.insert(word);
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (trie.children[board[i][j] - 'a'] != null) {
                    dfs(board, i, j, trie.children[board[i][j] - 'a'], res);
                }
            }
        }
        return res;
    }

    public static void dfs(char[][] board, int i, int j, Trie node, List<String> res) {
        if (node.isEnd) {
            res.add(node.word);
            trie.remove(node.word);
        }
        char c = board[i][j];
        //标记已使用过的字符 'z' +1 = 122 + 1
        //board[i][j] - 'a' = 123 - 97 = 26,26位置为空
        board[i][j] = 'z' + 1;
        if (i > 0 && node.children[board[i - 1][j] - 'a'] != null) {
            dfs(board, i - 1, j, node.children[board[i - 1][j] - 'a'], res);
        }
        if (i < board.length - 1 && node.children[board[i + 1][j] - 'a'] != null) {
            dfs(board, i + 1, j, node.children[board[i + 1][j] - 'a'], res);
        }
        if (j > 0 && node.children[board[i][j - 1] - 'a'] != null) {
            dfs(board, i, j - 1, node.children[board[i][j - 1] - 'a'], res);
        }
        if (j < board[i].length - 1 && node.children[board[i][j + 1] - 'a'] != null) {
            dfs(board, i, j + 1, node.children[board[i][j + 1] - 'a'], res);
        }
        board[i][j] = c;
    }

    static class Trie {
        Trie[] children = new Trie[27];
        boolean isEnd = false;
        String word;
        public void insert(String word) {
            Trie cur = this;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Trie();
                }
                cur = cur.children[c - 'a'];
            }
            cur.isEnd = true;
            cur.word = word;
        }

        public boolean search(String word) {
            Trie cur = this;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                if (cur.children[c - 'a'] == null) {
                    return false;
                }
                cur = cur.children[c - 'a'];
            }
            return cur.isEnd;
        }

        public void remove(String word) {
            Trie cur = this;
            char[] cs = word.toCharArray();
            Trie[] stack = new Trie[cs.length];
            for (int i = 0; i < cs.length; i++) {
                if (cur.children[cs[i] - 'a'] == null) {
                    return;
                }
                stack[i] = cur;
                cur = cur.children[cs[i] - 'a'];
            }
            cur.isEnd = false;
            for (Trie trie : cur.children) {
                if (trie != null) {
                    return;
                }
            }
            for (int i = cs.length - 1; i >= 0 ; i--) {
                cur = stack[i];
                cur.children[cs[i] - 'a'] = null;
                for (Trie trie : cur.children) {
                    if (trie != null) {
                        return;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> list = findWords(board, words);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
