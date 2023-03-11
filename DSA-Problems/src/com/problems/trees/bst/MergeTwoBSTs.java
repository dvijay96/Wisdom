package com.problems.trees.bst;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class MergeTwoBSTs {

//	Given two BSTs, return elements of both BSTs in sorted form.
//
//
//			Example 1:
//
//			Input:
//			BST1:
//			       5
//			     /   \
//			    3     6
//			   / \
//			  2   4  
//			BST2:
//			        2
//			      /   \
//			     1     3
//			            \
//			             7
//			            /
//			           6
//			Output: 1 2 2 3 3 4 5 6 6 7
//			Explanation: 
//			After merging and sorting the
//			two BST we get 1 2 2 3 3 4 5 6 6 7.
//			
//			Example 2:
//
//			Input:
//			BST1:
//			       12
//			     /   
//			    9
//			   / \    
//			  6   11
//			BST2:
//			      8
//			    /  \
//			   5    10
//			  /
//			 2
//			Output: 2 5 6 8 9 10 11 12
//			Explanation: 
//			After merging and sorting the
//			two BST we get 2 5 6 8 9 10 11 12.
//			
	public static void main(String[] args) {

		MergeTwoBSTs obj = new MergeTwoBSTs();

		int[] arr = ArrayUtils.getIntArray(10);

		Node tree1 = TreeUtils.createBST(arr);

		arr = ArrayUtils.getIntArray(12);

		Node tree2 = TreeUtils.createBST(arr);

		System.out.println(obj.merge(tree1, tree2));
	}

	public List<Integer> merge(Node root1, Node root2) {
		List<Integer> nodes1 = new ArrayList<>();
		inorder(root1, nodes1);
		List<Integer> nodes2 = new ArrayList<>();
		inorder(root2, nodes2);
		return merge(nodes1, nodes2);
	}

	void inorder(Node root, List<Integer> nodes) {
		if (root != null) {
			inorder(root.left, nodes);
			nodes.add(root.data);
			inorder(root.right, nodes);
		}
	}

	List<Integer> merge(List<Integer> nodes1, List<Integer> nodes2) {
		List<Integer> ans = new ArrayList<>();
		int i = 0;
		int j = 0;

		while (i < nodes1.size() && j < nodes2.size()) {
			if (nodes1.get(i) < nodes2.get(j)) {
				ans.add(nodes1.get(i++));
			} else {
				ans.add(nodes2.get(j++));
			}
		}

		while (i < nodes1.size()) {
			ans.add(nodes1.get(i++));
		}

		while (j < nodes2.size()) {
			ans.add(nodes2.get(j++));
		}

		return ans;
	}

}
