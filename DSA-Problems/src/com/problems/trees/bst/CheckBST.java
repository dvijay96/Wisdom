package com.problems.trees.bst;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class CheckBST {

//	Given the root of a binary tree. Check whether it is a BST or not.
//	Note: We are considering that BSTs can not contain duplicate Nodes.
//	A BST is defined as follows:
//
//	The left subtree of a node contains only nodes with keys less than the node's key.
//	The right subtree of a node contains only nodes with keys greater than the node's key.
//	Both the left and right subtrees must also be binary search trees.
//	 
//
//	Example 1:
//
//	Input:
//	   2
//	 /    \
//	1      3
//	Output: 1 
//	Explanation: 
//	The left subtree of root node contains node
//	with key lesser than the root nodes key and 
//	the right subtree of root node contains node 
//	with key greater than the root nodes key.
//	Hence, the tree is a BST.
//	
//	Example 2:
//
//	Input:
//	  2
//	   \
//	    7
//	     \
//	      6
//	       \
//	        5
//	         \
//	          9
//	           \
//	            2
//	             \
//	              6
//	Output: 0 
//	Explanation: 
//	Since the node with value 7 has right subtree 
//	nodes with keys less than 7, this is not a BST.

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);
		Node root = TreeUtils.createBST(arr);
		System.out.println(isBST(root));
	}

//	Approach:-
//			-> We know in a BST, all nodes on the left sub tree are smaller than root node and on right sub tree all nodes are greater than the 
//				root node.
//			-> Making use of this property, we set a initial range of [low, high].
//			-> Starting with root, the root would be in the range [low, high].
//			-> For the left, the left should be in the range [low, root.data] and so on on the left.
//			-> For the right, the right should be in the range [root.data, high] and so on on the right.
//	
//	TC:- O(N)
	static boolean isBST(Node root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean validate(Node root, int minValue, int maxValue) {
		if (root == null)
			return true;
		if (root.data >= maxValue && root.data <= minValue)
			return false;
		return validate(root.left, minValue, root.data) && validate(root.right, root.data, maxValue);
	}
}
