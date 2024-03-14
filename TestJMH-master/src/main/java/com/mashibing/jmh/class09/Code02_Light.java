package com.mashibing.jmh.class09;

import java.util.HashSet;

public class Code02_Light {

	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	//str【index。。。】位置，自由选择放灯还是不放灯
	//str【0.。。index-1】位置呢？已经做完决定了。那些放了等位置存在lights里
	//返回要求选出能照亮所有。的方案，并且在这些有效的方案中，返回最少需要几个灯
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) {//所有处理结束时候
			for (int i = 0; i < str.length; i++) {
				//验证收集到的方案是否把所有位置都照亮
				if (str[i] != 'X') {//当前位置是点的话
					//判断是否当前点被照亮
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else {//str未结束
			//表示当前位置不放灯，后续放灯的最少次数（lights无任何操作）
			int no = process(str, index + 1, lights);

			int yes = Integer.MAX_VALUE;
			//当前位置开始放灯，后续位置的操作
			if (str[index] == '.') {
				//表示当前位置需要放灯了-lights.add(index);
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int minLight2(String road) {
		char[] str = road.toCharArray();
		int index = 0;
		int light = 0;
		while (index < str.length) {
			if (str[index] == 'X') {
				index++;
			} else {//i位置是个点
				light++;
				if (index + 1 == str.length) {
					break;
				} else {
					if (str[index + 1] == 'X') {
						//i+1为x，需要在i位置放灯，下一步需要去i+2位置做决定
						index = index + 2;
					} else {
						// i+1为点，需要在i+1位置放灯，则无需关注i+2位置是否为x还是点，
						// 因为i+2为x需要在i+1位置放灯，i+2位置为点依然需要在i+1位置放灯
						// 所以直接去i+3位置判断为x还是点。
						index = index + 3;
					}
				}
			}
		}
		return light;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight2(test);
			if (ans1 != ans2) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
