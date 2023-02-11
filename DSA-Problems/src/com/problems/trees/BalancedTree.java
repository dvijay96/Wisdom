package com.problems.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class BalancedTree {

//	Given a binary tree, find if it is height balanced or not. 
//	A tree is height balanced if difference between heights of left and right subtrees is not more than one 
//	for all nodes of tree.
//	
//		A height balanced tree
//    		1
// 		/     \
//	   10      39
//	  /
//	 5
//
//		An unbalanced tree
//    		1								1
// 		   /    						   /  \
//		 10   							  4	   5
//		/                               /  \
//	   5							   6    3
//									 /     /
//									8     10
	public static void main(String[] args) {
		BalancedTree obj = new BalancedTree();
		int[] arr = ArrayUtils.getIntArray(10);
		ArrayUtils.print(arr);
		Node root = TreeUtils.createBinaryTree(arr);

		TreeUtils.printLevelOrder(root);
		System.out.println(obj.isBalancedOp(root));
	}

//	To check at each node the diff between the height of left sub tree and right sub tree is <= 1.
//	TC:- O(N^2)
	public boolean isBalanced(Node root) {
		if (root == null)
			return true;

		int left = height(root.left);
		int right = height(root.right);

		if (Math.abs(left - right) > 1) {
			return false;
		}

		boolean isBalanceLeft = isBalanced(root.left);
		boolean isBalanceright = isBalanced(root.right);

		if (!isBalanceLeft || !isBalanceright)
			return false;

		return true;
	}

	int height(Node node) {
		if (node == null)
			return 0;

		int left = height(node.left);
		int right = height(node.right);

		return 1 + Math.max(left, right);
	}

//	An optimized approach where we check at each node the diff between its left and right sub tree height by slightly 
//	altering the height algo of bTree.
//	
//	TC:-> O(N)
	public boolean isBalancedOp(Node root) {
		return heightII(root) != -1;
	}

	private int heightII(Node node) {
		if (node == null)
			return 0;

		int left = heightII(node.left);
		int right = heightII(node.right);

		if (left == -1 || right == -1) // if any sub tree is unbalanced, no need to check for further sub trees.
			return -1;
		if (Math.abs(left - right) > 1) // At any root node if the diff between left and right is > 1, return -1 then
										// and there
			return -1; // stating that sub tree is unbalanced.

		return 1 + Math.max(left, right);
	}
}
