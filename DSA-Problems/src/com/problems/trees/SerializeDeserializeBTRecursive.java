package com.problems.trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.TreeUtils;

public class SerializeDeserializeBTRecursive {

	public static void main(String[] args) {
		Node root = createExample();

		TreeUtils.printLevelOrder(root);

		String nodes = serialize(root);

		System.out.println(nodes);

		TreeUtils.printLevelOrder(deserialize(nodes));

	}

	static Node createExample() {
		Node root = new Node(11);
		root.left = new Node(19);
		root.right = new Node(3);
		root.left.left = new Node(19);
		root.left.right = new Node(17);
		root.right.left = new Node(2);
		root.right.right = new Node(2);
		root.left.left.left = new Node(5);
		root.right.right.right = new Node(10);
		root.left.left.left.left = new Node(8);
		return root;
	}

	public static String serialize(Node root) {
		StringBuilder tree = new StringBuilder();
		postorder(root, tree);
		return tree.toString();
	}

	private static void postorder(Node root, StringBuilder tree) {
		if (root == null) {
			tree.append("null").append(",");
			return;
		}
		tree.append(root.data).append(",");
		postorder(root.left, tree);
		postorder(root.right, tree);
	}

	public static Node deserialize(String data) {
		Queue<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(",")));
		return buildTree(nodes);
	}

	private static Node buildTree(Queue<String> nodes) {
		String node = nodes.poll();
		if (node.equals("null"))
			return null;
		else {
			Node root = new Node(Integer.valueOf(node));
			root.left = buildTree(nodes);
			root.right = buildTree(nodes);
			return root;
		}
	}
}
