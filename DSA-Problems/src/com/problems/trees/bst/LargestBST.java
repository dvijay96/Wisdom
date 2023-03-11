package com.problems.trees.bst;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class LargestBST {

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArrayUnique(10);
		ArrayUtils.shuffle(arr);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(largestBst(root));
		System.out.println(largestBSTII(root));
	}

//	Approach:
//			-> Need to check at each node if the curr node is greater than left subtree's largest val and less than right subtree's smallest value.
//			-> To carry this info, we make use of a extra meta data node.
//			-> A subtree will be a BST only if its left subtree is smaller than curr node and right subtree is larger than curr node.
//	
//	TC:- O(n)
//	SC:- O(h) aux stack space
	static int largestBst(Node root) {
		return helper(root).size;
	}

	static MetaNode helper(Node root) {
		if (root == null) {
			return new MetaNode(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		}

		MetaNode left = helper(root.left);
		MetaNode right = helper(root.right);

		if (root.data > left.maxVal && root.data < right.minVal) {
			return new MetaNode(Math.max(root.data, right.maxVal), Math.min(root.data, left.minVal),
					left.size + right.size + 1);
		}

		return new MetaNode(Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(left.size, right.size));
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Similar approach with diff implementation
	 * 
	 */
	private static int max = 0;

	static int largestBSTII(Node root) {
		max = 0;
		helperII(root);
		return max;
	}

	private static NodeInfo helperII(Node root) {
		if (root == null) {
			return new NodeInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0);
		}

		NodeInfo left = helperII(root.left);
		NodeInfo right = helperII(root.right);

		NodeInfo curr = new NodeInfo();
		curr.max = Math.max(root.data, right.max);
		curr.min = Math.min(root.data, left.min);
		curr.size = left.size + right.size + 1;

		if (left.isBST && right.isBST && (left.max < root.data && right.min > root.data)) {
			curr.isBST = true;
		}
		if (curr.isBST) {
			max = Math.max(max, curr.size);
		}

		return curr;
	}
}

class MetaNode {
	int maxVal;
	int minVal;
	int size;

	MetaNode(int maxVal, int minVal, int size) {
		this.maxVal = maxVal;
		this.minVal = minVal;
		this.size = size;
	}
}

class NodeInfo {
	int max;
	int min;
	boolean isBST;
	int size;

	public NodeInfo(int max, int min, boolean isBST, int size) {
		super();
		this.max = max;
		this.min = min;
		this.isBST = isBST;
		this.size = size;
	}

	public NodeInfo() {

	}
}
