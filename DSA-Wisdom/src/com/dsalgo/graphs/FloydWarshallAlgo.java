package com.dsalgo.graphs;

import com.dsalgo.utility.ArrayUtils;

public class FloydWarshallAlgo {

//	Floyd Warshall algorithm is a multi-source shortest path algorithm and it helps to detect negative cycles as well. 
//	The shortest path between node u and v necessarily means the path(from u to v) for which the sum of the edge 
//	weights is minimum.
//
//	In Floyd Warshall’s algorithm, we need to check every possible path going via each possible node. And after 
//	checking every possible path, we will figure out the shortest path(a kind of brute force approach to find the 
//	shortest path). 

//	if u is source and v is destination, we have to check from every possible vertex edge i.e
//	
//	u to v via u1, or via u2, or via u3 and so on all nodes.
//	and by visiting through all nodes, we can calculate min cost.
//	i.e 
//	
//	dist[u][v] = Min(dist[u][v] , dist[u][k]+ dist[k][v]);   // k is via node, in between node
//	
//	We make use of adjacency matrix instead of adjacency list in this.

//	TC:- O(V^3)
//	SC:- O(V^2)
	public static void main(String[] args) {
		FloydWarshallAlgo obj = new FloydWarshallAlgo();
		int[][] matrix = { { 0, 3, -1, -1 }, { 3, 0, 1, 4 }, { -1, 1, 0, 1 }, { -1, 4, 1, 0 } };
		obj.shortestDistance(matrix);

		ArrayUtils.print(matrix);
	}

	public void shortestDistance(int[][] matrix) {

		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					matrix[i][j] = 0;
				} else if (matrix[i][j] == -1) {
					matrix[i][j] = 100000000;
				}
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// if(i==j){
				// matrix[i][j] = 0;
				// }else
				if (matrix[i][j] == 100000000) {
					matrix[i][j] = -1;
				}
			}
		}
	}
}
