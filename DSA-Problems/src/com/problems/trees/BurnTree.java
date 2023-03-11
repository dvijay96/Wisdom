package com.problems.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class BurnTree {

//	Given a binary tree and a node called target. Find the minimum time required to burn the complete binary tree if the target is set on fire. 
//	It is known that in 1 second all nodes connected to a given node get burned. That is its left child, right child, and parent.
//
//
//	Example 1:
//
//	Input:      
//	          1
//	        /   \
//	      2      3
//	    /  \      \
//	   4    5      6
//	       / \      \
//	      7   8      9
//	                   \
//	                   10
//	Target Node = 8
//	Output: 7
//	Explanation: If leaf with the value 
//	8 is set on fire. 
//	After 1 sec: 5 is set on fire.
//	After 2 sec: 2, 7 are set to fire.
//	After 3 sec: 4, 1 are set to fire.
//	After 4 sec: 3 is set to fire.
//	After 5 sec: 6 is set to fire.
//	After 6 sec: 9 is set to fire.
//	After 7 sec: 10 is set to fire.
//	It takes 7s to burn the complete tree.
//	
//	Example 2:
//
//	Input:      
//	          1
//	        /   \
//	      2      3
//	    /  \      \
//	   4    5      7
//	  /    / 
//	 8    10
//	Target Node = 10
//	Output: 5

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArrayUnique(10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(minTime(root, 6));

	}

//	Approach:- 
//			-> Traverse the whole tree and generate parent child relationship map.
//			-> Traverse from the target node to 3 different dirs i.e, left, right & parent.
//			-> Keep track of visited nodes to avoid looping.
//			-> Increment the time only when a node is burning other nodes staring with target node. 
	public static int minTime(Node root, int target) {
		if (root == null)
			return 0;

		Map<Integer, Node> parentMap = new HashMap<>();

		loadParents(root, parentMap);

		Node targetNode = getNode(root, target);

		return burnTime(parentMap, targetNode);
	}

	private static int burnTime(Map<Integer, Node> parentMap, Node target) {
		int ans = 0;

		Queue<Node> queue = new LinkedList<>();
		queue.add(target);
		Set<Integer> visited = new HashSet<>();
		Set<Integer> burned = new HashSet<>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flag = false;
			while (size > 0) {
				Node node = queue.poll();
				visited.add(node.data);

				if (node.left != null && !visited.contains(node.left.data)) {
					queue.add(node.left);
					if (!burned.contains(node.left.data)) {
						flag = true;
						burned.add(node.left.data);
					}
				}
				if (node.right != null && !visited.contains(node.right.data)) {
					queue.add(node.right);
					if (!burned.contains(node.right.data)) {
						flag = true;
						burned.add(node.right.data);
					}
				}

				if (parentMap.containsKey(node.data) && !visited.contains(parentMap.get(node.data).data)) {
					queue.add(parentMap.get(node.data));
					if (!burned.contains(parentMap.get(node.data).data)) {
						flag = true;
						burned.add(parentMap.get(node.data).data);
					}
				}
				size--;
			}
			if (flag) {
				ans++;
			}
		}
		return ans;
	}

	private static void loadParents(Node root, Map<Integer, Node> map) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				Node node = queue.poll();
				if (node.left != null) {
					queue.add(node.left);
					map.put(node.left.data, node);
				}
				if (node.right != null) {
					queue.add(node.right);
					map.put(node.right.data, node);
				}
				size--;
			}
		}
	}

	private static Node getNode(Node root, int target) {
		if (root == null)
			return null;

		if (root.data == target)
			return root;

		Node left = getNode(root.left, target);
		if (left != null)
			return left;

		return getNode(root.right, target);
	}
}
