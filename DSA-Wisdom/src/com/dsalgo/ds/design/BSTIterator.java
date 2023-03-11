package com.dsalgo.ds.design;

import java.util.LinkedList;

import com.dsalgo.binary.trees.Node;
import com.dsalgo.utility.ArrayUtils;
import com.dsalgo.utility.TreeUtils;

public class BSTIterator {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);
		Node root = TreeUtils.createBST(arr);

		BSTIterator itr = new BSTIterator(root);

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

	}

	private LinkedList<Node> stack;

	public BSTIterator(Node root) {
		stack = new LinkedList<>();
		pushAll(root);
	}

	private void pushAll(Node root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	public int next() {
		Node node = stack.pop();
		pushAll(node.right);
		return node.data;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}
}
