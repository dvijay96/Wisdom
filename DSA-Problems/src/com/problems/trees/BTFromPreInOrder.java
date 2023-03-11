package com.problems.trees;

import java.util.HashMap;
import java.util.Map;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class BTFromPreInOrder {

	public static void main(String[] args) {
		BTFromPreInOrder obj = new BTFromPreInOrder();

		int[] arr = ArrayUtils.getIntArrayUnique(10);
		Node tree = TreeUtils.createBinaryTree(arr);

		int[] preorder = TreeUtils.preorderArray(tree);
		int[] inorder = TreeUtils.inorderArray(tree);

		Node root = obj.buildTree(preorder, inorder);

		TreeUtils.printLevelOrder(root);

	}

	public Node buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	private Node buildTree(int[] pre, int preStart, int preEnd, int[] ino, int inStart, int inEnd,
			Map<Integer, Integer> map) {
		if (preStart > preEnd || inStart > inEnd)
			return null;
		Node node = new Node(pre[preStart]);
		int inRoot = map.get(node.data);
		int numLeft = inRoot - inStart;
		node.left = buildTree(pre, preStart + 1, preStart + numLeft, ino, inStart, inRoot - 1, map);
		node.right = buildTree(pre, preStart + numLeft + 1, preEnd, ino, inRoot + 1, inEnd, map);
		return node;
	}
}
