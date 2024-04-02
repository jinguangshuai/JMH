package com.jgs.jmh.class08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code07_lowestAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node lowestAncestor1(Node head, Node o1, Node o2) {
		if (head == null) {
			return null;
		}
		//key的父节点就是value
		HashMap<Node, Node> parentMap = new HashMap<>();
		parentMap.put(head, null);

		//填充所有节点的父节点
		fillParentMap(head, parentMap);

		HashSet<Node> o1Set = new HashSet<>();
		//将o1所有的节点的父节点填充到set
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		//遍历o2节点的父节点，看那个节点包含相同的父节点
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		return cur;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	public static Node lowestAncestor2(Node head, Node o1, Node o2) {
		return process(head, o1, o2).ans;
	}

	//任何子树，o1和o2最初的交互点，在这颗子树上是否有o1和o2
	public static class Info {
		public Node ans;
		public boolean findO1;
		public boolean findO2;

		public Info(Node a, boolean f1, boolean f2) {
			ans = a;
			findO1 = f1;
			findO2 = f2;
		}
	}

	public static Info process(Node head, Node o1, Node o2) {
		if (head == null) {
			return new Info(null, false, false);
		}
		Info leftInfo = process(head.left, o1, o2);
		Info rightInfo = process(head.right, o1, o2);
		//判断左子树右子树上是否包含o1和o2
		boolean findO1 = head == o1 || leftInfo.findO1 || rightInfo.findO1;
		boolean findO2 = head == o2 || leftInfo.findO2 || rightInfo.findO2;
		//获取o1和o2最初的交汇点
		Node ans = null;
		//左子树发现交汇点，则最低祖先为左子树的ans
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		}
		//右子树发现交汇点，则最低祖先为右子树的ans
		if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		}
		if (ans == null) {
			//如果左子树右子树都没有交汇点并且该二叉树存在o1和o2，那交汇点为头结点
			if (findO1 && findO2) {
				ans = head;
			}
		}
		return new Info(ans, findO1, findO2);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// for test
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
