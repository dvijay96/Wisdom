package com.problems.trees;

import java.util.HashMap;
import java.util.Map;

import com.problems.java.utility.TreeUtils;

public class BTFromPostInorder {

	public static void main(String[] args) {
		BTFromPostInorder obj = new BTFromPostInorder();

		int[] inorder = { 8, 2, 1, 7, 4, 3, 6, 5 };
		int[] postorder = { 8, 2, 7, 4, 6, 5, 3, 1 };

		Node root = obj.buildTree(inorder, postorder);

		TreeUtils.printLevelOrder(root);
	}

	public Node buildTree(int[] inorder, int[] postorder) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	private Node buildTree(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> map) {

		if (postStart > postEnd || inStart > inEnd) {
			return null;
		}
		Node node = new Node(postorder[postEnd]);
		int inpos = map.get(postorder[postEnd]);
		int leftNodes = inpos - inStart;

		node.left = buildTree(postorder, postStart, postStart + leftNodes - 1, inorder, inStart, inpos - 1, map);
		node.right = buildTree(postorder, postStart + leftNodes, postEnd - 1, inorder, inpos + 1, inEnd, map);
		return node;
	}
}
