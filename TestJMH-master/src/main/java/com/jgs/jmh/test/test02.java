package com.jgs.jmh.test;

import java.lang.reflect.Field;

/**
 * @Auther：jinguangshuai
 * @Data：2024/6/7 - 06 - 07 - 15:08
 * @Description:com.jgs.jmh.test
 * @version:1.0
 */
public class test02 {

    //https://blog.csdn.net/WLQ0621/article/details/107573968
    //交换两个对象的值
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        System.out.println("开始" + a + "-" + b);
        swap(a,b);
        System.out.println("结束" + a + "-" + b);
    }
    public static void swap(Integer a,Integer b){
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            int temp = a;
            field.set(a,b);
            field.set(b,new Integer(temp));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
