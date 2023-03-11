package com.problems.trees.bst;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class SearchInBST {

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBST(arr);

		System.out.println(search(root, 6) != -1);
	}

	static int search(Node root, int key) {

		while (root != null) {
			if (key == root.data)
				return key;
			else if (key > root.data) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return -1;
	}
}
