package com.problems.graphs;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.GraphUtils;

public class EventualSafeNodes {

//	There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 
//	2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge 
//	from node i to each node in graph[i].
//
//	A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting 
//	from that node leads to a terminal node (or another safe node).
//
//	Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
//
//	 
//
//	Example 1:
//
//	Illustration of graph
//	Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//	Output: [2,4,5,6]
//	Explanation: The given graph is shown above.
//	Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
//	Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
//	
//	Example 2:
//
//	Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//	Output: [4]
//	Explanation:
//	Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
//	
	public static void main(String[] args) {

		EventualSafeNodes obj = new EventualSafeNodes();

		List<List<Integer>> adj = GraphUtils.adjacencyListDirected(7);

		System.out.println("Graph:- ");
		adj.forEach(System.out::println);
		System.out.println();

		System.out.println(obj.eventualSafeNodes(adj));
	}

//	Approach:- 
//			-> DFS over the graph.
//			-> Maintain a visited path array.
//			-> for any node if a cycle is detected which means that node and the nodes for that path can't be safe.
//			-> If a path leads to a terminal node without a cycle, mark the node as safe in some safeNode array.
//			-> After DFS, collect all the safe nodes.
//	
//	TC:- O( V + E )
//	SC:- O( V )
	public List<Integer> eventualSafeNodes(List<List<Integer>> graph) {
		int v = graph.size();
		boolean[] vis = new boolean[v];
		boolean[] path = new boolean[v];
		boolean[] safeNodes = new boolean[v];

		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < v; i++) {
			if (!vis[i]) {
				isCycle(i, graph, vis, path, safeNodes);
			}
		}

		for (int i = 0; i < safeNodes.length; i++) {
			if (safeNodes[i]) {
				ans.add(i);
			}
		}

		return ans;
	}

	boolean isCycle(int src, List<List<Integer>> graph, boolean[] vis, boolean[] path, boolean[] safeNodes) {
		vis[src] = true;
		path[src] = true;

		for (int node : graph.get(src)) {
			if (!vis[node]) {
				if (isCycle(node, graph, vis, path, safeNodes)) {
					return true;
				}
			} else if (path[node]) {
				return true;
			}
		}

		path[src] = false;
		safeNodes[src] = true;
		return false;
	}
}
