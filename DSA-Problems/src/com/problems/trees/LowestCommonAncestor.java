package com.problems.trees;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class LowestCommonAncestor {

//	Given a Binary Tree with all unique values and two nodes value, n1 and n2. The task is to find the lowest common ancestor of the given two nodes. We may assume that either both n1 and n2 are present in the tree or none of them are present.
//
//	LCA: It is the first common ancestor of both the nodes n1 and n2 from bottom of tree.
//
//	Example 1:
//
//	Input:
//	n1 = 2 , n2 = 3  
//	       1 
//	      / \ 
//	     2   3
//	Output: 1
//	Explanation:
//	LCA of 2 and 3 is 1.
//	
//	Example 2:
//
//	Input:
//	n1 = 5 , n2 = 4
//	
//								 3
//							  /    \
//							 5       1
//					       /   \   /   \
//					      6    2  0     8
//					         /   \
//					        7     4
//	Output: 5
//	Explanation:
//	The LCA of nodes 5 and 4 is 5, since a node can be a descendant(successor) of itself according to the LCA definition. 
	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArrayUnique(10);

		ArrayUtils.shuffle(arr);
		
		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(RootToNodePath.rootToNodesPaths(root));

		System.out.println(lowestCommonAncestor(root, 5, 9));
	}

//	Approach:- 
//	
//			-> Traverse the tree in dfs fashion.
//			-> If the curr node is either target1 or target2, we return that node.
//			-> For a parent node, if either left or right nodes are not null, return non null child.
//			-> if both are not null, then return curr node.
//			-> This curr returned node will be used in deciding lowest common ancestor.

//	TC:- O(N)
//	SC:- O(N)
	public static Node lowestCommonAncestor(Node root, int p, int q) {
		if (root == null)
			return null;

		if (root.data == p || root.data == q) {
			return root;
		}

		Node left = lowestCommonAncestor(root.left, p, q);
		Node right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
}
