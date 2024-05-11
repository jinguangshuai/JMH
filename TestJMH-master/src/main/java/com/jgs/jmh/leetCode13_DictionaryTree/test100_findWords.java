package com.jgs.jmh.leetCode13_DictionaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/11 - 05 - 11 - 16:58
 * @Description:com.jgs.jmh.leetCode13_DictionaryTree
 * @version:1.0
 */

import java.util.List;

/**
 * * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 *
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * * 同一个单元格内的字母在一个单词中不允许被重复使用。
 */
public class test100_findWords {

    public List<String> findWords(char[][] board, String[] words) {
        //构建字典树（递归）
        //根据单词前缀获取是否可构成单词
        //最后返回结果
        return null;
    }

    public class Trie{
        Trie[] children;
        public Trie(){
            children = new Trie[26];
        }
        public void insert(String word) {
            
        }
    }
}
