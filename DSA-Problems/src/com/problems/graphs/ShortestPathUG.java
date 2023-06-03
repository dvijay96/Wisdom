package com.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;

public class ShortestPathUG {

//	You are given an Undirected Graph having unit weight, Find the shortest path from src to all the vertex and if it is unreachable to reach 
//	any vertex, then return -1 for that vertex.
//
//			Example:
//
//			Input:
//			n = 9, m= 10
//			edges=[[0,1],[0,3],[3,4],[4 ,5]
//			,[5, 6],[1,2],[2,6],[6,7],[7,8],[6,8]] 
//			src=0
//			Output:
//			0 1 2 1 2 3 3 4 4

	public static void main(String[] args) {

		ShortestPathUG obj = new ShortestPathUG();

		int vertices = 8;
		int edges = 10;
		int[][] edgesArr = new int[edges][2];

		for (int[] edge : edgesArr) {
			ArrayUtils.fillRandomRange(edge, 0, vertices - 1);
		}

		ArrayUtils.print(edgesArr);

		int[] ans = obj.shortestPath(edgesArr, vertices, 0);

		ArrayUtils.print(ans);
	}

//	Approach:- 
//			-> Since the weight of each edge is unit value i.e 1, we can perform BFS/DFS and calculate the distance 
//				as a level wise traversing.
//	
//	TC:- O(V + E)
//	SC:- O(V)
	public int[] shortestPath(int[][] edges, int vertices, int src) {

		List<List<Integer>> adj = new ArrayList<>();
		int[] dist = new int[vertices];

		for (int i = 0; i < vertices; i++) {
			adj.add(new ArrayList<>());
			dist[i] = 1000;
		}

		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		dist[src] = 0;

		Queue<Integer> queue = new LinkedList<>();

		queue.add(src);

		while (!queue.isEmpty()) {
			int node = queue.poll();

			for (int nodes : adj.get(node)) {
				if (dist[node] + 1 < dist[nodes]) {
					dist[nodes] = dist[node] + 1;
					queue.add(nodes);
				}
			}
		}

		for (int i = 0; i < vertices; i++) {
			if (dist[i] >= 1000) {
				dist[i] = -1;
			}
		}

		return dist;
	}
}
