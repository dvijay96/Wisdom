package com.problems.graphs;

import java.util.ArrayList;
import java.util.List;

import com.problems.metadata.Hard;

@Hard
public class SimilarStringGroups {

	public static void main(String[] args) {
		SimilarStringGroups obj = new SimilarStringGroups();
		String[] strs = { "tars", "rats", "arts", "star" };

		System.out.println(obj.numSimilarGroupsUsingDFS(strs));
		System.out.println(obj.numSimilarGroupsUsingUnionFind(strs));

	}

	public int numSimilarGroupsUsingDFS(String[] strs) {
		List<List<Integer>> adj = new ArrayList<>();

		int n = strs.length;

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (areEqual(strs[i], strs[j])) {
					adj.get(i).add(j);
					adj.get(j).add(i);
				}
			}
		}

		boolean[] vis = new boolean[n];
		int count = 0;

		for (int i = 0; i < n; i++) {
			if (!vis[i]) {
				count++;
				dfs(i, adj, vis);
			}
		}
		return count;
	}

	private void dfs(int src, List<List<Integer>> adj, boolean[] vis) {
		vis[src] = true;

		for (int node : adj.get(src)) {
			if (!vis[node]) {
				dfs(node, adj, vis);
			}
		}
	}

	/****************************************************************************************************/
	/*
	 * Using Union find
	 */
	public int numSimilarGroupsUsingUnionFind(String[] strs) {

		int n = strs.length;

		DisJointSet ds = new DisJointSet(n);

		int count = n;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (areEqual(strs[i], strs[j]) && ds.findParent(i) != ds.findParent(j)) {
					count--;
					ds.union(i, j);
				}
			}
		}

		return count;
	}

	boolean areEqual(String s1, String s2) {
		int diff = 0;

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diff++;
			}
		}

		return diff == 0 || diff == 2;
	}

	final class DisJointSet {
		private int[] rank;
		private int[] parent;

		DisJointSet(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
			rank = new int[n];
		}

		public int findParent(int node) {
			if (parent[node] == node) {
				return node;
			}
			parent[node] = findParent(parent[node]);
			return parent[node];
		}

		public void union(int u, int v) {
			int pu = findParent(u);
			int pv = findParent(v);

			if (pu == pv) {
				return;
			}
			if (rank[pu] < rank[pv]) {
				parent[pu] = pv;
			} else if (rank[pv] < rank[pu]) {
				parent[pv] = pu;
			} else if (rank[pu] == rank[pv]) {
				parent[pu] = pv;
				rank[pv]++;
			}
		}
	}
}
