package com.problems.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.GraphUtils;

public class DetectCycleInGraph {

	public static void main(String[] args) {
		DetectCycleInGraph obj = new DetectCycleInGraph();
		List<List<Integer>> adj = GraphUtils.adjacencyListUndirected(5);

		for (int i = 0; i < adj.size(); i++) {
			System.out.println(i + " -> " + adj.get(i));
		}

		System.out.println(obj.isCycleUsingBFS(5, adj));
		System.out.println(obj.isCycleUsingDFS(5, adj));
	}

//	Approach:-
//			-> We need to traverse the graph to check if there exists a cycle
//			-> We can detect a cycle if we come at a node where two traversal collide
//				for ex:
//					
//							1 ------- 2
//							|		  |
//							|		  |
//							|		  |
//							3---------4
//
//						source = 1
//					traverse I :-  1-> 3 -> 4
//					traverse II:-  1-> 2 -> 4
//					
//					both traversal from 2 & 3 reach at a point 4, which can't be possible in non cyclic graph.
//			
//			-> Since there can be connected graphs as well, so we perform traversal from each node as source node to check for cycle.
//			-> We will use BFS traversal here in which we store the pair of node and its parent i.e where it came from (node,parent).
//			-> For source node it can be (src, -1).
//
//	TC:- O(V) + O(V+2E)			for traversing from each source node and other one for BFS traversal
//	SC:- O(V) + O(V)			for storing visited array and BFS for queue.

	public boolean isCycleUsingBFS(int v, List<List<Integer>> adj) {

		boolean[] visited = new boolean[v];

		for (int src = 0; src < v; src++) {
			if (!visited[src] && isCyclePresent(src, adj, visited)) {
				return true;
			}
		}
		return false;
	}

	private boolean isCyclePresent(int src, List<List<Integer>> adj, boolean[] visited) {
		visited[src] = true;
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { src, -1 });

		while (!queue.isEmpty()) {
			int node = queue.peek()[0];
			int parent = queue.peek()[1];
			queue.remove();
			for (Integer num : adj.get(node)) {
				if (!visited[num]) {
					visited[num] = true;
					queue.add(new int[] { num, node });
				} else if (parent != num) { // if the visited node is a parent then
					return true; // how can it be visited if its not a parent for that path.
				} // which means its been visited by some other path and this path crossing this
					// node
			} // detects a path which will lead to the source node, hence a cycle.
		}
		return false;
	}

	public boolean isCycleUsingDFS(int v, List<List<Integer>> adj) {
		boolean[] visited = new boolean[v];

		for (int i = 0; i < v; i++) {
			if (!visited[i] && isCyclePresent(i, -1, visited, adj)) {
				return true;
			}
		}
		return false;
	}

	private boolean isCyclePresent(int src, int parent, boolean[] visited, List<List<Integer>> adj) {
		visited[src] = true;

		for (Integer neig : adj.get(src)) {
			if (!visited[neig]) {
				if (isCyclePresent(neig, src, visited, adj))
					return true;
			} else if (parent != neig) {
				return true;
			}
		}
		return false;
	}
}
