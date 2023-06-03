package com.problems.graphs;

public class CityWithSmallestNumberNeighborsAtThresholdDistance {

	public static void main(String[] args) {
		CityWithSmallestNumberNeighborsAtThresholdDistance obj = new CityWithSmallestNumberNeighborsAtThresholdDistance();
		int n = 5;
		int[][] edges = { { 0, 1, 2 }, { 0, 4, 8 }, { 1, 2, 3 }, { 1, 4, 2 }, { 2, 3, 1 }, { 3, 4, 1 } };
		int distanceThreshold = 2;

		System.out.println(obj.findTheCity(n, edges, distanceThreshold));
	}

//	Approach:- 
//			-> Generate the cost matrix to perform floyd warhall algo for multisource shortest path.
//			-> Perform the floyd warshall algo on the above cost matrix.
//			-> Calculate for teach city 0 to n-1, how many are reachable within threashold distance.

//	TC:- O(V^3)
//	SC:- O(V^2)
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {

		int[][] cost = new int[n][n];

		buildCostMatrix(n, edges, cost);

		floydWarshall(n, cost);

		int max = n + 1;
		int city = -1;

		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (cost[i][j] <= distanceThreshold) {
					count++;
				}
			}

			if (count <= max) {
				max = count;
				city = i;
			}
		}

		return city;
	}

	private void floydWarshall(int n, int[][] cost) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j || cost[i][k] == 100000000 || cost[k][j] == 100000000)
						continue;
					else
						cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}
	}

	private void buildCostMatrix(int n, int[][] edges, int[][] cost) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					cost[i][j] = 0;
				} else {
					cost[i][j] = 100000000;
				}
			}
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];

			cost[u][v] = wt;
			cost[v][u] = wt;
		}
	}
}
