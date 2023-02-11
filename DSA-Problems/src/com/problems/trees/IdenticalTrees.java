package com.problems.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class IdenticalTrees {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(5);

		Node tree1 = TreeUtils.createBinaryTree(arr);

		Node tree2 = TreeUtils.createBinaryTree(arr);
//
//		Node tree1 = TreeUtils.createCBTree(arr);
//		Node tree2 = TreeUtils.createCBTree(arr);

		TreeUtils.printLevelOrder(tree1);
		TreeUtils.printLevelOrder(tree2);

		System.out.println(isIdentical(tree1, tree2));
	}

	static boolean isIdentical(Node root1, Node root2) {
		if (root1 != null && root2 != null) {
			if (root1.data != root2.data) {
				return false;
			}

			boolean left = isIdentical(root1.left, root2.left);
			boolean right = isIdentical(root1.right, root2.right);

			return left == right;
		}
		if (root1 == null && root2 == null) {
			return true;
		}
		return false;
	}
}
