package com.problems.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class MaxPathSum {

//	A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
//
//	The path sum of a path is the sum of the node's values in the path.
//
//	Given the root of a binary tree, return the maximum path sum of any non-empty path.
//
//	 
//
//	Example 1:
//					1
//				  /	  \
//				 2     3
//	
//	Input: root = [1,2,3]
//	Output: 6
//	Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//	
//	Example 2:
//						 -10
//						 /  \
//						9   20
//					       /  \
//						  15   7
//	
//	Input: root = [-10,9,20,null,null,15,7]
//	Output: 42
//	Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10, -10, 10);

		Node root = TreeUtils.createBinaryTree(arr);
		int[] ans = { Integer.MIN_VALUE };
		maxPath(root, ans);
		System.out.println(ans[0]);
	}

	static int maxPath(Node node, int[] ans) {
		if (node == null) {
			return 0;
		}

		int left = Math.max(0, maxPath(node.left, ans));
		int right = Math.max(0, maxPath(node.right, ans));

		ans[0] = Math.max(ans[0], node.data + left + right);

		return node.data + Math.max(left, right);
	}
}
