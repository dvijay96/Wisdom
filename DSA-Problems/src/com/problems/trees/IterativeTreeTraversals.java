package com.problems.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class IterativeTreeTraversals {

	public static void main(String[] args) {
//		{3,5,6,8,0,2,9,0,7};
		int[] arr = ArrayUtils.getIntArray(10);
		ArrayUtils.print(arr);
		System.out.println();
		Node root = TreeUtils.createCBTree(arr);

//		Node root = TreeUtility.createTree(arr);

		System.out.println("\nPreOrder:- ");
		TreeUtils.preorder(root);

		System.out.println("\n" + preorder(root));

		System.out.println("\nInOrder:- ");
		TreeUtils.inorder(root);
		System.out.println("\n" + inOrder(root));

		System.out.println("\nPostOrder 2 stack:- ");
		TreeUtils.postOrder(root);
		System.out.println("\n" + postOrderWith2Stacks(root));

		System.out.println("\nPostOrder 1 Stack:- ");
		System.out.println(postOrderWith1Stack(root));
	}

	static List<Integer> preorder(Node root) {
		List<Integer> ans = new ArrayList<>();

		Deque<Node> stack = new LinkedList<>();

		stack.add(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
			ans.add(node.data);
		}

		return ans;
	}

	static List<Integer> inOrder(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();

		Deque<Node> stack = new LinkedList<>();
		Node node = root;

		while (true) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				if (stack.isEmpty())
					break;
				node = stack.pop();
				ans.add(node.data);
				node = node.right;
			}
		}
		return ans;
	}

	static List<Integer> postOrderWith2Stacks(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();

		Deque<Node> stack1 = new LinkedList<>();
		Deque<Node> stack2 = new LinkedList<>();

		stack1.push(root);

		while (!stack1.isEmpty()) {
			Node node = stack1.pop();
			stack2.push(node);
			if (node.left != null)
				stack1.push(node.left);
			if (node.right != null)
				stack1.push(node.right);
		}

		while (!stack2.isEmpty()) {
			ans.add(stack2.pop().data);
		}
		return ans;
	}

	static List<Integer> postOrderWith1Stack(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();

		Deque<Node> stack = new LinkedList<>();

		Node node = root;

		while (node != null || !stack.isEmpty()) {
			if (node != null) { // go as left as possible and keep storing the nodes in stack.
				stack.push(node);
				node = node.left;
			} else {
				Node temp = stack.peek().right; // if left not available, check for right
				if (temp != null) {
					node = temp;
				} else {
					temp = stack.pop(); // if right also not available then this is the extreme left or right, pop from
										// stack and print
					ans.add(temp.data);
					while (!stack.isEmpty() && temp == stack.peek().right) { // now for the parent node, check if the
																				// popped out node was right
						temp = stack.pop(); // child of its parent node present in stack's top.
						ans.add(temp.data);
					}
				}
			}
		}

		return ans;
	}
}
