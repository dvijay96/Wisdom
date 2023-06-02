package com.problems.graphs;

import java.util.HashMap;
import java.util.Map;

public class BipartiteGraph {

//	There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] 
//			is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u 
//			and node v. The graph has the following properties:
//
//		* There are no self-edges (graph[u] does not contain u).
//		* There are no parallel edges (graph[u] does not contain duplicate values).
//		* If v is in graph[u], then u is in graph[v] (the graph is undirected).
//		* The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
//		
//		A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node 
//		in set A and a node in set B.
//
//		Return true if and only if it is bipartite.
//
//		Example 1:
//
//		Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
//		Output: false
//		Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
//		
//		Example 2:
//
//		Input: graph = [[1,3],[0,2],[1,3],[0,2]]
//		Output: true
//		Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.

	public static void main(String[] args) {

		BipartiteGraph obj = new BipartiteGraph();
		int[][] graph = { { 3, 4, 6 }, { 3, 6 }, { 3, 6 }, { 0, 1, 2, 5 }, { 0, 7, 8 }, { 3 }, { 0, 1, 2, 7 }, { 4, 6 },
				{ 4 }, {} };

		System.out.println(obj.isBipartite(graph));
		System.out.println(obj.isBipartiteII(graph));
	}

//	Approach:- 
//			-> A graph can be called a bipartite graph if we can color the whole graph with just 2 colors and each adjacent node will have diff color.
//			-> We can perform a DFS/BFS on all connected nodes and keep coloring them alternatively and at any point we get two adjacent nodes 
//				visited and having same color, we return false.
//	
//	TC:- O(V + 2E)
//	SC:- O(V)
	public boolean isBipartite(int[][] graph) {
		Map<Integer, Character> vis = new HashMap<>();

		for (int i = 0; i < graph.length; i++) {
			if (!vis.containsKey(i)) {
				if (dfs(i, graph, vis, null)) {
					return false;
				}
			}
		}

		return true;
	}

	boolean dfs(int src, int[][] graph, Map<Integer, Character> vis, Character prev) {
		if (prev == null)
			vis.put(src, 'R');
		else {
			if (prev == 'R')
				vis.put(src, 'B');
			else
				vis.put(src, 'R');
		}

		for (int nodes : graph[src]) {
			if (!vis.containsKey(nodes)) {
				if (dfs(nodes, graph, vis, vis.get(src))) {
					return true;
				}
			} else if (vis.get(nodes).equals(vis.get(src))) {
				return true;
			}
		}
		return false;
	}

	/*********************************************************************/

	/* TO BE Checked !!!! */
	@Deprecated
	public boolean isBipartiteII(int[][] graph) {
		int n = graph.length;

		boolean[] vis = new boolean[n];
		char[] mark = new char[n];

		for (int i = 0; i < n; i++) {
			if (!vis[i] && !dfs(i, graph, vis, 'R', mark)) {
				return false;
			}
		}

		return true;
	}

	@Deprecated
	boolean dfs(int node, int[][] graph, boolean[] vis, char color, char[] mark) {
		vis[node] = true;
		mark[node] = color;

		char nextColor = color == 'R' ? 'G' : 'R';

		for (int adj : graph[node]) {
			if (!vis[adj]) {
				return dfs(adj, graph, vis, nextColor, mark);
			} else if (mark[adj] != nextColor) {
				return false;
			}
		}

		return true;
	}
}
