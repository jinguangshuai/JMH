package com.jgs.jmh.course.class10;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/24 - 01 - 24 - 9:41
 * @Description:com.mashibing.jmh.class10
 * @version:1.0
 */
public class test_BFS {

    public void bfs(Node node){
        if(null == node){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet set = new HashSet();
        stack.add(node);
        set.add(node);

        System.out.println(node.value);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            if(!cur.nexts.isEmpty()){
                for(Node next: cur.nexts){
                    if(!set.contains(next)){
                        stack.push(next);
                        set.add(next);
                    }
                }
            }
        }
    }
}
