package com.problems.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class FlattenBT {

//	Given the root of a binary tree, flatten the tree into a "linked list":
//
//		The "linked list" should use the same Node class where the right child pointer points to the next node in the list
//		and the left child pointer is always null.
//		The "linked list" should be in the same order as a pre-order traversal of the binary tree.
//		Example 1:
//
//		Input : 
//		          1
//		        /   \
//		       2     5
//		      / \     \
//		     3   4     6
//		Output :
//		1 2 3 4 5 6 
//		Explanation: 
//		After flattening, the tree looks 
//		like this
//		    1
//		     \
//		      2
//		       \
//		        3
//		         \
//		          4
//		           \
//		            5
//		             \
//		              6 
//		Here, left of each node points 
//		to NULL and right contains the 
//		next node in preorder.The inorder 
//		traversal of this flattened tree 
//		is 1 2 3 4 5 6.
//		
//		Example 2:
//
//		Input :
//		        1
//		       / \
//		      3   4
//		         /
//		        2
//		         \
//		          5 
//		Output : 
//		1 3 4 2 5  
//		Explanation : 
//		After flattening, the tree looks 
//		like this 
//		     1
//		      \
//		       3
//		        \
//		         4
//		          \
//		           2
//		            \ 
//		             5 
//		Here, left of each node points 
//		to NULL and right contains the 
//		next node in preorder.The inorder 
//		traversal of this flattened tree 
//		is 1 3 4 2 5.

	public static void main(String[] args) {

		List<Runnable> tasks = new ArrayList<>();
		tasks.add(() -> {
			System.out.println("List Approach:- \n");
			int[] arr = ArrayUtils.getIntArray(10);
			Node root = TreeUtils.createBinaryTree(arr);
			TreeUtils.preorder(root);
			flatten(root);
			System.out.println();
			TreeUtils.preorder(root);
			System.out.println();
			TreeUtils.inorder(root);
			System.out.println();
		});

		tasks.add(() -> {
			System.out.println("reverse preorder Approach:- \n");
			int[] arr = ArrayUtils.getIntArray(10);
			Node root = TreeUtils.createBinaryTree(arr);
			TreeUtils.preorder(root);
			flattenII(root);
			System.out.println();
			TreeUtils.preorder(root);
			System.out.println();
			TreeUtils.inorder(root);
			System.out.println();
		});

		tasks.add(() -> {
			System.out.println("Stack Approach:- \n");
			int[] arr = ArrayUtils.getIntArray(10);
			Node root = TreeUtils.createBinaryTree(arr);
			TreeUtils.preorder(root);
			flattenIII(root);
			System.out.println();
			TreeUtils.preorder(root);
			System.out.println();
			TreeUtils.inorder(root);
			System.out.println();
		});

		tasks.add(() -> {
			System.out.println("Morris Traversal Approach:- \n");
			int[] arr = ArrayUtils.getIntArray(10);
			Node root = TreeUtils.createBinaryTree(arr);
			TreeUtils.preorder(root);
			flattenIV(root);
			System.out.println();
			TreeUtils.preorder(root);
			System.out.println();
			TreeUtils.inorder(root);
			System.out.println();
		});

		for (Runnable task : tasks) {
			task.run();
			System.out.println();
		}

	}

//	Approach:-
//			-> By using a extra list we store the preorder traversal nodes of the tree in the list
//			-> then by traversing the extra List, we create the required linked list by making each nodes left as null and adding following nodes 
//				to the right.
//	
//	TC:- O(N)
//	SC:- O(N)
	private static void flatten(Node root) {
		Node head = new Node(-1);
		Node temp = head;
		Queue<Node> queue = new LinkedList<>();
		preorder(root, queue);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			node.right = node.left = null;
			temp.right = node;
			temp = temp.right;
		}
		root = head.right;
	}

	static void preorder(Node root, Queue<Node> queue) {
		if (root == null)
			return;

		queue.add(root);

		preorder(root.left, queue);
		preorder(root.right, queue);
	}

	private static Node prev;

//	Approach:-
//			-> We traverse the tree in reverse preorder fashion i.e R L H
//			-> We keep a prev pointer to point to prev node once reaching right most node of reverse preorder
//			-> For every node, after traversing its right and left child, we set the node's left as null & right to prev
//				and update prev to point to current node;
//			-> And so on follow the process.
//	
//	TC:- O(N)
//	SC:- O(N) -> stack space if the tree is a right skew tree

	private static void flattenII(Node root) {
		if (root == null)
			return;
		flattenII(root.right);
		flattenII(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
	}

//	Approach:- 
//			-> Perform a BFS with a stack.
//			-> push right first then left. becuz we are following preorder for the final ans.
//			-> for the curr node, place its right as stack's peek and left as null.
//			
//	TC:- O(N)
//	SC:- O(N)
	private static void flattenIII(Node root) {
		if (root == null)
			return;

		LinkedList<Node> stack = new LinkedList<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node curr = stack.pop();
			if (curr.right != null)
				stack.push(curr.right);
			if (curr.left != null)
				stack.push(curr.left);

			curr.right = stack.peek();
			curr.left = null;
		}
	}

//	Approach:-
//			-> Using Morris traversal
//			-> Idea is in preorder Head->left->right should point to head->right
//			-> If 
//								1
//							   / \
//							  2	  3
//				then 
//					HLR    1,2,3 i.e 1->2->3
//			-> root's left sub tree's right most node should point to root's right node.
//			
//	TC:- O(N)
//	SC:- O(1)
	private static void flattenIV(Node root) {
		Node curr = root;

		while (curr != null) {
			if (curr.left != null) {
				Node next = curr.left;

				while (next.right != null) {
					next = next.right;
				}
				next.right = curr.right;
				next = curr.left;
				curr.left = null;
				curr.right = next;
			}
			curr = curr.right;
		}

	}

	@SuppressWarnings("unused")
	private static Node createExample() {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(3);
		root.left.right = new Node(4);
		root.right = new Node(5);
		root.right.right = new Node(6);
		return root;
	}
}
