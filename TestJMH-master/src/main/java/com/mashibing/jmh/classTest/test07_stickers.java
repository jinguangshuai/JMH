package com.mashibing.jmh.classTest;

import java.util.HashMap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/11 - 03 - 11 - 14:17
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test07_stickers {
    public static int getResult(String[] arr, String str) {
        if (null == arr || arr.length == 0 || " ".equals(str)) {
            return 0;
        }
        int[][] map = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            char[] cha = arr[i].toCharArray();
            for (int j = 0; j < cha.length; j++) {
                map[i][cha[j] - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(map, dp, str);
    }

    public static int process(int[][] help, HashMap<String, Integer> dp, String rest) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        int[] tmap = new int[26];
        char[] target = rest.toCharArray();
        for (int i = 0; i < target.length; i++) {
            tmap[target[i] - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < help.length; i++) {
            if (help[i][target[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - help[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String s = sb.toString();
            int tem = process(help, dp, s);
            if (tem != -1) {
                ans = Math.min(ans, 1 + tem);
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"bbbccc","ddgddg","gdnmfm"};
        String str = "bccccb";
        System.out.println(getResult(arr, str));
    }

}
