package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class DeleteNode {

	public static void main(String[] args) {
		List<Runnable> tasks = new ArrayList<>();
		tasks.add(() -> {
			System.out.println("Recursive Aproach:- ");
			int[] arr = ArrayUtils.getIntArray(10);

			Node root = TreeUtils.createBST(arr);

			TreeUtils.printLevelOrder(root);
			System.out.println();
			root = deleteNode(root, root.data);
			TreeUtils.printLevelOrder(root);
		});

		tasks.add(() -> {
			System.out.println("Naive Aproach:- ");
			int[] arr = ArrayUtils.getIntArray(10);

			Node root = TreeUtils.createBST(arr);

			TreeUtils.printLevelOrder(root);
			System.out.println();
			root = deleteNodeNaive(root, root.data);
			TreeUtils.printLevelOrder(root);
		});
		
		tasks.add(() -> {
			System.out.println("Iterative Aproach:- ");
			int[] arr = ArrayUtils.getIntArray(10);

			Node root = TreeUtils.createBST(arr);

			TreeUtils.printLevelOrder(root);
			System.out.println();
			root = deleteNodeII(root, root.data);
			TreeUtils.printLevelOrder(root);
		});

		tasks.forEach(task -> {
			System.out.println();
			task.run();
			System.out.println();
		});
	}

//	Approach:
//			-> Search the node.
//			-> Once found, we know that on the parent node is that left subtree < right subtree.
//			-> We find the right most node on the left subtree of the to be deleted node.
//			-> Assign the deleted node's right to the left subtree's rightmost node.
//			-> return the left subtree to be assigned to the parent of the deleted node.
	public static Node deleteNodeII(Node root, int key) {
		if (root == null)
			return null;
		if (root.data == key) {
			return helper(root);
		}
		Node node = root;

		while (node != null) {
			if (node.data > key) {
				if (node.left != null && node.left.data == key) {
					Node temp = helper(node.left);
					node.left = temp;
					break;
				} else {
					node = node.left;
				}
			} else {
				if (node.right != null && node.right.data == key) {
					Node temp = helper(node.right);
					node.right = temp;
					break;
				} else {
					node = node.right;
				}
			}
		}
		return root;
	}

	private static Node helper(Node root) {
		if (root.left == null)
			return root.right;
		else if (root.right == null)
			return root.left;
		else {
			Node rightMost = rightMost(root.left);
			rightMost.right = root.right;
			return root.left;
		}
	}

	private static Node rightMost(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

//	Approach:-
//			-> Recursively find the node that has the same value as the key, while setting the left/right nodes equal to the returned subtree
//			-> Once the node is found, have to handle the below 4 cases
//			-> node doesn't have left or right - return null
//			-> node only has left subtree- return the left subtree
//			-> node only has right subtree- return the right subtree
//			-> node has both left and right - find the minimum value in the right subtree, set that value to the currently found node, 
//				then recursively delete the minimum value in the right subtree
//	
//	TC:- O(Logn)
	public static Node deleteNode(Node root, int key) {
		if (root == null) {
			return null;
		}
		if (key < root.data) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.data) {
			root.right = deleteNode(root.right, key);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			Node minNode = findMin(root.right);
			root.data = minNode.data;
			root.right = deleteNode(root.right, root.data);
		}
		return root;
	}

	private static Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

//	Approach:-
//			-> Store the preorder traversal of the tree.
//			-> Remove the node val from the tree traversal
//			-> Build the tree again.
//	
//	TC:- O(N)
	public static Node deleteNodeNaive(Node root, int key) {
		List<Integer> nodes = new ArrayList<>();
		preorder(root, nodes, key);
		int[] arr = new int[nodes.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = nodes.get(i);
		}
		return TreeUtils.createBST(arr);
	}

	private static void preorder(Node root, List<Integer> nodes, int key) {
		if (root == null)
			return;
		if (root.data != key) {
			nodes.add(root.data);
		}
		preorder(root.left, nodes, key);
		preorder(root.right, nodes, key);
	}

	@SuppressWarnings("unused")
	private static Node createExample() {
		Node root = new Node(16);
		root.left = new Node(7);
		root.right = new Node(20);
		root.left.left = new Node(1);
		root.left.right = new Node(12);
		return root;
	}
}
