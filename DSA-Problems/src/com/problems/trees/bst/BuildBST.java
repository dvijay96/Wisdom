package com.problems.trees.bst;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;
import com.problems.trees.Node;

public class BuildBST {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBST(arr);

		TreeUtils.printLevelOrder(root);
	}

}
