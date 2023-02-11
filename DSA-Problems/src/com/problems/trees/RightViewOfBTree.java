package com.problems.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class RightViewOfBTree {

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);
		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(rightSideView(root));
	}

	public static List<Integer> rightSideView(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}
		Set<Integer> set = new HashSet<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int level = 1;
		List<Integer> ans = new ArrayList<>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (!set.contains(level)) {
					ans.add(node.data);
					set.add(level);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
				if (node.left != null) {
					queue.add(node.left);
				}
			}
			level++;
		}
		return ans;
	}
}
