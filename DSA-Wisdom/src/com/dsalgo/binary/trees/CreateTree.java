package com.dsalgo.binary.trees;

import java.util.LinkedList;
import java.util.Queue;

import com.dsalgo.utility.ArrayUtils;

public class CreateTree {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(7);

		ArrayUtils.print(arr);
		Node root = new Node(arr[0]);

		createTree(root, arr);

		System.out.println("Inorder:- ");
		TreeTraverser.inorder(root);
		System.out.println("\nPreorder:- ");
		TreeTraverser.preorder(root);
		System.out.println("\nPostOrder:- ");
		TreeTraverser.postOrder(root);
		System.out.println("\nLevel Order:-");
		TreeTraverser.levelOrder(root);
	}

	private static void createTree(Node root, int[] arr) {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		int i = 1;
		int n = arr.length;
		while (i < n) {
			Node croot = q.poll();
//			q.remove();
			croot.left = new Node(arr[i++]);
			q.add(croot.left);

			if (i >= n)
				break;
			croot.right = new Node(arr[i++]);
			q.add(croot.right);
		}

	}

}
