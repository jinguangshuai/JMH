package com.jgs.jmh.class10;

import java.util.*;

// undirected graph only
public class Code05_Prim {

	public static class Edge {
		public int weight;
		public Node from;
		public Node to;

		public Edge(int weight, Node from, Node to) {
			this.weight = weight;
			this.from = from;
			this.to = to;
		}

	}

	public class Graph {
		public HashMap<Integer, Node> nodes;
		public HashSet<Edge> edges;

		public Graph() {
			nodes = new HashMap<>();
			edges = new HashSet<>();
		}
	}

	public class Node {
		public int value;
		public int in;
		public int out;
		public ArrayList<Node> nexts;
		public ArrayList<Edge> edges;

		public Node(int value) {
			this.value = value;
			in = 0;
			out = 0;
			nexts = new ArrayList<>();
			edges = new ArrayList<>();
		}
	}

	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static Set<Edge> primMST(Graph graph) {
		// 解锁的边进入小根堆
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		//解锁的点放到set里面
		HashSet<Node> nodeSet = new HashSet<>();
		//已经考虑过的边不要重复考虑
		HashSet<Edge> edgeSet = new HashSet<>();
		//依次挑选的的边在result里
		Set<Edge> result = new HashSet<>();
		//防止出现多个图，组成森林，导致剩余的图无法遍历
		for (Node node : graph.nodes.values()) { // 随便挑了一个点
			// node 是开始点
			if (!nodeSet.contains(node)) {
				//第一个点位出发点，放到set里面
				nodeSet.add(node);
				for (Edge edge : node.edges) { // 由一个点，解锁所有相连的边
					//已经考虑过的边不重复考虑
					if(!edgeSet.contains(edge)){
						edgeSet.add(edge);
						priorityQueue.add(edge);
					}
				}
				while (!priorityQueue.isEmpty()) {
					Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
					Node toNode = edge.to; // 可能的一个新的点
					if (!nodeSet.contains(toNode)) { // 不含有的时候，就是新的点
						nodeSet.add(toNode);
						result.add(edge);
						//再由新的点解锁所有新的边
						for (Edge nextEdge : toNode.edges) {
							if(!edgeSet.contains(nextEdge)){
								edgeSet.add(nextEdge);
								priorityQueue.add(nextEdge);
							}
						}
					}
				}
			}
			//break;
		}
		return result;
	}

	// 请保证graph是连通图
	// graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
	// 返回值是最小连通图的路径之和
	public static int prim(int[][] graph) {
		int size = graph.length;
		int[] distances = new int[size];
		boolean[] visit = new boolean[size];
		visit[0] = true;
		for (int i = 0; i < size; i++) {
			distances[i] = graph[0][i];
		}
		int sum = 0;
		for (int i = 1; i < size; i++) {
			int minPath = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int j = 0; j < size; j++) {
				if (!visit[j] && distances[j] < minPath) {
					minPath = distances[j];
					minIndex = j;
				}
			}
			if (minIndex == -1) {
				return sum;
			}
			visit[minIndex] = true;
			sum += minPath;
			for (int j = 0; j < size; j++) {
				if (!visit[j] && distances[j] > graph[minIndex][j]) {
					distances[j] = graph[minIndex][j];
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println("hello world!");
	}

}
