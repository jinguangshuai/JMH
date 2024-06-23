package com.jgs.jmh.course.class10;

public class GraphGenerator {

	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	public static Graph createGraph(Integer[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) { // matrix[0][0], matrix[0][1]  matrix[0][2]
			Integer from = matrix[i][0];
			Integer to = matrix[i][1];
			Integer weight = matrix[i][2];
			//如果没有from的点就新建这个点
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			//如果没有to的点就新建这个点
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			//获取当前开始节点
			Node fromNode = graph.nodes.get(from);
			//获取当前结束节点
			Node toNode = graph.nodes.get(to);

			//创建边，包括边的权重，边的起始点，边的结束点
			Edge newEdge = new Edge(weight, fromNode, toNode);
			//fromNode的邻居节点+1
			fromNode.nexts.add(toNode);
			//fromNode的出度节点+1
			fromNode.out++;
			//toNode的入度节点+1
			toNode.in++;
			//newEdge作为fromNode的直接边
			fromNode.edges.add(newEdge);
			//newEdge加入到整个图结构
			graph.edges.add(newEdge);
		}
		return graph;
	}

}
