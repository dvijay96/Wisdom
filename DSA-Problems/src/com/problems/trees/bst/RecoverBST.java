package com.problems.trees.bst;

import java.util.concurrent.ThreadLocalRandom;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class RecoverBST {

//	You are given the root of a binary search tree(BST), where exactly two nodes were swapped by mistake. 
//	Fix (or correct) the BST by swapping them back. Do not change the structure of the tree.
//	
//	Note: It is guaranteed that the given input will form BST, except for 2 nodes that will be wrong. 
//	All changes must be reflected in the original linked list.
//	 
//	Example 1:
//	Input:
//	       10
//	     /    \
//	    5      8
//	   / \
//	  2   20
//	Output: 1
//	Explanation:
//	 
//	Example 2:
//
//	Input:
//	         11
//	       /    \
//	      3      17
//	       \    /
//	        4  10
//	Output: 1 
//	Explanation: 
//	By swapping nodes 11 and 10, the BST 
//	can be fixed.

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);
		Node root = TreeUtils.createBST(arr);
		swapNodes(root);
		TreeUtils.inorder(root);

		System.out.println("\nApproach 2:");

		recoverBST(root);

		TreeUtils.inorder(root);
	}

	private static void swapNodes(Node root) {
		int i = 2;
		Node first = null;
		Node last = null;
		char[] dir = { 'L', 'R' };
		first = chooseNode(root, dir, i);
		last = chooseNode(root, dir, i);

		if (first != null && last != null) {
			int data = first.data;
			first.data = last.data;
			last.data = data;
		}
	}

	private static Node chooseNode(Node root, char[] dir, int i) {
		while (root != null && i > 0) {
			int d = ThreadLocalRandom.current().nextInt(0, 2);
			if (dir[d] == 'L') {
				root = root.left;
			} else {
				root = root.right;
			}
			i--;
		}
		return root;
	}

	public static void recoverBST(Node root) {
		Node curr = root;
		first = prev = mid = last = null;

		while (curr != null) {
			if (curr.left == null) {
				check(curr);
				prev = curr;
				curr = curr.right;
			} else {
				Node temp = curr.left;
				while (temp.right != null && temp.right != curr) {
					temp = temp.right;
				}
				if (temp.right == null) {
					temp.right = curr;
					curr = curr.left;
				} else {
					check(curr);
					temp.right = null;
					prev = curr;
					curr = curr.right;
				}
			}
		}

		if (first != null && last != null) {
			int data = first.data;
			first.data = last.data;
			last.data = data;
		} else if (first != null && mid != null) {
			int data = first.data;
			first.data = mid.data;
			mid.data = data;
		}
	}

	private static void check(Node curr) {
		if (prev != null && curr.data < prev.data) {
			if (first == null) {
				first = prev;
				mid = curr;
			} else {
				last = curr;
			}
		}
	}

//	Approach:-
//			-> Since we know inorder of BST is sorted and if two nodes are swapped , then there will be a voilation of ascending order
//			-> These 2 nodes can be found either adjacent to each other or not in a inorder traversal.
//				ex:
//					preper inorder:- 2,4,5,7,8,9
//					
//					1.	swapped 5 & 9
//									  	2, 4, 9, 7, 8, 5		
//					2.  swapped 5 & 7
//										2, 4, 7, 5, 8, 9
//			
//			-> We keep two pointers prev and curr. with curr being the current node on a inorder traversal and prev will keep updating as we traverse.
//			-> Also keep two more pointers to find the first violation and the second violation.
//			-> Another pointer to mid as to point to the adjacent node of first violation incase violations are in adjacent.
//			-> We need to check for any violation where curr node is smaller than prev node in inorder traversal.

//	TC:- O(N)
//	SC:- O(h)
	private static Node prev;
	private static Node first;
	private static Node mid;
	private static Node last;

	public static Node correctBST(Node root) {
		prev = first = mid = last = null;

		inorder(root);
		if (first != null && last != null) {
			int data = first.data;
			first.data = last.data;
			last.data = data;
		} else if (first != null && mid != null) {
			int data = first.data;
			first.data = mid.data;
			mid.data = data;
		}
		return root;
	}

	private static void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			if (prev != null && root.data < prev.data) {
				if (first == null) {
					first = prev;
					mid = root;
				} else {
					last = root;
				}
			}
			prev = root;
			inorder(root.right);
		}
	}

}
