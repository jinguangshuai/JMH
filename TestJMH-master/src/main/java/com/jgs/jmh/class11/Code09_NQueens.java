package com.jgs.jmh.class11;

public class Code09_NQueens {

	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		// record[0] ?  record[1]  ?  record[2]
		int[] record = new int[n]; // record[i] -> i行的皇后，放在了第几列
		return process1(0, record, n);
	}

	// 潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共行、不共列，不共斜线
	// 目前来到了第i行
	// record[0..i-1]表示之前的行，放了的皇后位置
	// n代表整体一共有多少行,如果下标从0开始，那么一共到n-1行，n-1列
	// 返回值是，摆完所有的皇后，合理的摆法有多少种
	public static int process1(int i, int[] record, int n) {
		if (i == n) { // 终止行
			return 1;
		}
		int res = 0;
		for (int j = 0; j < n; j++) { // 当前行在i行，尝试i行所有的列  -> j
			// 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列或者共斜线，
			// 如果是，认为有效
			// 如果不是，认为无效
			if (isValid(record, i, j)) {
				record[i] = j;//记录当前行i位置，j列的皇后
				res += process1(i + 1, record, n);
			}
		}
		return res;
	}

	// 判断第i行的皇后是否和 0~i-1行的皇后冲突
	// record[0..i-1]你需要看，record[i...]不需要看
	// 返回i行皇后，放在了j列，是否有效
	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) { // 之前的某个k行的皇后
			//之前的皇后位于 k行，record[k]列
			//判断方法：列是否相等 j == record[k]    是否在一条斜线上 Math.abs(record[k] - j) == Math.abs(i - k))
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

	// 请不要超过32皇后问题
	public static int num2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		//如果你是8皇后问题，limit最右8个1，其他都是0
		int limit = n == 32 ? -1 : (1 << n) - 1;
		return process2(limit, 0, 0, 0);
	}

	//limit划定了问题的规模 -》固定
	// colLim 列的限制，1的位置不能放皇后，0的位置可以
	// leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
	// rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
	public static int process2(int limit, 
			int colLim, 
			int leftDiaLim,
			int rightDiaLim) {
		if (colLim == limit) { // base case
			return 1;
		}
		// 所有可以放皇后的位置，都在pos上，每个1都是可以放皇后的位置
		//colLim | leftDiaLim | rightDiaLim  代表总限制
		// ~(colLim | leftDiaLim | rightDiaLim) 左侧的一堆0是干扰的东西，右侧的每个1是可以尝试的位置
		// limit & (~(colLim | leftDiaLim | rightDiaLim)) 只要为1都可以放置皇后

		// &  相同0为0，相同1为1,1和0则为0
		// | 相同0为0，相同1为1,1和0则为1
		// ~  0变成1,1变成0
		int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
		int mostRightOne = 0;
		int res = 0;
		while (pos != 0) {
			//单独取出最右侧位置的1，剩下位置都是0，计算方式为 自己取反+1在与自身计算
			mostRightOne = pos & (~pos + 1);
			//验证完成去除最右侧的1
			pos = pos - mostRightOne;
			res += process2(limit,
					//列限制与当前放皇后位置进行或运算
					colLim | mostRightOne,
					//左斜线限制与当前放皇后位置进行或运算
					(leftDiaLim | mostRightOne) << 1,
					//右斜线限制与当前放皇后位置进行或运算
					(rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 14;

		long start = System.currentTimeMillis();
		System.out.println(num2(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

		start = System.currentTimeMillis();
		System.out.println(num1(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

	}
}
