package com.dsalgo.binary.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dsalgo.utility.ArrayUtils;
import com.dsalgo.utility.TreeUtils;

public class MorrisTraversal {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(inorder(root));
		System.out.println(preorder(root));
	}

//	Approach:-
//			-> The idea is to create a threaded connection between the right most node of the left sub tree to the root of that sub tree.
//			-> We start with root, and check if left available, then we start from left sub tree in search of the right most node.
//			-> when found, we connect that right most node's right to the curr i.e root.
//			-> Then move to left, and repeat the same process.
//			-> Once, the left sub tree is done then we move to the right and follow the same process.
//			-> There are 3 cases to be checked that are:
//					1. when curr or any nodes left is null, then it is considered as a root node.
//					2. when prev node's right is pointing to curr, then also we add curr in the traversal.

//	Algo:-
//			Initialize current as root 
//			2. While current is not NULL
//			   If the current does not have left child
//			      a) Print current’s data
//			      b) Go to the right, i.e., current = current->right
//			   Else
//			      a) Find rightmost node in current left subtree OR
//			              node whose right child == current.
//			         If we found right child == current
//			             a) Update the right child as NULL of that node whose right child is current
//			             b) Print current’s data
//			             c) Go to the right, i.e. current = current->right
//			         Else
//			             a) Make current as the right child of that rightmost 
//			                node we found; and 
//			             b) Go to this left child, i.e., current = current->left

	protected static List<Integer> inorder(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}
		List<Integer> ans = new ArrayList<>();
		Node curr = root;

		while (curr != null) {
			if (curr.left == null) {
				ans.add(curr.data);
				curr = curr.right;
			} else {
				Node prev = curr.left;
				while (prev.right != null && prev.right != curr) {
					prev = prev.right;
				}
				if (prev.right == null) {
					prev.right = curr;
					curr = curr.left;
				} else {
					prev.right = null;
					ans.add(curr.data);
					curr = curr.right;
				}
			}
		}

		return ans;
	}

//	Algo:
//	1...If left child is null, print the current node data. Move to right child. 
//	….Else, Make the right child of the inorder predecessor point to the current node. Two cases arise: 
//	………a) The right child of the inorder predecessor already points to the current node. Set right child to NULL. 
//			Move to right child of current node. 
//	………b) The right child is NULL. Set it to the current node. Print the current node’s data and move to left child of current node. 
//	2...Iterate until the current node is not NULL.
	protected static List<Integer> preorder(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}
		List<Integer> ans = new ArrayList<>();
		Node curr = root;

		while (curr != null) {
			if (curr.left == null) {
				ans.add(curr.data);
				curr = curr.right;
			} else {
				Node prev = curr.left;
				while (prev.right != null && prev.right != curr) {
					prev = prev.right;
				}
				if (prev.right == null) {
					prev.right = curr;
					ans.add(curr.data);
					curr = curr.left;
				} else {
					prev.right = null;
					curr = curr.right;
				}
			}
		}

		return ans;
	}
}
