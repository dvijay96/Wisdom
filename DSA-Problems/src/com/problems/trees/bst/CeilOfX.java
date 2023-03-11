package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class CeilOfX {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBST(arr);
		int key = 10;
		List<Runnable> tasks = new ArrayList<>();

		tasks.add(() -> {
			System.out.print("Priority Queue Approach: ");
			System.out.println(findCeil(root, key));
		});
		tasks.add(() -> {
			System.out.print("Optimized Approach: ");
			System.out.println(findCeilOP(root, key));
		});

		for (Runnable task : tasks) {
			task.run();
			System.out.println();
		}
	}

	static int findCeilOP(Node root, int key) {
		int ceil = -1;
		while (root != null) {
			if (root.data == key)
				return root.data;
			else if (root.data > key) {
				ceil = root.data;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return ceil;
	}

	static int findCeil(Node root, int key) {
		if (root == null)
			return -1;
		class Pair {
			Node node;
			int diff;

			Pair(Node node, int diff) {
				this.node = node;
				this.diff = diff;
			}
		}
		PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> {
			return a.diff - b.diff;
		});

		while (root != null) {
			if (root.data == key) {
				return key;
			} else if (root.data < key) {
				root = root.right;
			} else {
				queue.add(new Pair(root, root.data - key));
				root = root.left;
			}
		}

		return queue.isEmpty() ? -1 : queue.peek().node.data;
	}
}
