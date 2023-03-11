package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

public class DFSTraversal {

	public static void main(String[] args) {
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);

		List<Integer> dfs = new ArrayList<>();
		boolean[] visited = new boolean[5];
		dfsOfGraph(0, visited, adj, dfs);

		System.out.println(dfs);

	}

	private static void dfsOfGraph(int node, boolean[] visited, List<List<Integer>> adj, List<Integer> dfs) {
		visited[node] = true;
		dfs.add(node);

		for (Integer n : adj.get(node)) {
			if (!visited[n]) {
				dfsOfGraph(n, visited, adj, dfs);
			}
		}
	}

}
