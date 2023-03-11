package com.problems.graphs;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;

public class Provinces {

//	There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, 
//	and city b is connected directly with city c, then city a is connected indirectly with city c.
//
//	A province is a group of directly or indirectly connected cities and no other cities outside of the group.
//
//	You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly 
//	connected, and isConnected[i][j] = 0 otherwise.
//
//	Return the total number of provinces.
//
//	 
//
//	Example 1:
//
//
//	Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//	Output: 2
//	
//	Example 2:
//
//	Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//	Output: 3
//	 
//
//	Constraints:
//
//	1 <= n <= 200
//	n == isConnected.length
//	n == isConnected[i].length
//	isConnected[i][j] is 1 or 0.
//	isConnected[i][i] == 1
//	isConnected[i][j] == isConnected[j][i]

	public static void main(String[] args) {
		int[][] adj = new int[5][5];

		for (int i = 0; i < adj.length; i++) {
			adj[i] = ArrayUtils.getIntArray(5, 0, 1);
		}

		for (int[] ar : adj) {
			ArrayUtils.print(ar);
			System.out.println();
		}

		System.out.println(provincesUsingDFS(adj));
		System.out.println(provincesUsingBFS(adj));
	}

//	Approach:-
//			-> Provinces means nothing but the no. of components of a graph.
//			-> For each node we traverse once and check for the no. of disconnections in the whole map.
//	
//	TC:- O(N) + O(V + 2*E)   N for the outer loop and dsf takes V + 2*E i.e nodes + degree of graph
//	SC:- O(N) + O(N)		1 for visited array and other for stack space
	public static int provincesUsingDFS(int[][] isConnected) {
		int count = 0;
		boolean[] visited = new boolean[isConnected.length + 1];

		for (int i = 1; i <= isConnected.length; i++) {
			if (!visited[i]) {
				dfs(i, visited, isConnected);
				count++;
			}
		}

		return count;
	}

	static void dfs(int node, boolean[] visited, int[][] adj) {
		visited[node] = true;

		for (int i = 0; i < adj[node - 1].length; i++) {
			int m = adj[node - 1][i];
			if (!visited[i + 1] && m == 1) {
				dfs(i + 1, visited, adj);
			}
		}
	}

	public static int provincesUsingBFS(int[][] isConnected) {
		int count = 0;
		boolean[] visited = new boolean[isConnected.length + 1];

		Queue<Integer> queue;
		for (int i = 1; i <= isConnected.length; i++) {
			if (!visited[i]) {
				count++;
				queue = new LinkedList<>();
				queue.add(i);
				visited[i] = true;

				while (!queue.isEmpty()) {
					Integer n = queue.poll();

					for (int j = 0; j < isConnected[n - 1].length; j++) {
						int m = isConnected[n - 1][j];
						if (!visited[j + 1] && m == 1) {
							queue.add(j + 1);
							visited[j + 1] = true;
						}
					}
				}
			}
		}

		return count;
	}
}
