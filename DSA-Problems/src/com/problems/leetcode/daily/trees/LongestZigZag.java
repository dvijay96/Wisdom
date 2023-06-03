package com.problems.leetcode.daily.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class LongestZigZag {

//	You are given the root of a binary tree.
//
//	A ZigZag path for a binary tree is defined as follow:
//
//	Choose any node in the binary tree and a direction (right or left).
//	If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
//	Change the direction from right to left or from left to right.
//	Repeat the second and third steps until you can't move in the tree.
//	Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
//
//	Return the longest ZigZag path contained in that tree.

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		LongestZigZag obj = new LongestZigZag();

		System.out.println(obj.longestZigZag(root));

	}

//	Approach:-
//			-> To DFS on the tree's left side and right side.
//			-> starting from any direction (L or R).
//			-> switching the directions in each traversal.
//			-> If alternative directions are observed then increment the depth, or else start a new depth with the existing direction.
//		
//	TC:- O(n)
//	SC:- O(h)
	private int maxPath = 0;

	public int longestZigZag(Node root) {
		dfs(root, true, 0);
		dfs(root, false, 0);
		return maxPath;
	}

	void dfs(Node root, boolean left, int steps) {
		if (root == null) {
			return;
		}

		maxPath = Math.max(maxPath, steps);

		if (left) {
			dfs(root.left, false, steps + 1);
			dfs(root.right, true, 1);
		} else {
			dfs(root.right, true, steps + 1);
			dfs(root.left, false, 1);
		}
	}
}
