package com.dsalgo.binary.trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public interface TreeTraverser {

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

}
