package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm {

//Spanning Tree:
//	A spanning tree is a tree in which we have N nodes(i.e. All the nodes present in the original graph) and N-1 edges
//	and all nodes are reachable from each other. 

//Minimum Spanning Tree:
//	Among all possible spanning trees of a graph, the minimum spanning tree is the one for which the sum of all the 
//	edge weights is the minimum.

//	Prim's Algo is used to detect such minimum spanning tree in a graph.

	public static void main(String[] args) {
		PrimsAlgorithm obj = new PrimsAlgorithm();

		int v = 5;
		int[][] edges = { { 0, 1, 2 }, { 0, 3, 6 }, { 1, 2, 3 }, { 1, 3, 8 }, { 1, 4, 5 }, { 4, 2, 7 } };

		List<int[]> mst = new ArrayList<>();

		System.out.println(obj.spanningTree(v, edges, mst));

		System.out.println("Minimum spanning tree edges: ");

		mst.forEach(edge -> System.out.print(Arrays.toString(edge)));
	}

//	Approach:- 
//			-> Greedily selecting a node's unvisited adjacent node with the minimum edge weight.
//			-> Doing so for every node, we can get the sum of all the edge weights of the minimum spanning tree and the spanning tree itself
//				(if we wish to) as well.
//			-> To do the above process, we make use of Min Heap for edge weights.
//			-> We do a BFS kind of traversal but only for unvisited nodes while selecting the min edge weight. 
//			-> Also adding the weight along the way.

//	TC:- O(E*logE)		
//	SC:- O(E + V)
	private int spanningTree(int vertices, int[][] edges, List<int[]> mst) {

		List<List<Pair>> graph = new ArrayList<>();

		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];

			graph.get(u).add(new Pair(v, -1, wt));
			graph.get(v).add(new Pair(u, -1, wt));
		}

		int sum = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.wt - b.wt);

		boolean[] vis = new boolean[vertices];

		pq.add(new Pair(0, -1, 0));

		while (!pq.isEmpty()) { // E*logE
			Pair p = pq.poll();

			int node = p.node;

			if (!vis[node]) {

				vis[node] = true;

				if (p.parent != -1) {
					mst.add(new int[] { p.parent, p.node });	// add the edge
				}

				sum += p.wt;

				for (Pair adj : graph.get(node)) { // E*logE
					if (!vis[adj.node]) {
						pq.add(new Pair(adj.node, node, adj.wt));
					}
				}
			}
		}

		return sum;
	}

	private class Pair {
		int node;
		int parent;
		int wt;

		Pair(int node, int parent, int wt) {
			this.node = node;
			this.parent = parent;
			this.wt = wt;
		}
	}
}
