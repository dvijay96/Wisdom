package com.problems.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AllPathsFromSourceToTarget {

//	Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in 
//	any order.
//
//	The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node 
//	graph[i][j]).
//
//			 
//
//			Example 1:
//
//
//			Input: graph = [[1,2],[3],[3],[]]
//			Output: [[0,1,3],[0,2,3]]
//			Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
//			
//			Example 2:
//
//
//			Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//			Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//					
	public static void main(String[] args) {
		AllPathsFromSourceToTarget obj = new AllPathsFromSourceToTarget();
		int[][] graph = { { 4, 3, 1 }, { 3, 2, 4 }, { 3 }, { 4 }, {} };
		System.out.println(obj.allPathsSourceTarget(graph));
		System.out.println(obj.allPathsSourceTarget2(graph));
	}

//	Approach:-
//			-> Start a DFS from 0 to all adjacent nodes.
//			-> Keep track of visited nodes in that particular dfs path.
//			-> when reached n-1 th node, add the path in paths list.
//			-> while backtracking to next path, remove the last added node.
//	
//	TC:- O(n*2^n)	if in worst case each node has connection with each other in the graph
//	SC:- O(n)		for storing each possible path
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

		List<List<Integer>> paths = new ArrayList<>();

		dfs(0, graph, paths, new ArrayList<>());

		return paths;
	}

	private void dfs(int node, int[][] graph, List<List<Integer>> paths, List<Integer> path) {
		path.add(node);
		if (node == graph.length - 1) {
			paths.add(new ArrayList<>(path));
		} else {
			int[] adj = graph[node];

			for (int nodes : adj) {
				dfs(nodes, graph, paths, path);
			}
		}
		path.remove(path.size() - 1);
	}

	/****************************************************************************************************************************
	 * ***************************************************************************************************************************/

	public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
		Map<Integer, List<List<Integer>>> map = new HashMap<>();

		return dfsSearch(graph, 0, map);
	}

	private List<List<Integer>> dfsSearch(int[][] graph, int node, Map<Integer, List<List<Integer>>> map) {
		if (map.containsKey(node)) {
			return map.get(node);
		}

		List<List<Integer>> res = new ArrayList<>();
		if (node == graph.length - 1) {
			List<Integer> path = new LinkedList<>();
			path.add(node);
			res.add(path);
		} else {
			for (int nextNode : graph[node]) {
				List<List<Integer>> subPaths = dfsSearch(graph, nextNode, map);
				for (List<Integer> path : subPaths) {
					LinkedList<Integer> newPath = new LinkedList<>(path);
					newPath.addFirst(node);
					res.add(newPath);
				}
			}
		}

		map.put(node, res);
		return res;
	}
}
