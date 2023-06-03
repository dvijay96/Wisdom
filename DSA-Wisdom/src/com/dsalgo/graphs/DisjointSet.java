package com.dsalgo.graphs;

import java.util.Arrays;

public class DisjointSet {

	public static void main(String[] args) {

		byRank();

		bySize();

	}

	private static void bySize() {
		DisjointSet size = new DisjointSet(7);

		size.unionBySize(1, 2);
		size.unionBySize(2, 3);
		size.unionBySize(4, 5);
		size.unionBySize(6, 7);
		size.unionBySize(5, 6);

		// if 3 and 7 same or not
		if (size.findParent(3) == size.findParent(7)) {
			System.out.println("Same");
		} else
			System.out.println("Not Same");

		size.unionByRank(3, 7);
		if (size.findParent(3) == size.findParent(7)) {
			System.out.println("Same");
		} else
			System.out.println("Not Same");
	}

	private static void byRank() {
		DisjointSet rank = new DisjointSet(7);

		rank.unionByRank(1, 2);
		rank.unionByRank(2, 3);
		rank.unionByRank(4, 5);
		rank.unionByRank(6, 7);
		rank.unionByRank(5, 6);

		// if 3 and 7 same or not
		if (rank.findParent(3) == rank.findParent(7)) {
			System.out.println("Same");
		} else
			System.out.println("Not Same");

		rank.unionByRank(3, 7);
		if (rank.findParent(3) == rank.findParent(7)) {
			System.out.println("Same");
		} else
			System.out.println("Not Same");
	}

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

	protected int findParent(int x) {
		if (x == parent[x])
			return x;
		parent[x] = findParent(parent[x]);
		return parent[x];
	}

	protected void unionByRank(int u, int v) {
		int pu = findParent(u);
		int pv = findParent(v);

		if (pu == pv)
			return;

		// attach the smaller one to the larger one
		if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else if (rank[pv] < rank[pu]) {
			parent[pv] = pu;
		} else {
			parent[pv] = pu;
			rank[pu]++;
		}
	}

	protected void unionBySize(int u, int v) {
		int pu = findParent(u);
		int pv = findParent(v);

		if (pu == pv)
			return;
		if (size[pu] <= size[pv]) {
			parent[pu] = pv;
			size[pv] += size[pu];
		} else {
			parent[pv] = pu;
			size[pu] += size[pv];
		}
	}
}
