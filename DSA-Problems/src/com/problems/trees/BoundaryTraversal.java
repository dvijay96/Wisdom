package com.problems.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class BoundaryTraversal {

//	Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order: 
//
//		Left boundary nodes:- defined as the path from the root to the left-most node ie- the leaf node you could reach 
//								when you always travel preferring the left subtree over the right subtree. 
//		
//		Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
//		
//		Reverse right boundary nodes:- defined as the path from the right-most node to the root. The right-most node is 
//										the leaf node you could reach when you always travel preferring the right subtree 
//										over the left subtree. Exclude the root from this as it was already included in the 
//										traversal of left boundary nodes.
//		
//	Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary. 
//
//		Example 1:
//
//		Input:
//		        1 
//		      /   \
//		     2     3  
//		    / \   / \ 
//		   4   5 6   7
//		      / \
//		     8   9
//		   
//		Output: 1 2 4 8 9 6 7 3
	public static void main(String[] args) {
		BoundaryTraversal obj = new BoundaryTraversal();

		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(obj.boundary(root));

	}

//	Approach:-
//	
//	Collecting left boundary elements:-
//		Traverse in as left as possible and store the resulting nodes.
//		If there exists no left only then go to right, as a second priority. 1st priority is left always.
//		Traverse till the last leaf node on left boundary path. but doesn't include leaf node.

//	Collecting leaf nodes:-
//		Traverse the tree in in-order fashion, and collect all the leaf nodes
//		i.e nodes which don't have left or right child.

//	Collecting right nodes:- 
//		Traverse in as right as possible and store the resulting nodes.
//		If there exists no right only then go to left, as a second priority. 1st priority is right always.
//		Traverse till the last leaf node on right boundary path, but do not include leaf node.
//		Since result for right is required in reverse, better store it in stack so it could be extracted in reverse order.
	List<Integer> boundary(Node node) {
		List<Integer> ans = new ArrayList<>();
		if (node == null) {
			return ans;
		}
		if (isLeafNode(node)) {
			ans.add(node.data);
			return ans;
		}
		ans.add(node.data);
		addLeftNodes(node, ans);
		addLeafNodes(node, ans);
		addRightNodes(node, ans);

		return ans;
	}

	void addLeftNodes(Node node, List<Integer> ans) {
		Node curr = node.left;

		while (curr != null) {
			if (isLeafNode(curr)) {
				break;
			}
			ans.add(curr.data);
			if (curr.left != null) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
	}

	void addRightNodes(Node node, List<Integer> ans) {
		Node curr = node.right;
		LinkedList<Integer> stack = new LinkedList<>();
		while (curr != null) {
			if (isLeafNode(curr)) {
				break;
			}
			stack.push(curr.data);
			if (curr.right != null) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
		while (!stack.isEmpty()) {
			ans.add(stack.pop());
		}
	}

	void addLeafNodes(Node node, List<Integer> ans) {
		if (node == null) {
			return;
		}
		if (isLeafNode(node)) {
			ans.add(node.data);
			return;
		}
		addLeafNodes(node.left, ans);
		addLeafNodes(node.right, ans);
	}

	boolean isLeafNode(Node node) {
		return node.left == null && node.right == null;
	}
}
