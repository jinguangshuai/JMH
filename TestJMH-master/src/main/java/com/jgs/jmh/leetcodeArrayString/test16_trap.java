package com.jgs.jmh.leetcodeArrayString;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/20 - 03 - 20 - 16:15
 * @Description:com.mashibing.jmh.leetcode
 * @version:1.0
 */

/**
 * *给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 */

public class test16_trap {

    //双数组做法
    public static int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int result = 0;
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }
        return result;
    }

    //单调栈做法
    public static int trap2(int[] height) {
        int ans = 0;//总雨水量
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            //这里是在干这样一件事情：把当前的这根柱子作为“右柱”，把栈顶的元素作为“中间柱”也叫“接水柱”
            //（此时还没弹栈），然后把“接水柱”前面的那个柱子，作为“左柱”，有了“左柱”和“右柱”，
            //咱们的“接水柱”就能接水了，但是它只能接到左右两边更低的那个柱子高度的水。
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                //上面这个式子说明：“右柱”比栈顶也就是“接水柱”更高，这样的话才能准备接水。
                //否则的话，就是满足单调递减栈的，那么我们继续入栈。
                int top = stack.pop();//拿出前一个柱子
                if (stack.isEmpty()) break;//如果拿出这根柱子后，前面没有元素了，那就接不了雨水了，因为接雨水的话，至少需要左右两边都有柱子才行。
                int left = stack.peek();//记录一下拿到的这根柱子的左边那根柱子的高度
                int currWidth = i - left - 1;//看图推算。
                //上面这个式子有人会说：不都是1吗？其实不是的，假如我们连续加入两个0高度的柱子（有点奇怪），
                //这个时候，不符合单调栈的定义，那么我们会弹出一个栈，但是由于高度为0，我们也不会因此得到更多的面积，
                //因为s = h * w； 不过，这个时候你会发现，中间空出来了一个，准确的说是两格，
                //因为前面还有一个0高度的柱子，那么我们下次找到“右柱”的时候就会发现：这个宽度并非是1，
                //而是隔开了一定的距离，这个距离和下标有关，看图稍加推导得出距离为：i - left - 1；
                int currHeight = Math.min(height[left], height[i]) - height[top];//用左右两边更小的柱子来接雨水（木桶原理）
                ans += currWidth * currHeight;//记录本次所接的雨水量
            }
            stack.push(i);//经过上面一顿操作之后，咱们的栈又满足单调性了，于是将当前元素的下标入栈。
        }
        return ans;
    }


    public static int trap3(int[] height) {
        int n = height.length;
        int left = 0,right = n-1;
        int leftMax = 0,rightMax = 0;
        int result = 0;
        while(left < right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);

            if(height[left] < height[right] ){
                result += leftMax - height[left];
                left++;
            }else{
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }




    public static void main(String[] args) {
        int[] arr = {4,2,0,3,2,5};
        System.out.println(trap2(arr));
    }
}
