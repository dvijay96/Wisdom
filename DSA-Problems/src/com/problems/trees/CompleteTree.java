package com.problems.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class CompleteTree {

	public static void main(String[] args) {
		CompleteTree obj = new CompleteTree();

		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createCBTree(arr);

		System.out.println(obj.isCompleteTree(root));
		System.out.println(obj.isCompleteTreeII(root));
	}

//	Approach 1:
//			-> BFS the tree and push the nodes in queue until peek is not null
//			-> Then pop the queue peek until it is null
//			-> If queue is no empty then it is not a complete tree
//	
//	TC: O(N)
//	SC: O(N)
	public boolean isCompleteTree(Node root) {
		Queue<Node> bfs = new LinkedList<>();
		bfs.offer(root);
		while (bfs.peek() != null) {
			Node node = bfs.poll();
			bfs.offer(node.left);
			bfs.offer(node.right);
		}
		while (!bfs.isEmpty() && bfs.peek() == null)
			bfs.poll();
		return bfs.isEmpty();
	}

//	Approach:
//			-> BFS the tree and store it in a traversal list along with its null child.
//			-> find the first occurrence of null in the list
//			-> after first occurrence of null child , if there is a non null node present in the list then it is not complete.
//	
//	TC:- O(N) + O(N)
//	SC:- O(N) + O(N)
	public boolean isCompleteTreeII(Node root) {
		List<Node> list = new ArrayList<>();

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				Node node = queue.poll();

				if (node != null) {
					queue.add(node.left);
					queue.add(node.right);
				}
				list.add(node);
				size--;
			}
		}

		int i = 0;
		while (i < list.size() && list.get(i) != null) {
			i++;
		}

		while (i < list.size()) {
			if (list.get(i) != null) {
				return false;
			}
			i++;
		}
		// System.out.println(list);
		return true;
	}
}
