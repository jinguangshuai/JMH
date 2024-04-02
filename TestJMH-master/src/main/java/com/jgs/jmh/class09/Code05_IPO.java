package com.jgs.jmh.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {

	//k表示做多少个项目
	//W表示初始资金
	//返回值为最后获得的最大钱数
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		//小跟堆加入所有项目
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
		//将符合初始资金的项目挑选利润最大的项目
		for (int i = 0; i < K; i++) {
			//小根堆符合要求的扔到大根堆
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			//无项目可做或者项目金额过大导致无法去做，提前返回最大钱数
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			//获得大根堆最大利润的值
			W += maxProfitQ.poll().p;
		}
		return W;
	}

	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	//小根堆
	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}
	//大根堆
	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
