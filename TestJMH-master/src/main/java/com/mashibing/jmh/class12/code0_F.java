package com.mashibing.jmh.class12;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/7 - 03 - 07 - 11:09
 * @Description:com.mashibing.jmh.class12
 * @version:1.0
 */

public class code0_F {
    //斐波那契数列结果   1 1 2 3 5 8   f(n) = f(n-1) + f(n-2)
    public static int getResult(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        return getResult(n-1) + getResult(n-2);

    }
}
