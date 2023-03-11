package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class KthSmallestElement {

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);
		Node root = TreeUtils.createBST(arr);

		List<Runnable> tasks = new ArrayList<>();

		tasks.add(() -> {
			System.out.println("Morris Traversal Approach:");
			System.out.println("kth Smallest " + kthSmallestElementII(root, 5));
		});

		tasks.add(() -> {
			System.out.println("Inorder traversal stack space Approach:");
			System.out.println("kth Smallest " + kthSmallestElementI(root, 5));
		});

		tasks.add(() -> {
			System.out.println("Naive Approach:");
			System.out.println("kth Smallest " + kthSmallestElementNaive(root, 5));
		});

		for (Runnable task : tasks) {
			task.run();
			System.out.println();
		}
	}

	public static int kthSmallestElementII(Node root, int k) {
		if (root == null) {
			return -1;
		}
		int count = 0;
		int kthElement = -1;
		while (root != null) {
			if (root.left == null) {
				count++;
				if (count == k) {
					kthElement = root.data;
				}
				root = root.right;
			} else {
				Node temp = root.left;
				while (temp.right != null && temp.right != root) {
					temp = temp.right;
				}
				if (temp.right == null) {
					temp.right = root;
					root = root.left;
				} else {
					count++;
					temp.right = null;
					if (count == k) {
						kthElement = root.data;
					}
					root = root.right;
				}
			}
		}
		return kthElement;
	}

//	Approach:
//			-> Keep global pointers count and kthElement.
//			-> Traverse the tree in inorder, and increment the count for each node traversal in inorder
//			-> If count equals k, then that is the kth smallest element.
//	
//	TC:- O(N)
//	SC:- O(H)	stack space, height of tree
	private static int count = 0;
	private static int kthElement = -1;

	public static int kthSmallestElementI(Node root, int k) {
		inorder(root, k);
		return kthElement;
	}

	private static void inorder(Node root, int k) {
		if (root != null) {
			inorder(root.left, k);
			count++;
			if (count == k) {
				kthElement = root.data;
			}
			inorder(root.right, k);
		}
	}

//	Approach:-
//			-> Store the inorder traversal of the BST
//			-> if k-1 is less than the size of tree nodes then k-1 node in the inorder traversal in the kth smallest.
//			-> else -1 i.e no kth element.
//	
//	TC:- O(N)
//	SC:- O(N)
	public static int kthSmallestElementNaive(Node root, int k) {
		List<Integer> nodes = new ArrayList<>();
		inorder(root, nodes);
		return k - 1 < nodes.size() ? nodes.get(k - 1) : -1;
	}

	private static void inorder(Node root, List<Integer> nodes) {
		if (root != null) {
			inorder(root.left, nodes);
			nodes.add(root.data);
			inorder(root.right, nodes);
		}
	}
}
