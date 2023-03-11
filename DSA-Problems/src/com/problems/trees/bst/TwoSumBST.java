package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class TwoSumBST {

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBST(arr);

		System.out.println(findTarget(root, 15));

		System.out.println(findTarget(root, 15));
	}

	public static boolean findTarget(Node root, int k) {
		if (root == null)
			return false;
		BSTIterator low = new BSTIterator(root, false);
		BSTIterator high = new BSTIterator(root, true);

		int a = low.next();
		int b = high.next();

		while (a < b) {
			if (a + b == k) {
				return true;
			} else if (a + b > k) {
				b = high.next();
			} else {
				a = low.next();
			}
		}

		return false;
	}

//	Approach:-
//			-> Store the inorder in a list.
//			-> perform two sum algo with two pointer approach on the inorder list.
//	
//	TC:- O(N) + O(N)   ,  one for traversing the tree in inorder and one for two sum approach.
//	SC:- O(N)
	public static boolean findTargetNaive(Node root, int k) {
		List<Integer> nodes = new ArrayList<>();

		inorder(root, nodes);

		int low = 0;
		int high = nodes.size() - 1;

		while (low < high) {
			if (nodes.get(low) + nodes.get(high) == k) {
				return true;
			} else if (nodes.get(low) + nodes.get(high) > k) {
				high--;
			} else {
				low++;
			}
		}
		return false;
	}

	static void inorder(Node root, List<Integer> nodes) {
		if (root != null) {
			inorder(root.left, nodes);
			nodes.add(root.data);
			inorder(root.right, nodes);
		}
	}
}

class BSTIterator {

	private LinkedList<Node> stack;

	private boolean isReverse;

	public BSTIterator(Node root, boolean isReverse) {
		this.isReverse = isReverse;
		stack = new LinkedList<>();
		pushAll(root);
	}

	private void pushAll(Node root) {
		while (root != null) {
			stack.push(root);
			if (isReverse)
				root = root.right;
			else {
				root = root.left;
			}
		}
	}

	public int next() {
		Node node = stack.pop();
		if (isReverse)
			pushAll(node.left);
		else
			pushAll(node.right);
		return node.data;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}
}