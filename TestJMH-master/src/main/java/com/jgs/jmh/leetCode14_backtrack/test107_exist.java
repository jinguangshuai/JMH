package com.jgs.jmh.leetCode14_backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/15 - 05 - 15 - 16:11
 * @Description:com.jgs.jmh.leetCode14_backtrack
 * @version:1.0
 */

/**
 * * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class test107_exist {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean exist1(char[][] board, String word) {
        if (null == board || board.length == 0) {
            return false;
        }
        Trie node = new Trie();
        node.insert(word);
        List<String> result = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (node.children.containsKey(board[row][col])) {
                    backtrack2(row, col, board, node, result);
                    if (!result.isEmpty())
                        return true;
                }
            }
        }
        return false;
    }

    public static void backtrack1(int row, int col, char[][] board, Trie node, List<String> result) {
        if (!node.children.containsKey(board[row][col])) {
            return;
        }
        char ch = board[row][col];
        node = node.children.get(ch);
        if (!"".equals(node.word)) {
            result.add(node.word);
        }
        board[row][col] = '*';
        for (int[] ints : dirs) {
            int nextRow = row + ints[0];
            int nextCol = col + ints[1];
            if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 & nextCol < board[0].length) {
                backtrack1(nextRow, nextCol, board, node, result);
            }
        }
        board[row][col] = ch;
    }

    //剪枝 + 回溯
    public static void backtrack2(int row, int col, char[][] board, Trie node, List<String> result) {
        if (!node.children.containsKey(board[row][col])) {
            return;
        }
        char ch = board[row][col];
        Trie next = node.children.get(ch);
        if (!"".equals(next.word)) {
            result.add(next.word);
            next.word = "";
        }
        if (!next.children.isEmpty()) {
            board[row][col] = '*';
            for (int[] ints : dirs) {
                int nextRow = row + ints[0];
                int nextCol = col + ints[1];
                if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 & nextCol < board[0].length) {
                    backtrack2(nextRow, nextCol, board, next, result);
                }
            }
            board[row][col] = ch;
        }
        if (next.children.isEmpty()) {
            node.children.remove(ch);
        }
    }

    public static class Trie {
        HashMap<Character, Trie> children;
        String word;

        public Trie() {
            children = new HashMap<>();
            word = "";
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new Trie());
                }
                node = node.children.get(ch);
            }
            node.word = word;
        }
    }


    public static boolean exist2(char[][] board, String word) {
        if (null == board || board.length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boolean flag = backtrack3(0, word, row, col, visited, board);
                if (flag) return true;
            }
        }
        return false;
    }

    public static boolean backtrack3(int index, String word, int row, int col, boolean[][] visited, char[][] board) {
        if (board[row][col] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }
        visited[row][col] = true;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean result = false;
        for (int[] ints : dirs) {
            int nextRow = row + ints[0];
            int nextCol = col + ints[1];
            if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 & nextCol < board[0].length) {
                if (!visited[nextRow][nextCol]) {
                    boolean flag = backtrack3(index + 1, word, nextRow, nextCol, visited, board);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[row][col] = false;
        return result;
    }


    public static boolean exist4(char[][] board, String word) {
        //O(3^K * M * N),K是word长度，M*N是数组总size
        char[] words = word.toCharArray();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if(board[row][col] == word.charAt(0)){
                    if (dfs4(board, words, row, col, 0)) return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs4(char[][] board, char[] words, int row, int col, int index) {
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0) return false;
        if (board[row][col] != words[index]) return false;
        if (index == words.length - 1) return true;
        //访问过标记，避免倒退查找
        board[row][col] = '*';
        boolean res = dfs4(board, words, row + 1, col, index + 1) || dfs4(board, words, row - 1, col, index + 1) ||
                dfs4(board, words, row, col + 1, index + 1) || dfs4(board, words, row, col - 1, index + 1);
        board[row][col] = words[index];
        return res;
    }


    public static void main(String[] args) {
        String word = "ABCCED";
        char[][] board = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        boolean exist = exist1(board, word);
        System.out.println(exist);
    }
}
