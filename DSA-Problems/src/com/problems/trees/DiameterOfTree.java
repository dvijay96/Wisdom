package com.problems.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class DiameterOfTree {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		TreeUtils.printLevelOrder(root);

		System.out.println("Height:-> " + height(root));

		System.out.println("Diameter:-> " + diameter);
	}

	private static int diameter = 0;

	private static int height(Node node) {
		if (node == null)
			return 0;

		int left = height(node.left);
		int right = height(node.right);

		diameter = Math.max(diameter, left + right);
		return 1 + Math.max(left, right);
	}
}
