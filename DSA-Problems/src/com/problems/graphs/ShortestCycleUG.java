package com.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestCycleUG {

	public static void main(String[] args) {
		int[][] edges = { { 1, 3 }, { 3, 5 }, { 5, 7 }, { 7, 1 }, { 0, 2 }, { 2, 4 }, { 4, 0 }, { 6, 0 }, { 6, 1 } };

		int n = 8;
		
		ShortestCycleUG obj = new ShortestCycleUG();

		System.out.println(obj.findShortestCycle(n, edges));

	}

	public int findShortestCycle(int n, int[][] edges) {
		
		int ans = Integer.MAX_VALUE;
		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		for (int i = 0; i < n; i++) {
			int[] dist = new int[n];

			for (int j = 0; j < n; j++) {
				dist[j] = 100000;
			}

			int[] parent = new int[n];

			for (int j = 0; j < n; j++) {
				parent[j] = -1;
			}

			Queue<Integer> queue = new LinkedList<>();

			dist[i] = 0;
			queue.add(i);
			while (!queue.isEmpty()) {
				int par = queue.poll();

				for (int child : adj.get(par)) {
					if (dist[child] == 100000) {
						dist[child] = 1 + dist[par];
						parent[child] = par;
						queue.add(child);
					} else if (parent[child] != par && parent[par] != child) {
						ans = Math.min(ans, dist[par] + dist[child] + 1);
					}
				}
			}
		}

		if (ans == Integer.MAX_VALUE) {
			return -1;
		}
		return ans;
	}
}
