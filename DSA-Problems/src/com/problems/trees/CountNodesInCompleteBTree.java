package com.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class CountNodesInCompleteBTree {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createCBTree(arr);

		System.out.println(countNodesNaive(root));
		System.out.println(countNodesOptimal(root));
	}

//	Approach:-
//			-> To traverse the tree in any fashion and count the nodes
//	
//	TC:- O(n)
	static int countNodesNaive(Node root) {
		if (root == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int ans = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			ans += size;
			while (size > 0) {
				Node node = queue.poll();
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
				size--;
			}
		}
		return ans;
	}

//	Approach:-
//			-> We know in a complete binary tree, when all levels are completely filled then the num of nodes in that tree
//				are 2^h - 1.  	h = Height of the tree.
//			-> Also in such complete binary tree, for every node, the depth on left and right are same.
//			-> Otherwise in partial complete tree, the number of nodes are equal to 1 + leftDepth + rightDepth for a 
//				given parent node.
//			-> Hence making use of these, we can design our algo.
//			-> for each node we calculate its leftDepth and rightDepth.
//			-> If they are equal, that means below tree is completely complete, and hence we return num of nodes as 
//					Math(2, depth) - 1.
//			-> Else, we go and find leftCount and rightCount then return the total count including the root.
//	
//	TC:- O( (LogN)^2 )
	static int countNodesOptimal(Node root) {
		if (root == null)
			return 0;

		int leftHeight = leftHeight(root);
		int rightHeight = rightHeight(root);

		if (leftHeight == rightHeight) {
			return (int) Math.pow(2, rightHeight) - 1;
		}

		int leftCount = countNodesOptimal(root.left);
		int rightCount = countNodesOptimal(root.right);

		return 1 + leftCount + rightCount;
	}

	private static int leftHeight(Node root) {
		return root == null ? 0 : 1 + leftHeight(root.left);
	}

	private static int rightHeight(Node root) {
		return root == null ? 0 : 1 + rightHeight(root.right);
	}
}
