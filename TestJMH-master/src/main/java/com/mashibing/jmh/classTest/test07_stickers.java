package com.mashibing.jmh.classTest;

import java.util.HashMap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/11 - 03 - 11 - 14:17
 * @Description:com.mashibing.jmh.classTest
 * @version:1.0
 */
public class test07_stickers {
    public static int getResult(String[] sticker, String target) {
        if (" ".equals(target)) {
            return 0;
        }
        int[][] map = new int[sticker.length][26];
        for (int i = 0; i < sticker.length; i++) {
            char[] chars = sticker[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                map[i][chars[j] - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(map, target, dp);

    }

    public static int process(int[][] map, String rest, HashMap<String, Integer> dp) {

        if (dp.containsKey(rest)) {
            return dp.get(rest);

        }

        char[] tempChar = rest.toCharArray();
        int[] temp = new int[26];
        for (int i = 0; i < tempChar.length; i++) {
            temp[tempChar[i] - 'a']++;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if (map[i][tempChar[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (temp[j] > 0) {
                    for (int k = 0; k < Math.max(0, temp[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String s = sb.toString();
            int tmp = process(map, s, dp);
            if (tmp != -1) {
                ans = Math.min(ans, tmp + 1);
            }

        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"bbbccc", "ddgddg", "gdnmfm"};
        String str = "bccccb";
        System.out.println(getResult(arr, str));
    }

}
