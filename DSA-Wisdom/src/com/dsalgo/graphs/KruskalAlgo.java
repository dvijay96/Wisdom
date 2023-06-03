package com.dsalgo.graphs;

import java.util.Arrays;

public class KruskalAlgo {

	// Kruskal's Algo is used in finding the minimum spanning tree sum using
	// Disjoint Set data structure.

	public static void main(String[] args) {
		KruskalAlgo obj = new KruskalAlgo();
		int v = 5;
		int[][] edges = { { 0, 1, 2 }, { 0, 3, 6 }, { 1, 2, 3 }, { 1, 3, 8 }, { 1, 4, 5 }, { 4, 2, 7 } };

		System.out.println(obj.spanningTree(v, edges));
	}

//	Approach:-
//			-> Sort the Edges array by edge weight in asc order.
//			-> Perform the DSU operations on the sorted edges.

//	TC:- O(E*LogE) + O(4$)
//	SC:- O(V)
	int spanningTree(int vertives, int[][] edges) {

		Arrays.sort(edges, (a, b) -> a[2] - b[2]);

		DisjointSet dsu = new DisjointSet(vertives);

		int sum = 0;
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];

			if (dsu.findParent(u) != dsu.findParent(v)) {
				dsu.unionByRank(u, v);
				sum += wt;
			}
		}

		return sum;
	}
}
