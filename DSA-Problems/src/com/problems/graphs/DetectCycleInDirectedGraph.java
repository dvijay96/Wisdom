package com.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.GraphUtils;

public class DetectCycleInDirectedGraph {

	public static void main(String[] args) {
		DetectCycleInDirectedGraph obj = new DetectCycleInDirectedGraph();
		List<List<Integer>> adjList = GraphUtils.adjacencyListDirected(7);

		System.out.println("Graph:- ");
		adjList.forEach(System.out::println);
		System.out.println();

		System.out.println("Cycle: " + obj.isCycle(adjList));
	}

//	Approach:-
//			-> We DFS over the graph.
//			-> We maintain the path that we DFS on to.
//			-> At any node if that node has been visited and also has been traversed in the same path, we detect a cycle.
//	
//	TC:- O( V + E )
//	SC:- O(V)
	boolean isCycle(List<List<Integer>> graph) {
		int n = graph.size();
		boolean[] vis = new boolean[n];
		boolean[] pathVis = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!vis[i]) {
				if (isCycle(i, graph, vis, pathVis)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isCycle(int src, List<List<Integer>> graph, boolean[] vis, boolean[] pathVis) {
		vis[src] = true;
		pathVis[src] = true;

		for (int node : graph.get(src)) {
			if (!vis[node]) {
				if (isCycle(node, graph, vis, pathVis))
					return true;
			} else if (pathVis[node]) {
				return true;
			}
		}
		pathVis[src] = false;
		return false;
	}

//	Approach:- 
//			-> Using Kahn's topological sorting
//			-> if we are able to find all the nodes through topological sort then there is no cycle else there is.
//	
//	TC:- O(V+E)
//	SC:- O(N)
	public boolean isCyclic(int v, List<List<Integer>> adj) {

		int[] indegree = new int[v];

		for (List<Integer> list : adj) {
			for (int i : list) {
				indegree[i]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < v; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		int count = 0;

		while (!queue.isEmpty()) {
			int node = queue.poll();
			count++;
			for (int nodes : adj.get(node)) {
				indegree[nodes]--;
				if (indegree[nodes] == 0) {
					queue.add(nodes);
				}
			}
		}

		return count != v;
	}
}
