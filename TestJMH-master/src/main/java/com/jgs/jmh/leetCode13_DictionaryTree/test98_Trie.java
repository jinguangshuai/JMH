package com.jgs.jmh.leetCode13_DictionaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/11 - 05 - 11 - 9:09
 * @Description:com.jgs.jmh.leetCode13_DictionaryTree
 * @version:1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word)
 * * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix)
 * * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class test98_Trie {

    //内存使用太大、耗时太长
    public class Trie1 {
        HashMap<String, List<String>> map;
        HashSet<String> set;
        public Trie1() {
            map = new HashMap<>();
            set = new HashSet<>();
        }
        public void insert(String word) {
            if (!set.contains(word)) {
                set.add(word);
                if (!map.containsKey(word)) {
                    char[] arr = word.toCharArray();
                    for (int i = 0; i < arr.length; i++) {
                        List<String> element = map.getOrDefault(word.substring(0, i), new ArrayList<>());
                        element.add(word.substring(0, i));
                        map.put(word.substring(0, i), element);
                    }
                    map.put(word,new ArrayList<>());
                }else {
                    List<String> element = map.get(word);
                    element.add(word);
                    map.put(word,element);
                }
            }
        }
        public boolean search(String word) {
            return set.contains(word);
        }
        public boolean startsWith(String prefix) {
            return map.containsKey(prefix);
        }
    }
    //官方解法 前缀树
    //主要思路
    //（1）新建多子树
    //（2）根据字符判断每个子树的位置
    //（3）在子树的结尾标记isEnd为true
    class Trie {
        Trie[] children;
        boolean isEnd;
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if(null == node.children[index]){
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return null != node && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return null != searchPrefix(prefix);
        }

        public Trie searchPrefix(String prefix){
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if(null == node.children[index]){
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }



    public static void main(String[] args) {
        Trie trie = new test98_Trie().new Trie();
        String word = "apple";
        String word1 = "app";
        trie.insert(word);
        System.out.println(trie.search(word));
        System.out.println(trie.search(word1));
        System.out.println(trie.startsWith(word1));
        trie.insert(word1);
        System.out.println(trie.search(word1));
    }

}
