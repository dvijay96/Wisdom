package com.problems.trees.bst;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class LeastCommonAncestor {

//	Given a Binary Search Tree (with all values unique) and two node values. Find the Lowest Common Ancestors of the 
//	two nodes in the BST.
//
//	Example 1:
//
//	Input:
//	              5
//	           /    \
//	         4       6
//	          \       \
//	           3       7
//	                    \
//	                     8
//	n1 = 7, n2 = 8
//	Output: 7
//	
//	Example 2:
//
//	Input:
//	     2
//	   /   \
//	  1     3
//	n1 = 1, n2 = 3
//	Output: 2
	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBST(arr);

		System.out.println(lca(root, 4, 8));
	}

//	Approach:-
//			-> With the property of BST , left subtree nodes less than root and right sub tree nodes greater than root.
//			-> There are 3 possibilities
//				 --> n1 < root AND n2 > root OR  n2 < root AND n1 > root, in both cases, the curr root is the lca
//				 --> if the curr node is either n1 OR n2
//				 --> if both n1 and n2 are lesser than root, search in left.
//				 --> if both n1 and n2 are greater than root, search in right.
//				 
//	TC:- O(H) , height of the tree.
	static Node lca(Node root, int n1, int n2) {
		Node temp = root;

		while (temp != null) {
			if ((n1 < temp.data && n2 > temp.data) || (n2 < temp.data && n1 > temp.data)
					|| (temp.data == n1 || temp.data == n2)) {
				return temp;
			} else if (n1 < temp.data && n2 < temp.data) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		return temp;
	}
}
