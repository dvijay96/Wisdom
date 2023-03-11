package com.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class ChildrenSumParent {

//	Given a Binary Tree. Check whether all of its nodes have the value equal to the sum of their child nodes.
//
//
//	Example 1:
//
//	Input:
//	     10
//	    /
//	  10 
//	Output: 1
//	Explanation: Here, every node is sum of
//	its left and right child.
//	
//	Example 2:
//
//	Input:
//	       1
//	     /   \
//	    4     3
//	   /  \
//	  5    N
//	Output: 0
//	Explanation: Here, 1 is the root node
//	and 4, 3 are its child nodes. 4 + 3 =
//	7 which is not equal to the value of
//	root node. Hence, this tree does not
//	satisfy the given conditions.

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(isSumProperty(root));
	}

//	Approach:- 
//			-> We do level order traversal.
//			-> At each point we check if left + right != parent
//			-> if such condition found, we end the process and return.
	static int isSumProperty(Node root) {
		if (root == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flag = false;
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				int left = 0;
				int right = 0;
				if (node.left != null) {
					queue.add(node.left);
					left = node.left.data;
				}
				if (node.right != null) {
					queue.add(node.right);
					right = node.right.data;
				}

				if ((left > 0 || right > 0) && node.data != left + right) {
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

		return queue.isEmpty() ? 1 : 0;
	}
}
