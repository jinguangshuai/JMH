package com.jgs.jmh.class10;

import java.util.ArrayList;

//点结构的描述
public class Node {
	//这个点的值
	public int value;
	//从这个点进去的点有多少
	public int in;
	//从这个点出去的点有多少
	public int out;
	//直接邻居
	public ArrayList<Node> nexts;
	//边结构
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
