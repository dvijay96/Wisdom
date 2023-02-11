package com.problems.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class SpiralLevelTraversal {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		ArrayUtils.print(arr);

		Node root = TreeUtils.createBinaryTree(arr);

		TreeUtils.printLevelOrder(root);

		System.out.println(spiralTraversal(root));

	}

	static List<List<Integer>> spiralTraversal(Node root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null)
			return ans;

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);
		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> list = new LinkedList<>();

			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);

				if (level % 2 == 1)
					list.addFirst(node.data);
				else
					list.add(node.data);
			}
			level++;
			ans.add(list);
		}
		return ans;
	}
}
