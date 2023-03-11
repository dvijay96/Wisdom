package com.problems.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class VerticalTraversal {

//	Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
//
//	For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. 
//	The root of the tree is at (0, 0).
//
//	The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and 
//	ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
//
//	Return the vertical order traversal of the binary tree.

//	Example 1:
//								3	(0,0)
//							  /   \
//					(-1,1)	 9    20  (1,1)
//								 /  \
//						(0,2)	15	17  (2,2)
//	
//	
//		Input: root = [3,9,20,null,null,15,7]
//		Output: [[9],[3,15],[20],[7]]
//		Explanation:
//		Column -1: Only node 9 is in this column.
//		Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
//		Column 1: Only node 20 is in this column.
//		Column 2: Only node 7 is in this column.

	public static void main(String[] args) {
		VerticalTraversal obj = new VerticalTraversal();
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);

//		System.out.println(obj.verticalTraversal(root));

		System.out.println("\n\n1:-\n" + obj.verticalTraversalWithLevelOrder(root));

		System.out.println("\n\n2:-\n" + obj.verticalTraversalII(root));
	}

//	Approach:-
//		-> Thinking of a imaginary x axis which starts at root with 0 and on the left goes in negative and on right goes positive.
//		-> Now traverse each node and store these nodes in a TreeMap for its axis as key and value as another TreeMap of level as key and PriorityQueue
//			as value so as to keep the node values by sorted.
	public List<List<Integer>> verticalTraversal(Node root) {
		Map<Integer, Map<Integer, Queue<Integer>>> map = new TreeMap<>();
		dfs(root, map, 0, 1);

		List<List<Integer>> ans = new ArrayList<>();

		map.entrySet().forEach(entry -> {
			List<Integer> list = new ArrayList<>();
			entry.getValue().entrySet().forEach(level -> {

				while (!level.getValue().isEmpty()) {
					list.add(level.getValue().poll());
				}

			});
			ans.add(list);
		});

		return ans;
	}

	private void dfs(Node root, Map<Integer, Map<Integer, Queue<Integer>>> map, int x, int level) {

		if (root == null) {
			return;
		}

		Map<Integer, Queue<Integer>> levelMap = map.getOrDefault(x, new TreeMap<>());
		Queue<Integer> queue = levelMap.getOrDefault(level, new PriorityQueue<>());
		queue.add(root.data);

		levelMap.put(level, queue);
		map.put(x, levelMap);

		dfs(root.left, map, x - 1, level + 1);
		dfs(root.right, map, x + 1, level + 1);
	}

	public List<List<Integer>> verticalTraversalWithLevelOrder(Node root) {
		Map<Integer, Map<Integer, Queue<Integer>>> map = new TreeMap<>();

		bfs(root, map);

		List<List<Integer>> ans = new ArrayList<>();
		for (Map<Integer, Queue<Integer>> levelMaps : map.values()) {
			ans.add(new ArrayList<>());

			for (Queue<Integer> pq : levelMaps.values()) {
				while (!pq.isEmpty()) {
					ans.get(ans.size() - 1).add(pq.poll());
				}
			}
		}

		return ans;
	}

	private void bfs(Node root, Map<Integer, Map<Integer, Queue<Integer>>> map) {
		Queue<Tuple> queue = new LinkedList<>();
		queue.add(new Tuple(root, 0, 0));

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Node node = queue.peek().node;
				int level = queue.peek().level;
				int axis = queue.peek().axis;
				queue.poll();
				if (node.left != null) {
					queue.add(new Tuple(node.left, level + 1, axis - 1));
				}
				if (node.right != null) {
					queue.add(new Tuple(node.right, level + 1, axis + 1));
				}

//				if (!map.containsKey(axis)) {
//					map.put(axis, new TreeMap<>());
//				}
				map.computeIfAbsent(axis, val -> new TreeMap<>());
//				if (!map.get(axis).containsKey(level)) {
//					map.get(axis).put(level, new PriorityQueue<>());
//				}
				map.get(axis).computeIfAbsent(level, val -> new PriorityQueue<>()).add(node.data);

//				map.get(axis).get(level).add(node.data);
			}
		}
	}

//	Approach:
//		-> Similar but instead of creating a map we could directly make use of a PriorityQueue.
//		-> We could store a pair of node value, its axis and its level as a Tuple in the PriorityQueue.
//		-> This priority queue will sort based on axis as its 1st priority else level as 2nd priority and lastly the node value.
	public List<Integer> verticalTraversalII(Node root) {
		Queue<PairX> queue = new PriorityQueue<>((a, b) -> {
			if (a.axis != b.axis)
				return a.axis - b.axis;
			else if (a.level != b.level)
				return a.level - b.level;
			else
				return a.val - b.val;
		});

		dfs(root, queue, 0, 1);

		List<Integer> ans = new ArrayList<>();

		List<List<Integer>> list = new ArrayList<>();

		int axis = queue.peek().axis;
		list.add(new ArrayList<>());
		while (!queue.isEmpty()) {
			PairX pair = queue.poll();
			if (axis != pair.axis) {
				list.add(new ArrayList<>());
				axis = pair.axis;
			}
			list.get(list.size() - 1).add(pair.val);

			ans.add(pair.val);
		}

		System.out.println(list);
		return ans;
	}

	private void dfs(Node root, Queue<PairX> queue, int x, int level) {
		if (root == null) {
			return;
		}

		queue.add(new PairX(root.data, level, x));

		dfs(root.left, queue, x - 1, level + 1);
		dfs(root.right, queue, x + 1, level + 1);

	}

	public List<Integer> verticalOrder(Node root) {
		final class PairNode {
			Node node;
			int hd;

			PairNode(Node node, int hd) {
				this.node = node;
				this.hd = hd;
			}
		}
		Queue<PairNode> q = new LinkedList<>();
		Map<Integer, ArrayList<Integer>> tm = new TreeMap<>();
		q.add(new PairNode(root, 0));
		while (!q.isEmpty()) {
			PairNode curr = q.poll();
			if (tm.containsKey(curr.hd)) {
				tm.get(curr.hd).add(curr.node.data);
			} else {
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(curr.node.data);
				tm.put(curr.hd, temp);
			}

			if (curr.node.left != null)
				q.add(new PairNode(curr.node.left, curr.hd - 1));
			if (curr.node.right != null)
				q.add(new PairNode(curr.node.right, curr.hd + 1));
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (Integer i : tm.keySet()) {
			ans.addAll(tm.get(i));
		}
		return ans;
	}

}

class PairX {
	int val;
	int level;
	int axis;

	/**
	 * 
	 * @param val
	 * @param level
	 * @param axis
	 */
	public PairX(int val, int level, int axis) {
		super();
		this.val = val;
		this.level = level;
		this.axis = axis;
	}

}

class Tuple {
	Node node;
	int level;
	int axis;

	public Tuple(Node node, int level, int axis) {
		super();
		this.node = node;
		this.level = level;
		this.axis = axis;
	}

}
