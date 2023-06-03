package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import com.dsalgo.utility.ArrayUtils;

public class DijkstraAlgorithm {

	public static void main(String[] args) {
		DijkstraAlgorithm obj = new DijkstraAlgorithm();

		List<List<List<Integer>>> adj = new ArrayList<>();

		int v = 3;

		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}

		adj.get(0).add(Arrays.asList(1, 1)); // for each node, adding the pair of node and distance to that node.
		adj.get(0).add(Arrays.asList(2, 6));

		adj.get(1).add(Arrays.asList(2, 3));
		adj.get(1).add(Arrays.asList(0, 1));

		adj.get(2).add(Arrays.asList(1, 3));
		adj.get(2).add(Arrays.asList(0, 6));

		int[] dist = obj.dijkstra(v, adj, 0);

		ArrayUtils.print(dist);
	}

//	Used to find out the smallest path distance from a source node to all or any destination node.
//	Below method returns shortest distance from given source node to all other nodes in the graph.
//	
//	-> Its similar to BFS traversal of the graph, where we use a Priority Queue instead of a normal Queue.
//	-> We can use normal Queue as well, but finding the shortest path with a normal Queue is brute force.
//	-> While with a Priority Queue, we always explore the min distance and hence a better approach.
//	
//	TC:- O( ElogV )
//	SC:- O( V + E )
	public int[] dijkstra(int v, List<List<List<Integer>>> adj, int s) {

		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);

		int[] dist = new int[v];

		for (int i = 0; i < v; i++) {
			dist[i] = 1000000;
		}

		dist[s] = 0;

		pq.add(new Pair(s, 0));

		while (!pq.isEmpty()) {
			Pair pair = pq.poll();
			int node = pair.node;
			int distance = pair.distance;

			for (List<Integer> nodes : adj.get(node)) {
				int wt = nodes.get(1);
				int neigh = nodes.get(0);

				if (distance + wt < dist[neigh]) {
					dist[neigh] = distance + wt;
					pq.add(new Pair(neigh, dist[neigh]));
				}
			}
		}

		return dist;
	}

	class Pair {
		private int node;
		private int distance;

		Pair(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}

	}
}
