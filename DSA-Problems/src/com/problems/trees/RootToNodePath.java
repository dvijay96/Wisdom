package com.problems.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class RootToNodePath {

//	Given a Binary Tree A containing N nodes.
//
//	You need to find the path from Root to a given node B.
//
//	NOTE:
//
//	No two nodes in the tree have same data values.
//	You can assume that B is present in the tree A and a path always exists.

//	Input 1:
//
//		 A =
//
//		           1
//		         /   \
//		        2     3
//		       / \   / \
//		      4   5 6   7 
//
//
//		B = 5
//
//		Input 2:
//
//		 A = 
//		            1
//		          /   \
//		         2     3
//		        / \ .   \
//		       4   5 .   6
//
//
//		B = 1
//
//
//
//
//		Example Output
//		Output 1:
//		 		 [1, 2, 5]
//		
//		Output 2:
//				 [1]
	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(rootToNodesPaths(root));

		System.out.println(rootToNodePath(root, 5));
	}

//	To Get all Paths from a root node to all leaf nodes
//	
//	Approach:- 
//			-> Traverse in any order.
//			-> once reached a leaf node , add the corresponding list path into the ans list of paths.
//			-> for any node, once its leaf nodes are checked, then remove it from the corresponding list path.
	static List<List<Integer>> rootToNodesPaths(Node root) {
		if (root == null)
			return Collections.emptyList();
		List<List<Integer>> ans = new ArrayList<>();

		traverse(root, ans, new ArrayList<>());
		return ans;
	}

	private static void traverse(Node root, List<List<Integer>> ans, List<Integer> list) {
		if (root == null) {
			return;
		}
		list.add(root.data);
		if (root.left == null && root.right == null) {
			ans.add(new ArrayList<>(list));
			list.remove(list.size() - 1);
			return;
		}
		traverse(root.left, ans, list);
		traverse(root.right, ans, list);
		list.remove(list.size() - 1);
	}

//	To find the path to a given node from the root node, if exists
//	
//	Approach:- 
//			-> Traverse the tree in any dfs fashion.
//			-> add the node to the path ans list only when it equals target.
//			-> return true or false if node found in that path or not.
//			-> this will reduce the travel time, i.e if node is found at left then no need to go and check in right.
	static List<Integer> rootToNodePath(Node root, int target) {
		if (root == null) {
			return Collections.emptyList();
		}

		if (root.data == target) {
			return Collections.singletonList(root.data);
		}

		LinkedList<Integer> ans = new LinkedList<>();

		traverse(root, ans, target);
		return ans;
	}

	private static boolean traverse(Node root, LinkedList<Integer> ans, int target) {
		if (root == null)
			return false;

		if (root.data == target) {
			ans.addFirst(root.data);
			return true;
		}

		if (traverse(root.left, ans, target)) {
			ans.addFirst(root.data);
			return true;
		}

		if (traverse(root.right, ans, target)) {
			ans.addFirst(root.data);
			return true;
		}
		return false;

	}
}
