package com.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

	public static void main(String[] args) {
		CheapestFlightsWithinKStops obj = new CheapestFlightsWithinKStops();
		int n = 4;
		int[][] flights = { { 0, 1, 1 }, { 0, 2, 5 }, { 1, 2, 1 }, { 2, 3, 1 } };
		int src = 0;
		int dst = 3;
		int k = 1;

		System.out.println(obj.findCheapestPrice(n, flights, src, dst, k));
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		List<List<Pair>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		int m = flights.length;
		for (int i = 0; i < m; i++) {
			adj.get(flights[i][0]).add(new Pair(flights[i][1], flights[i][2]));
		}

		Queue<Tuple> q = new LinkedList<>();

		q.add(new Tuple(0, src, 0));

		int[] dist = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = (int) (1e9);
		}
		dist[src] = 0;

		while (!q.isEmpty()) {
			Tuple it = q.peek();
			q.remove();
			int stops = it.stops;
			int node = it.node;
			int cost = it.cost;

			if (stops > k)
				continue;
			for (Pair iter : adj.get(node)) {
				int adjNode = iter.node;
				int edW = iter.cost;

				if (cost + edW < dist[adjNode] && stops <= k) {
					dist[adjNode] = cost + edW;
					q.add(new Tuple(stops + 1, adjNode, cost + edW));
				}
			}
		}
		if (dist[dst] == (int) (1e9))
			return -1;
		return dist[dst];
	}

	private class Pair {
		int node;
		int cost;

		public Pair(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "[" + node + ", " + cost + "]";
		}

	}

	private class Tuple {
		int stops;
		int node;
		int cost;

		Tuple(int stops, int node, int cost) {
			this.stops = stops;
			this.node = node;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "[" + stops + ", " + node + ", " + cost + "]";
		}

	}
}
