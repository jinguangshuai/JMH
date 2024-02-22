package com.mashibing.jmh.class10;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	//点集  key编号为1，Node为具体的点是什么
	public HashMap<Integer, Node> nodes;
	//边集，所有的边集都在里面
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
