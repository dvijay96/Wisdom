package com.dsalgo.graphs;

import java.util.Arrays;

import com.dsalgo.utility.ArrayUtils;

public class BellmanFordAlgo {

	public static void main(String[] args) {

		BellmanFordAlgo obj = new BellmanFordAlgo();

		// Negative cycle
//		int[][] edges = { { 0, 1, 2 }, { 0, 2, -2 }, { 0, 3, -1 }, { 0, 4, -6 }, { 0, 6, 2 }, { 1, 0, -2 }, { 1, 2, 4 },
//				{ 1, 4, -7 }, { 1, 6, 10 }, { 2, 0, -8 }, { 2, 1, 10 }, { 2, 6, -8 }, { 3, 1, -1 }, { 3, 4, 4 },
//				{ 4, 1, -11 }, { 4, 2, -10 }, { 4, 3, -4 }, { 5, 0, 1 }, { 5, 2, 8 }, { 5, 3, -3 }, { 5, 4, 3 },
//				{ 5, 6, 6 }, { 6, 0, 9 }, { 6, 1, 1 }, { 6, 3, -11 }, };

		// No negative cycle
		int[][] edges = { { 3, 2, 6 }, { 5, 3, 1 }, { 0, 1, 5 }, { 1, 5, -3 }, { 1, 2, -2 }, { 3, 4, -2 },
				{ 2, 4, 3 } };

		int source = 0;
		int vertices = 6;

		int[] dist = obj.bellmanFord(vertices, edges, source);

		ArrayUtils.print(dist);
	}

//	Approach:- 
//			-> We need to relax the edges for atleast V-1 times.
//			-> relaxing i.e let u -> v exists with weight wt and
//					if( dist[u] + wt < dist[v]) {
//						dist[v] = dist[u] + wt;    				this is relaxing edges
//					}
//			-> After V-1 iterations of relaxations, in the Vth iteration,it is found that further relaxation of any edge 
//				is possible, we can conclude that the graph has a negative cycle.
//	
//	TC:- O(V*E)
//	SC:- O(V)
	int[] bellmanFord(int vertices, int[][] edges, int source) {

		int[] dist = new int[vertices];
		Arrays.fill(dist, 100000000);
		dist[source] = 0;

		for (int i = 0; i < vertices - 1; i++) {
			for (int[] edge : edges) {
				int u = edge[0];
				int v = edge[1];
				int wt = edge[2];

				if (dist[u] != 100000000 && dist[u] + wt < dist[v]) {
					dist[v] = dist[u] + wt;
				}
			}
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];

			if (dist[u] != 100000000 && dist[u] + wt < dist[v]) {
				return new int[] { -1 };
			}
		}

		return dist;
	}
}
