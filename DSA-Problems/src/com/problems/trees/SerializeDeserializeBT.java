package com.problems.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.TreeUtils;

public class SerializeDeserializeBT {

	public static void main(String[] args) {

//		int[] arr = ArrayUtils.getIntArray(10);
//
//		Node root = TreeUtils.createBinaryTree(arr);

		Node root = createExample();

		TreeUtils.printLevelOrder(root);

		String tree = serialize(root);
		System.out.println(tree);

		root = deserialize(tree);
		TreeUtils.printLevelOrder(root);
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

//	Approach:- 
//			-> Traverse the tree in BFS and store the nodes in a list along with the null childs
//			-> return the list
//	
//	TC:- O(N)
//	SC:- O(N)
	public static String serialize(Node root) {
		List<String> ans = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				Node node = queue.poll();
				if (node != null) {
					ans.add(node.data + "");
					queue.add(node.left);
					queue.add(node.right);
				} else {
					ans.add(null);
				}
				size--;
			}
		}
		return String.join(",", ans);
	}

//	Approach:- 
//			-> Use the property of tree's parent-child relation i.e for ith parent its children are (2*i + 1) and (2*i + 2)
//			-> Use a queue, start with the root node i.e 0th val, and start the BFS process for each polled node, and add its children.
//			-> Add back in the queue the non null child.
//			-> Keep mind the null val.
//			-> Build the tree accordingly.
//	
//	TC:- O(N)
//	SC:- O(N)
	public static Node deserialize(String data) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		List<String> nodes = Arrays.asList(data.split(","));

		Queue<Node> queue = new LinkedList<>();
		Node root = new Node(Integer.valueOf(nodes.get(0)));
		queue.add(root);

		int i = 1;
		while (!queue.isEmpty() && i < nodes.size() - 1) {
			Node node = queue.poll();
			String left = nodes.get(i++);
			String right = nodes.get(i++);

			node.left = left.equals("null") ? null : new Node(Integer.valueOf(left));
			node.right = right.equals("null") ? null : new Node(Integer.valueOf(right));

			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);
		}
		return root;
	}
}
