package com.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;

public class ShortestPathDAG {

//	Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer 
//	array(or vector) edges[ ][ ] of length M, where there is a directed edge from 
//	edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i, 0<=i
//
//	Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, 
//	then return -1 for that vertex.
//
//			 
//
//			Example:
//
//			Input:
//			n = 6, m= 7
//			edge=[[0,1,2],[0,4,1],[4,5,4]
//			,[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
//
//			Output:
//			0 2 3 6 1 5
	public static void main(String[] args) {
		ShortestPathDAG obj = new ShortestPathDAG();
		int[][] edge = { { 0, 1, 2 }, { 0, 4, 1 }, { 4, 5, 4 }, { 4, 2, 2 }, { 1, 2, 3 }, { 2, 3, 6 }, { 5, 3, 1 } };

		int[] ans = obj.shortestPath(6, 7, edge);

		ArrayUtils.print(ans);
	}

//	Approach:- 
//			-> Perform topo sort on the graph.
//			-> Take out top node from the topo sort stack and relax its neigbours, i.e. find the distance from the top node to its neighbours
//				which will be 
//				distance[neighbour] = distance[top] + weight of the edge.
//			-> Why topo sort? Since it is a DAG, doing topo sort ensures we have a node to visit after the current node in the topo sort order.
//	
//	TC:- O(V+E)
//	SC:- O(V)
	public int[] shortestPath(int n, int m, int[][] edges) {
		List<List<Pair>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			int sv = edges[i][0];
			int dv = edges[i][1];
			int wt = edges[i][2];
			adj.get(sv).add(new Pair(dv, wt));
		}

		boolean[] vis = new boolean[n];
		LinkedList<Integer> stack = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			if (!vis[i]) {
				topoSort(i, adj, vis, stack);
			}
		}

		int[] dist = new int[n];

		for (int i = 0; i < n; i++) {
			dist[i] = 1000;
		}
		dist[0] = 0;
		while (!stack.isEmpty()) {
			int top = stack.pop();

			for (int i = 0; i < adj.get(top).size(); i++) {
				int nv = adj.get(top).get(i).node;
				int wt = adj.get(top).get(i).weight;

				dist[nv] = Math.min(dist[top] + wt, dist[nv]);
			}
		}

		for (int i = 0; i < n; i++) {
			if (dist[i] >= 1000) {
				dist[i] = -1;
			}
		}

		return dist;
	}

	void topoSort(int src, List<List<Pair>> adj, boolean[] vis, LinkedList<Integer> stack) {
		vis[src] = true;

		for (int i = 0; i < adj.get(src).size(); i++) {
			int nv = adj.get(src).get(i).node;
			if (!vis[nv]) {
				topoSort(nv, adj, vis, stack);
			}
		}

		stack.push(src);
	}
}

class Pair {
	int node;
	int weight;

	protected Pair(int dest, int weight) {
		super();
		this.node = dest;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "{ " + node + ", " + weight + " }";
	}

}
