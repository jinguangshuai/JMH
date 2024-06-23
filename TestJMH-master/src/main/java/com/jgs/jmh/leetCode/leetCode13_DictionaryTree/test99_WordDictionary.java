package com.jgs.jmh.leetCode.leetCode13_DictionaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/11 - 05 - 11 - 14:31
 * @Description:com.jgs.jmh.leetCode13_DictionaryTree
 * @version:1.0
 */

/**
 * * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；
 * * 否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 */
public class test99_WordDictionary {

    public static class WordDictionary {
        WordDictionary[] children;
        boolean isEnd;

        public WordDictionary() {
            children = new WordDictionary[26];
            isEnd = false;
        }

        public void addWord(String word) {
            WordDictionary node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (null == node.children[index]) {
                    node.children[index] = new WordDictionary();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            return search(this, word, 0);
        }

        //深度优先遍历
        public boolean search(WordDictionary node, String word, int start) {
            int n = word.length();
            if (start == n) {
                return node.isEnd;
            }
            char ch = word.charAt(start);
            if (ch != '.') {
                node = node.children[ch - 'a'];
                return null != node && search(node, word, start + 1);
            }
            for (int i = 0; i < 26; i++) {
                if (null != node.children[i] && search(node.children[i], word, start + 1)) {
                    return true;
                }
            }
            return false;
        }
        //宽度优先遍历
        public boolean bfs(String word, WordDictionary node) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch != '.') {
                    if (null == node.children[ch - 'a']) {
                        return false;
                    }
                    node = node.children[ch - 'a'];
                } else {
                    // 遍历,遇到不为null的节点就进入递归
                    for (int j = 0; j < 26; j++) {
                        if (null == node.children[j]) {
                            continue;
                        } else {
                            // 如果任何一个节点搜索到了结果,返回true
                            if (bfs(word.substring(i + 1, word.length()), node.children[j])) {
                                return true;
                            }
                        }
                    }
                    // 到最后都没搜索到,返回false
                    return false;
                }
            }
            return node.isEnd;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        System.out.println(wordDictionary.search("pad"));// 返回 False
//        System.out.println(wordDictionary.search("bad"));// 返回 True
//        System.out.println(wordDictionary.search(".ad"));// 返回 True
//        System.out.println(wordDictionary.search("b.."));// 返回 True

        wordDictionary.addWord("a");
        System.out.println(wordDictionary.search("a."));
    }

}
