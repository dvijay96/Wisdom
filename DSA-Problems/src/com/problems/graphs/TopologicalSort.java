package com.problems.graphs;

import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.GraphUtils;

public class TopologicalSort {

//	Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.
//
//	Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed 
//	edge u v, vertex u comes before v in the ordering.
//
//	Note: Topological Sorting for a graph is not possible if the graph is not a DAG.
//	
//	Example 1:
//		
//		0 -> []	 						2 <---- 5 ---> 0 <---- 4
//		1 -> []	 						|					   |
//		2 -> [3]						|----> 3 ----> 1 <-----|
//		3 -> [1]						
//		4 -> [0,1]
//		5 -> [0,2]
//	
//	A topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one topological sorting 
//	for a graph. Another topological sorting of the following graph is “4 5 2 3 1 0”. The first vertex in topological 
//	sorting is always a vertex with an in-degree of 0 (a vertex with no incoming edges).

	public static void main(String[] args) {

		List<List<Integer>> graph = GraphUtils.adjacencyListDirected(7);
		System.out.println("Graph:- ");
		graph.forEach(System.out::println);

		DetectCycleInDirectedGraph detect = new DetectCycleInDirectedGraph();

		if (!detect.isCycle(graph)) {
			ArrayUtils.print(topoSort(7, graph));
		} else {
			System.out.println("Can't perform topological sort on Cyclic graph.");
		}
	}

	static int[] topoSort(int v, List<List<Integer>> adj) {
		boolean[] vis = new boolean[v];

		LinkedList<Integer> stack = new LinkedList<>();

		for (int i = 0; i < v; i++) {
			if (!vis[i]) {
				dfs(i, vis, adj, stack);
			}
		}

		int[] ans = new int[stack.size()];
		int i = 0;
		while (!stack.isEmpty()) {
			ans[i++] = stack.pop();
		}

		return ans;
	}

	static void dfs(int src, boolean[] vis, List<List<Integer>> adj, LinkedList<Integer> stack) {
		vis[src] = true;

		for (int node : adj.get(src)) {
			if (!vis[node]) {
				dfs(node, vis, adj, stack);
			}
		}

		stack.push(src);
	}
}
