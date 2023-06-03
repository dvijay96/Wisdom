package com.problems.leetcode.daily.graphs;

import java.util.Arrays;

import com.problems.metadata.Hard;

@Hard
public class RemoveMaxNumEdges {

//	Alice and Bob have an undirected graph of n nodes and three types of edges:
//
//		Type 1: Can be traversed by Alice only.
//		Type 2: Can be traversed by Bob only.
//		Type 3: Can be traversed by both Alice and Bob.
//		
//		Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei 
//		between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, 
//		the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob 
//		if starting from any node, they can reach all other nodes.
//
//		Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse 
//				the graph.
	
	public static void main(String[] args) {
		RemoveMaxNumEdges obj = new RemoveMaxNumEdges();

		int n = 4;
		int[][] edges = { { 3, 1, 2 }, { 3, 2, 3 }, { 1, 1, 3 }, { 1, 2, 4 }, { 1, 1, 2 }, { 2, 3, 4 } };

		System.out.println(obj.maxNumEdgesToRemove(n, edges));
	}

	public int maxNumEdgesToRemove(int n, int[][] edges) {

		Arrays.sort(edges, (a, b) -> b[0] - a[0]);
		int removedEdges = 0;
		int aliceEdges = 0;
		int bobEdges = 0;

		DisJointSet alice = new DisJointSet(n);
		DisJointSet bob = new DisJointSet(n);

		for (int[] edge : edges) {
			int type = edge[0];
			int u = edge[1];
			int v = edge[2];

			switch (type) {
			case 3:
				if (alice.union(u, v)) {
					bob.union(u, v);
					aliceEdges++;
					bobEdges++;
				} else {
					removedEdges++;
				}
				break;

			case 2:
				if (bob.union(u, v)) {
					bobEdges++;
				} else {
					removedEdges++;
				}
				break;

			case 1:
				if (alice.union(u, v)) {
					aliceEdges++;
				} else {
					removedEdges++;
				}
				break;
			default:
			}
		}

		return (aliceEdges == n - 1 && bobEdges == n - 1) ? removedEdges : -1;
	}

	private class DisJointSet {

		private int[] rank;
		private int[] parent;

		DisJointSet(int n) {
			rank = new int[n + 1];

			parent = new int[n + 1];

			for (int i = 0; i <= n; i++) {
				parent[i] = i;
			}
		}

		public boolean union(int u, int v) {
			int pu = parent(u);
			int pv = parent(v);

			if (pu == pv) {
				return false;
			}

			if (rank[pu] < rank[pv]) {
				parent[pu] = pv;
			} else if (rank[pv] < rank[pu]) {
				parent[pv] = pu;
			} else {
				parent[pu] = pv;
				rank[pv]++;
			}
			return true;
		}

		public int parent(int node) {
			if (parent[node] == node) {
				return node;
			}

			parent[node] = parent(parent[node]);

			return parent[node];
		}
	}
}
