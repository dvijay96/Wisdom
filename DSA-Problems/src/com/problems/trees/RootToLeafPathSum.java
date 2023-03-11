package com.problems.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class RootToLeafPathSum {

//	Given a binary tree and an integer S, check whether there is root to leaf path with its sum as S.
//
//	Example 1:
//
//	Input:
//	Tree = 
//	            1
//	          /   \
//	        2      3
//	S = 2
//
//	Output: 0
//
//	Explanation:
//	There is no root to leaf path with sum 2.
//	
//	Example 2:
//
//	Input:
//	Tree = 
//	            1
//	          /   \
//	        2      3
//	S = 4
//
//	Output: 1
//
//	Explanation:
//	The sum of path from leaf node 3 to root 1 is 4.

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10, 1, 10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(hasPathSum(root, 15));
	}

//	Approach:- 
//			-> Traverse in a pre-order fashion.
//			-> keep adding the up the sum until reach a leaf node.
//			-> Once reached to leaf node, check if sum equals target sum, if yes, then return true.
//			-> If from any of the path i.e left or right , return a true , then return overall true.

//	TC:- O(n)
//	SC:- O(H)
	static boolean hasPathSum(Node root, int sum) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return sum - root.data == 0;
		}

		boolean left = hasPathSum(root.left, sum - root.data);
		if (left)
			return true;

		boolean right = hasPathSum(root.right, sum - root.data);
		if (right)
			return true;

		return false;
	}
}
