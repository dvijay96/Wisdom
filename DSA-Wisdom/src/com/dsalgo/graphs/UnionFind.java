package com.dsalgo.graphs;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;

import com.dsalgo.utility.GraphUtils;

public class UnionFind {

	public static void main(String[] args) {

		List<List<Integer>> adj = GraphUtils.adjacencyListUndirected(5);

		System.out.println("Graph:- " + adj);
		UnionFind obj = new UnionFind();

		obj.unionByRank(adj);
	}

	public void unionByRank(List<List<Integer>> adj) {
		DisjointSet ds = new DisjointSet(adj.size());

		ThreadLocalRandom random = ThreadLocalRandom.current();
		int node1 = random.nextInt(adj.size());
		int node2 = random.nextInt(adj.size());

		for (int i = 0; i < adj.size(); i++) {
			for (int j : adj.get(i)) {
				ds.unionByRank(i, j);
			}

			BooleanSupplier connected = () -> ds.findParent(node1) == ds.findParent(node2);
			System.out.println("Are " + node1 + " & " + node2 + " in same component? " + connected.getAsBoolean());
		}

		/*
		 * ds.unionByRank(1, 2); ds.unionByRank(2, 3); ds.unionByRank(4, 5);
		 * ds.unionByRank(6, 7); ds.unionByRank(5, 6);
		 */

		System.out.println("Are " + node1 + " & " + node2 + " in same component? " + areConnected(ds, node1, node2));
	}

	private boolean areConnected(DisjointSet ds, int u, int v) {
		return ds.findParent(u) == ds.findParent(v);
	}
}
