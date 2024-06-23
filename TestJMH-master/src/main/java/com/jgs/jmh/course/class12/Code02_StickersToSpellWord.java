package com.jgs.jmh.course.class12;

import java.util.Arrays;
import java.util.HashMap;

public class Code02_StickersToSpellWord {

	public static int minStickers1(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		//将数组的每个String的字母的数字记录在二维数组中
		//map[i][c - 'a']++; 0位置上为a，1位置上为b
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}

		HashMap<String, Integer> dp = new HashMap<>();
		//如果为空字符，则返回0张贴纸
		dp.put("", 0);
		return process1(dp, map, target);
	}

	// dp 傻缓存，如果t已经算过了，直接返回dp中的值
	// t 剩余的目标
	// 0..N每一个字符串所含字符的词频统计
	//如果返回值为-1，map中的贴纸是无法拼装为rest
	public static int process1(HashMap<String, Integer> dp, int[][] map, String t) {
		if (dp.containsKey(t)) {
			return dp.get(t);
		}

		//ans为使用最少的贴纸数量
		int ans = Integer.MAX_VALUE;
		//一共有n钟贴纸
		int n = map.length;

		//tmap去替代target剩余的主字符串
		int[] tmap = new int[26];
		char[] target = t.toCharArray();
		for (char c : target) {
			tmap[c - 'a']++;
		}

		for (int i = 0; i < n; i++) {
			//枚举当前第i张贴纸

			//如果无词频，则表示当前字符不包含目标字符的任何字符  比如贴纸为xyz，目标字符为abc，则会导致栈溢出
			// target[0]位置的字符，当前贴纸有没有，如果有的话才有试的必要
			// 如果target[0] - 'a' = 0，则表示target[0]为a字符，第i个数组的0位置判断是否包含a,如果等于0则表示不包含，停止当前循环，进行下一次循环
			// 如果target[0] - 'a' = 2，则表示target[0]为c字符，第i个数组的2位置判断是否包含c，如果等于0则表示不包含，停止当前循环，进行下一次循环
			// 当前贴纸必须包含目标字符的某个字符，才有试的必要，否则无需遍历
			if (map[i][target[0] - 'a'] == 0) {
				continue;
			}

			//新建一个StringBuilder，tmap[j] - map[i][j]表示剩余多少字符
			StringBuilder sb = new StringBuilder();
			//当前来到i贴纸， j在枚举从a-z的字符
			for (int j = 0; j < 26; j++) {
				if (tmap[j] > 0) { // j这个字符是target需要的
					for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
						sb.append((char) ('a' + j));
					}
				}
			}
			//t经过一次贴纸之后的剩余为s
			String s = sb.toString();
			int tmp = process1(dp, map, s);
			if (tmp != -1) {
				//1+tmp为第一次遍历使用的贴纸
				ans = Math.min(ans, 1 + tmp);
			}
		}

		//ans为最大值，则将当前方案赋值为-1，标识无贴纸方案组成t
		dp.put(t, ans == Integer.MAX_VALUE ? -1 : ans);
		return dp.get(t);
	}

	public static int minStickers2(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		char[] str = target.toCharArray();
		int[] tmap = new int[26];
		for (char c : str) {
			tmap[c - 'a']++;
		}
		HashMap<String, Integer> dp = new HashMap<>();
		int ans = process2(map, 0, tmap, dp);
		return ans;
	}

	public static int process2(int[][] map, int i, int[] tmap, HashMap<String, Integer> dp) {
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(i + "_");
		for (int asc = 0; asc < 26; asc++) {
			if (tmap[asc] != 0) {
				keyBuilder.append((char) (asc + 'a') + "_" + tmap[asc] + "_");
			}
		}
		String key = keyBuilder.toString();
		if (dp.containsKey(key)) {
			return dp.get(key);
		}
		boolean finish = true;
		for (int asc = 0; asc < 26; asc++) {
			if (tmap[asc] != 0) {
				finish = false;
				break;
			}
		}
		if (finish) {
			dp.put(key, 0);
			return 0;
		}
		if (i == map.length) {
			dp.put(key, -1);
			return -1;
		}
		int maxZhang = 0;
		for (int asc = 0; asc < 26; asc++) {
			if (map[i][asc] != 0 && tmap[asc] != 0) {
				maxZhang = Math.max(maxZhang, (tmap[asc] / map[i][asc]) + (tmap[asc] % map[i][asc] == 0 ? 0 : 1));
			}
		}
		int[] backup = Arrays.copyOf(tmap, tmap.length);
		int min = Integer.MAX_VALUE;
		int next = process2(map, i + 1, tmap, dp);
		tmap = Arrays.copyOf(backup, backup.length);
		if (next != -1) {
			min = next;
		}
		for (int zhang = 1; zhang <= maxZhang; zhang++) {
			for (int asc = 0; asc < 26; asc++) {
				tmap[asc] = Math.max(0, tmap[asc] - (map[i][asc] * zhang));
			}
			next = process2(map, i + 1, tmap, dp);
			tmap = Arrays.copyOf(backup, backup.length);
			if (next != -1) {
				min = Math.min(min, zhang + next);
			}
		}
		int ans = min == Integer.MAX_VALUE ? -1 : min;
		dp.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {
		String[] arr = new String[]{"bbbccc","ddgddg","gdnmfm"};
		String str = "bccccb";
		System.out.println(minStickers1(arr, str));
	}

}
