package com.mashibing.jmh.class09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther：jinguangshuai
 * @Data：2024/1/12 - 01 - 12 - 9:26
 * @Description:com.mashibing.jmh.class09
 * @version:1.0
 */
public class test01_lowString {

    public static class myCompator implements Comparator<String> {
        @Override
        public int compare(String a,String b){
            return (a+b).compareTo(b+a);
        }
    }

    public static String getLowString(String[] strings){
        if (null == strings){
            return "";

        }
        Arrays.sort(strings,new myCompator());
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            result += strings[i]+",";
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"ab","ca","ba","sk","bb","a","b","k"};
        System.out.println(getLowString(strings));
    }


}
