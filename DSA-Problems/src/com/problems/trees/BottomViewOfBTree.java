package com.problems.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class BottomViewOfBTree {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

		System.out.println(bottomView(root));
	}

//	Approach:-
//		-> select the last element for each vertical line.
//		-> Perform BFS and store the element in a map for key = axis, value = node value.
//		-> map values is the bottom view of the tree.
	public static List<Integer> bottomView(Node root) {
		class Pair {
			Node node;
			int axis;

			Pair(Node node, int axis) {
				this.node = node;
				this.axis = axis;
			}
		}

		if (root == null) {
			return Collections.emptyList();
		}
		Map<Integer, Integer> map = new TreeMap<>();

		Queue<Pair> queue = new LinkedList<>();

		queue.add(new Pair(root, 0));

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Node node = queue.peek().node;
				int axis = queue.peek().axis;
				queue.poll();

				if (node.left != null) {
					queue.add(new Pair(node.left, axis - 1));
				}
				if (node.right != null) {
					queue.add(new Pair(node.right, axis + 1));
				}

				map.put(axis, node.data);
			}
		}
		return new ArrayList<>(map.values());
	}
}
