package com.problems.trees;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class KDistanceNodes {

//	Given a binary tree, a target node in the binary tree, and an integer value k, find all the nodes that are at distance k from the given target node. No parent pointers are available.
//	Note: You have to return list in sorted order.
//
//	Example 1:
//
//	Input:      
//	          20
//	        /    \
//	      8       22 
//	    /   \
//	   4    12 
//	       /   \
//	      10    14
//	
//	Target Node = 8
//	K = 2
//	Output: 10 14 22
//	Explanation: The three nodes at distance 2
//				 from node 8 are 
//					10  =>  [8 -> 12 -> 10],
//					14  =>  [8 -> 12 -> 14],
//					22  =>  [8 -> 20 -> 22]			This one is through parent or say upward.
//
//	Example 2:
//
//	Input:      
//	         20
//	       /    \
//	      7      24
//	    /   \
//	   4     3
//	        /  
//	       1    
//	
//	Target Node = 7
//	K = 2
//	Output: 1 24
	public static void main(String[] args) {
		KDistanceNodes obj = new KDistanceNodes();
		int[] arr = ArrayUtils.getIntArrayUnique(10);

		Node root = TreeUtils.createBinaryTree(arr);

		Node target = obj.getTargetNode(root, 5);

		System.out.println(obj.distanceK(root, target, 2));
	}

//	Approach:- 
//			-> We first store the parent child relationship by traversing the whole tree such that we can keep track of each node's parent in
//				a data structure like map.
//			-> We now start traversing from the target node in 3 directions i.e left child , right child & parent.
//			-> Keep track of visited nodes to avoid back and forth looping.
//			-> Once the k distance is reached in all 3 directions, we stop and whatever nodes are present in our queue are the ans.
	public List<Integer> distanceK(Node root, Node target, int k) {
		if (root == null || target == null)
			return Collections.emptyList();
		Map<Integer, Node> parentMap = new HashMap<>();
		loadParentMap(root, parentMap);

		Set<Integer> visited = new HashSet<>();

		Queue<Node> queue = new LinkedList<>();
		queue.add(target);

		while (!queue.isEmpty() && k > 0) {
//			int size = queue.size();
			Queue<Node> temp = new LinkedList<>(queue);
			queue.clear();
			while (!temp.isEmpty()) {
				Node curr = temp.poll();
				visited.add(curr.data);

				if (curr.left != null && !visited.contains(curr.left.data)) {
					queue.add(curr.left);
				}
				if (curr.right != null && !visited.contains(curr.right.data)) {
					queue.add(curr.right);
				}
				if (parentMap.containsKey(curr.data) && !visited.contains(parentMap.get(curr.data).data)) {
					queue.add(parentMap.get(curr.data));
				}
			}
			k--;
		}

		return queue.stream().map(node -> node.data).sorted().collect(Collectors.toList());
	}

	void loadParentMap(Node root, Map<Integer, Node> map) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (node.left != null) {
					queue.add(node.left);
					map.put(node.left.data, node);
				}
				if (node.right != null) {
					queue.add(node.right);
					map.put(node.right.data, node);
				}
			}
		}
	}

	Node getTargetNode(Node root, int target) {
		if (root == null)
			return null;

		if (root.data == target)
			return root;

		if (getTargetNode(root.left, target) != null) {
			return root;
		}
		return getTargetNode(root.right, target);
	}
}
