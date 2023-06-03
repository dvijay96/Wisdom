package com.dsalgo.graphs;

public class DSU {

	private int[] parent;
	private int[] rank;

	public DSU(int n) {

		parent = new int[n + 1];
		rank = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}

	public int findParent(int x) {
		if (x == parent[x])
			return x;
		parent[x] = findParent(parent[x]);
		return parent[x];
	}

	public void union(int u, int v) {
		int pu = findParent(u);
		int pv = findParent(v);

		if (pu == pv)
			return;

		if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else if (rank[pv] < rank[pu]) {
			parent[pv] = pu;
		} else {
			parent[pv] = pu;
			rank[pu]++;
		}
	}
}
