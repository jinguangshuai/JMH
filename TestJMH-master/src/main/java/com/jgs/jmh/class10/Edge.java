package com.jgs.jmh.class10;

public class Edge {
	//边上的权重
	public int weight;
	//边的初始方向
	public Node from;
	//边的终止方向
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}
