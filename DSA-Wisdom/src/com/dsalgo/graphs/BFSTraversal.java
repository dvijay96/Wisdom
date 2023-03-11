package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSTraversal {

	public static void main(String[] args) {
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(0).add(1);
		adj.get(1).add(0);
		adj.get(0).add(4);
		adj.get(4).add(0);
		adj.get(1).add(2);
		adj.get(2).add(1);
		adj.get(1).add(3);
		adj.get(3).add(1);

		List<Integer> ans = bfsOfGraph(5, adj);
		int n = ans.size();
		System.out.println("BFS Traversal of the graph:");
		for (int i = 0; i < n; i++) {
			System.out.print(ans.get(i) + " ");
		}

	}

	// 0 based graph
	private static List<Integer> bfsOfGraph(int nodes, List<List<Integer>> adj) {
		List<Integer> ans = new ArrayList<>();
		boolean[] visited = new boolean[nodes];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			Integer node = queue.poll();
			ans.add(node);

			for (Integer neighbour : adj.get(node)) {
				if (!visited[neighbour]) {
					visited[neighbour] = true;
					queue.add(neighbour);
				}
			}
		}
		return ans;
	}

}
