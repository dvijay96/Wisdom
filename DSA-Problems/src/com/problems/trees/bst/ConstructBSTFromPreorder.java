package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class ConstructBSTFromPreorder {

//	Given an array arr[] of N nodes representing preorder traversal of some BST. You have to build the exact BST 
//	from it's given preorder traversal. 
//	In Pre-Order traversal, the root node is visited before the left child and right child nodes.
//
//	Example 1:
//
//	Input:
//	N = 5
//	arr[]  = {40,30,35,80,100}
//	Output: 35 30 100 80 40
//	Explanation: PreOrder: 40 30 35 80 100
//	Therefore, the BST will be:
//	              40
//	           /      \
//	         30       80
//	           \        \   
//	           35      100
//	Hence, the postOrder traversal will
//	be: 35 30 100 80 40
//	
//	Example 2:
//
//	Input:
//	N = 8
//	arr[]  = {40,30,32,35,80,90,100,120}
//	Output: 35 32 30 120 100 90 80 40

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(7);

		Node tree = TreeUtils.createBST(arr);
		System.out.println("Tree ");
		TreeUtils.printLevelOrder(tree);

		int[] preorder = TreeUtils.preorderArray(tree);
		System.out.println("\npreorder:- ");
		ArrayUtils.print(preorder);
		System.out.println();
		List<Runnable> soln = new ArrayList<>();

		soln.add(() -> {
			System.out.println("Naive Approach: \n");
			Node root = naiveApproach(preorder);
			TreeUtils.printLevelOrder(root);
		});
		soln.add(() -> {
			System.out.println("Better Approach: \n");
			Node root = betterApproach(preorder);
			TreeUtils.printLevelOrder(root);
		});
		soln.add(() -> {
			System.out.println("Optimal Approach:\n ");
			Node root = optimalApproach(preorder);
			TreeUtils.printLevelOrder(root);
		});

		for (Runnable sol : soln) {
			System.out.println();
			sol.run();
			System.out.println();
		}
	}

//	Approach:- 
//			-> We set a boundary range for each node.
//			-> ROOT node will begin from the max given constraint val, ROOT has to be lesser than this max.
//			-> for each node after wards we'll check the same case.
//			-> for left sub tree it should be lesser than its root node.
//			-> for right sub tree, it should be lesser than the max range for that iteration .
//			
//	TC:- O(N)
	static Node optimalApproach(int[] pre) {
		if (pre == null || pre.length == 0)
			return null;
		i = 0;
		return buildBST(pre, 5000); // took 500 as max bound due to constraints as max node value was 1000
	}

	private static Integer i = 0;

	private static Node buildBST(int[] pre, int max) {
		if (i >= pre.length || pre[i] > max)
			return null;

		Node root = new Node(pre[i++]);

		root.left = buildBST(pre, root.data);

		root.right = buildBST(pre, max);

		return root;
	}

//	Approach:- 
//			-> Sort the preorder to get a asc sorted inorder for the BST
//			-> build tree using preorder and inorder.
//	
//	TC:- O(N * LogN)
	static Node betterApproach(int[] pre) {
		if (pre == null || pre.length == 0)
			return null;

		int[] inorder = Arrays.copyOf(pre, pre.length);

		Arrays.sort(inorder);

		return buildTree(pre, inorder);
	}

	private static Node buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	private static Node buildTree(int[] pre, int preStart, int preEnd, int[] ino, int inStart, int inEnd,
			Map<Integer, Integer> map) {
		if (preStart > preEnd || inStart > inEnd)
			return null;
		Node node = new Node(pre[preStart]);
		int inRoot = map.get(node.data);
		int numLeft = inRoot - inStart;
		node.left = buildTree(pre, preStart + 1, preStart + numLeft, ino, inStart, inRoot - 1, map);
		node.right = buildTree(pre, preStart + numLeft + 1, preEnd, ino, inRoot + 1, inEnd, map);
		return node;
	}

//	Approach:-
//			-> As in preorder, we know the first element is the node,so we iterate over the preorder and create the BST based on its property.
//			-> i.e every incoming element will we checked , if less than root, then added in the left if greater then added in right.
//	
//	TC:- O(N * N)  in worst case if the tree is a skew tree
	static Node naiveApproach(int[] pre) {
		if (pre == null || pre.length == 0)
			return null;

		Node root = new Node(pre[0]);

		for (int i = 1; i < pre.length; i++) {
			Node temp = root;
			while (temp != null) {
				if (pre[i] < temp.data) {
					if (temp.left == null) {
						temp.left = new Node(pre[i]);
						temp = null;
					} else {
						temp = temp.left;

					}
				} else {
					if (temp.right == null) {
						temp.right = new Node(pre[i]);
						temp = null;
					} else {
						temp = temp.right;
					}
				}
			}
		}
		return root;
	}

}
