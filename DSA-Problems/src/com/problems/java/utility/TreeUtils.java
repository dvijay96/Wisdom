package com.problems.java.utility;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import com.problems.trees.Node;

public interface TreeUtils {

	/**
	 * Takes the root node and prints pre-order traversal of the B-Tree
	 * 
	 * @param root
	 */
	static void preorder(Node root) {
		if (root == null)
			return;
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * Take a root node and prints in-order traversal of the B-Tree
	 * 
	 * @param root
	 */
	static void inorder(Node root) {
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}

	/**
	 * Take a root node and prints post-order traversal of the B-Tree
	 * 
	 * @param root
	 */
	static void postOrder(Node root) {
		if (root == null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}

	/**
	 * Take a root node and prints level order traversal of the B-Tree
	 * 
	 * @param root
	 */
	static void levelOrder(Node root) {
		if (root == null)
			return;

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node curr = queue.poll();
				if (curr.left != null)
					queue.add(curr.left);
				if (curr.right != null)
					queue.add(curr.right);
				System.out.print(curr.data + " ");
			}
		}
		System.out.println();
	}

	static void printLevelOrder(Node root) {
		List<String> list = new ArrayList<>();

		if (root != null) {
			Queue<Node> queue = new LinkedList<>();

			queue.add(root);

			while (!queue.isEmpty()) {
				int size = queue.size();
				StringBuilder str = new StringBuilder();
				for (int i = 0; i < size; i++) {
					Node curr = queue.poll();
					if (curr.left != null)
						queue.add(curr.left);
					if (curr.right != null)
						queue.add(curr.right);
					str.append(curr.data + " ");
				}
				list.add(str.toString());
			}
		}

		System.out.println("Level Order:-> ");

		list.forEach(System.out::println);
	}

	/**
	 * Takes a root node and print spiral level order traversal of the B-Tree
	 * 
	 * @param root
	 */
	static void spiralOrder(Node root) {

		if (root == null) {
			return;
		}

		Deque<Node> queue = new LinkedList<>();

		queue.add(root);
		int level = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Node node;
				if (level % 2 == 1) {
					node = queue.pollLast();

					if (node.right != null) {
						queue.addFirst(node.right);
					}
					if (node.left != null) {
						queue.addFirst(node.left);
					}

					System.out.print(node.data + " ");
				} else {

					node = queue.pollFirst();

					if (node.left != null) {
						queue.addLast(node.left);
					}
					if (node.right != null) {
						queue.addLast(node.right);
					}
					System.out.print(node.data + " ");
				}
			}

			level++;
		}
	}

	/**
	 * Creates a complete Binary Tree out of the given arr ( doesn't include null
	 * nodes)
	 * 
	 * @param arr
	 * @return
	 */
	static Node createCBTree(int[] arr) {
		if (arr == null || arr.length == 0 || arr[0] == 0)
			return null;

		Node root = new Node(arr[0]);

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		int i = 1;
		while (i < arr.length) {
			Node node = queue.poll();
			int val = arr[i++];
			if (val != 0) {
				node.left = new Node(val);
				queue.add(node.left);
			}
			if (i >= arr.length)
				break;
			val = arr[i++];
			if (val != 0) {
				node.right = new Node(val);
				queue.add(node.right);
			}
		}

		return root;
	}

	/**
	 * Creates a Binary Tree out of the given array (including null nodes)
	 * @param arr
	 * @return
	 */
	static Node createBinaryTree(int[] arr) {
		class NodeData {
			int data;
			StringBuilder path = new StringBuilder();

			public NodeData(int data) {
				super();
				this.data = data;
			}

			@Override
			public String toString() {
				return "[ " + data + ", " + path + " ]";
			}

		}
		char[] dir = { 'L', 'R' };
		List<NodeData> list = new ArrayList<>();
		for (int i : arr) {
			list.add(new NodeData(i));
		}
		Node root = null;
		for (int i = 0; i < list.size(); i++) {
			Node node = new Node(list.get(i).data);

			if (root == null) {
				root = node;
				list.get(i).path.append("H");
			} else {
				Node ptr = root;
				char d;

				while (ptr != null) {
					int j = ThreadLocalRandom.current().nextInt(0, 2);
					d = dir[j];
					list.get(i).path.append(d);
					if (d == 'L') {
						if (ptr.left == null) {
							ptr.left = node;
							ptr = null;
						} else {
							ptr = ptr.left;
						}
					} else if (d == 'R') {
						if (ptr.right == null) {
							ptr.right = node;
							ptr = null;
						} else {
							ptr = ptr.right;
						}
					}
				}
			}
		}
		System.out.println("Tree:-> " + list);
		return root;
	}
}
