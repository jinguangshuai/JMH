package com.jgs.jmh.leetCode06_stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/12 - 04 - 12 - 17:15
 * @Description:com.jgs.jmh.leetCode06_stack
 * @version:1.0
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * <p>
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 */
public class test53_simplifyPath {
    //栈
    public static String simplifyPath(String path) {
        if (null == path || path.length() == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        String[] str = path.split("/");
        for (String s : str) {
            if (null != s && s.length() != 0) {
                if (s.equals("..") && !stack.isEmpty()) {
                    stack.pop();
                } else if (!".".equals(s) && !"..".equals(s)) {
                    stack.push(s);
                }
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return "" == result ? "/" : result;
    }

    //双端队列
    public static String simplifyPath1(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "/..";
        System.out.println(simplifyPath(s));
        System.out.println(simplifyPath1(s));
        String[] split = s.split("/");
        System.out.println(split);
    }
}
