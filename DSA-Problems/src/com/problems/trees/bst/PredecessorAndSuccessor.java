package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class PredecessorAndSuccessor {

//	There is BST given with root node with key part as an integer only. You need to find the in-order successor and 
//	predecessor of a given key. If either predecessor or successor is not found, then set it to NULL.
//
//	Example:
//
//	Input:
//									50
//								  /    \
//								30      70
//							   /  \    /  \
//							 20   40  60   80
//	65
//	 &
//	100
//
//	Output:
//	60 70
//	80 -1
//
//	Explanation:
//		In each test case first node is the root. Here, 50 is the root. Here, 50 30 L denotes that node having data 50 
//		has its left child having data 30. Similarly, 30 20 L denotes that node having data 30 has its left child having 
//		data 20. Same goes for 30 40 R but here R means node 40 is right child of node 30. So, we can easily draw a bst 
//		and in first case, we have find predecessor and successor of 65. 
//		Now, 65 need not to be present in the tree. Here, we can see 60 is its predecessor and 70 would be its successor. 
//		Please note that even if 65 were there in the tree, its predecessor and successor would have been the same because 
//		we don't count node itself as predecessor or successor.
//	 
//
//	Output:
//	Find the predecessor and successor of the key in BST and sets pre and suc as predecessor and successor, respectively 
//	Otherwise, set to NULL.
//
//	Your Task: You don't need to print anything. You only need to set p.pre to the predecessor and s.succ to the 
//	successor. p and s have been passed in the function parameter.
//	
	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(50);

		Node root = TreeUtils.createBST(arr);
//		TreeUtils.printLevelOrder(root);
		int key = 13;

		List<Runnable> soln = new ArrayList<>();

		soln.add(() -> {
			System.out.println("Naive Approach");
			Res pre = new Res();
			Res succ = new Res();
			findPreSuc(root, pre, succ, key);

			System.out.println("Predecessor: " + pre.pre);
			System.out.println("Succesor: " + succ.succ);
		});

		soln.add(() -> {
			System.out.println("Better Approach");
			Res pre = new Res();
			Res succ = new Res();
			findPreSucBetter(root, pre, succ, key);

			System.out.println("Predecessor: " + pre.pre);
			System.out.println("Succesor: " + succ.succ);
		});

		soln.add(() -> {
			System.out.println("Optimal Approach");
			Res pre = new Res();
			Res succ = new Res();
			findPreSucOptimal(root, pre, succ, key);

			System.out.println("Predecessor: " + pre.pre);
			System.out.println("Succesor: " + succ.succ);
		});

		for (Runnable sol : soln) {
			System.out.println();
			sol.run();
			System.out.println();
		}
	}

//	Approach:-
//			
//	TC:- O(Log N)
//	SC:- O(1)
	public static void findPreSucOptimal(Node root, Res p, Res s, int key) {
		findSuccessor(root, s, key);
		findPredecessor(root, p, key);
	}

//		-> To find predecessor, starting from root
//		-> if curr node is greater than the key, we move left in search of a smaller node
//		-> if curr node is smaller than the key, we move right to check if there exist a node which is smaller than key 
//			but greater than curr node. 
	private static void findPredecessor(Node root, Res p, int key) {
		while (root != null) {
			if (root.data >= key) {
				root = root.left;
			} else {
				p.pre = root;
				root = root.right;
			}
		}
	}

//		-> To find Successor, starting from root
//		-> if curr node is smaller than key, we move right to find a bigger node
//		-> if curr node is greater than key, we move in left to check if there exists another node greater than key but
//			smaller than curr node.
	private static void findSuccessor(Node root, Res s, int key) {
		while (root != null) {
			if (root.data <= key) {
				root = root.right;
			} else {
				s.succ = root;
				root = root.left;
			}
		}
	}

//	Approach:-
//			-> Traverse inorderly
//			-> Find the last smaller node to key
//			-> Find the first greater node to key.
//	
//	TC:- O(N)
//	SC:- O(1)
	public static void findPreSucBetter(Node root, Res p, Res s, int key) {
		if (root != null) {
			findPreSucBetter(root.left, p, s, key);

			if (root.data < key) {
				p.pre = root;
			}
			if (s.succ == null && root.data > key) {
				s.succ = root;
			}

			findPreSucBetter(root.right, p, s, key);
		}
	}

//	Approach:-
//			-> Store the inorder of the BST
//			-> Find the first greater node for successor and the last smaller node for predecessor of the key.
//	
//	TC:- O(N) + O(N) , if searched linearly in inorder
//			 OR
//		 O(N) + O(LogN) ,  if searched using BS
//	
//	SC:- O(N)
	public static void findPreSuc(Node root, Res p, Res s, int key) {
		List<Node> nodes = new ArrayList<>();

		inorder(root, nodes);

		int low = 0;
		int high = nodes.size() - 1;

		while (high >= 0 && nodes.get(high).data > key) {
			high--;
		}
		high++;
		while (low < nodes.size() && nodes.get(low).data < key) {
			low++;
		}
		low--;

		if (low >= 0 && low < nodes.size()) {
			p.pre = nodes.get(low);
		}

		if (high >= 0 && high < nodes.size()) {
			s.succ = nodes.get(high);
		}
	}

	static void inorder(Node root, List<Node> nodes) {
		if (root != null) {
			inorder(root.left, nodes);
			nodes.add(root);
			inorder(root.right, nodes);
		}
	}
}

class Res {
	Node pre;
	Node succ;
}
