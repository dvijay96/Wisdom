package com.problems.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathUWG {

//	You are given a weighted undirected graph having n+1 vertices numbered from 0 to n and m edges describing there 
//	are edges between a to b with some weight, find the shortest path between the vertex 1 and the vertex n and 
//	if path does not exist then return a list consisting of only -1.
//
//			Example:
//			Input:
//			n = 5, m= 6
//			edges = [[1,2,2], [2,5,5], [2,3,4], [1,4,1],[4,3,3],[3,5,1]]
//			Output:
//			1 4 3 5
//			Explanation:
//			Shortest path from 1 to n is by the path 1 4 3 5

	public static void main(String[] args) {

		int[][] edges = { { 1, 2, 2 }, { 2, 5, 5 }, { 2, 3, 4 }, { 1, 4, 1 }, { 4, 3, 3 }, { 3, 5, 1 } };

		int n = 5;

		ShortestPathUWG obj = new ShortestPathUWG();

		System.out.println(obj.shortestPath(n, 6, edges));

	}

//	Approach:-
//			-> We use Dijkstra's algo to find shortest path
//			-> Since Dijkstra's algo helps in finding shortest distance from a source node to all other nodes, we need 
//				to modify the algo slightly to get the shortest path.
//			-> To get the path info, we will store the parent node info for each shortest distance adjacent node.
//			-> After performing the algo, we move from destination node to source node via the parent array until 
//				we reach source, i.e parent[source] = -1 or 0.
//			-> Then reverse the path to get source to destination.
//	
//	Time Complexity: O( E log(V) ) { for Dijkstra’s Algorithm } + O(V) { for backtracking in order to find the parent 
//						for each node } Where E = Number of edges and V = Number of Nodes.
//
//	Space Complexity: O( |E| + |V| ) { for priority queue and dist array } + O( |V| ) { for storing the final path } 
//						Where E = Number of edges and V = Number of Nodes.
	public List<Integer> shortestPath(int n, int m, int[][] edges) {
		List<List<Pair>> adj = new ArrayList<>();

		for (int i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];

			Pair p1 = new Pair(u, wt);
			Pair p2 = new Pair(v, wt);

			adj.get(u).add(p2);
			adj.get(v).add(p1);
		}

		int[] parent = new int[n + 1];
		int[] dist = new int[n + 1];

		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);

		for (int i = 0; i < n + 1; i++) {
			dist[i] = 100000000;
			parent[i] = i;
		}

		dist[1] = 0;
		parent[1] = -1;

		pq.add(new Pair(1, 0));

		while (!pq.isEmpty()) {

			Pair pair = pq.poll();

			for (Pair p : adj.get(pair.node)) {
				if (dist[pair.node] + p.distance < dist[p.node]) {
					dist[p.node] = dist[pair.node] + p.distance;

					pq.add(new Pair(p.node, dist[p.node]));

					parent[p.node] = pair.node;
				}
			}
		}

		List<Integer> ans = new ArrayList<>();

		if (dist[n] == 100000000) {
			ans.add(-1);
			return ans;
		}

		int node = n;
		do {
			ans.add(node);
			node = parent[node];
		} while (parent[node] != -1);
		ans.add(1);
		Collections.reverse(ans);
		return ans;
	}

	private class Pair {
		int node;
		int distance;

		Pair(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "[" + node + ", " + distance + "]";
		}

	}
}
