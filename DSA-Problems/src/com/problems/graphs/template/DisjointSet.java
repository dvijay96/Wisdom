package com.problems.graphs.template;

import java.util.Arrays;

public class DisjointSet {

	private int[] parent;
	private int[] rank;
	private int[] size;

	public DisjointSet(int n) {

		parent = new int[n + 1];
		rank = new int[n + 1];
		size = new int[n + 1];

		Arrays.fill(size, 1);

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

	public void unionByRank(int u, int v) {
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

	public void unionBySize(int u, int v) {
		int pu = findParent(u);
		int pv = findParent(v);

		if (pu == pv)
			return;
		if (size[pu] < size[pv]) {
			parent[pu] = pv;
			size[pv] += size[pu];
		} else {
			parent[pv] = pu;
			size[pu] += size[pv];
		}
	}

	public int[] getParent() {
		return parent;
	}

	public int[] getSize() {
		return size;
	}

}
