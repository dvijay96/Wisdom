package com.problems.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.GraphUtils;

public class KahnTopologicalAlgo {

	public static void main(String[] args) {
		DetectCycleInDirectedGraph obj = new DetectCycleInDirectedGraph();
		List<List<Integer>> graph = GraphUtils.adjacencyListDirected(7);

		System.out.println("Graph:- ");
		graph.forEach(System.out::println);

		if (!obj.isCycle(graph)) {
			ArrayUtils.print(kahnsTopoSort(7, graph));
		} else {
			System.out.println("Can't perform topo sort on cylic graph.");
		}
	}

//	Approach:-
//			-> Store indegree of each node in an indegree array.
//			-> Define queue and push the node with indegree zero first.
//			-> Pop the queue, decrement the indegree count of the poped out node's neighbours.
//				If any neighbour indegree reduces to zero, push him into the queue.
//			-> Continue the process untill the queue is not empty.
//			-> Store ech poped out node in a list/array, which is out topo sort.
//	
//	TC:- O(V + E)
//	SC:- O(2N)
	static int[] kahnsTopoSort(int v, List<List<Integer>> adj) {

		int[] indegree = new int[v];

		for (List<Integer> list : adj) {
			for (int i : list) {
				indegree[i]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		int[] ans = new int[v];
		int i = 0;

		while (!queue.isEmpty()) {
			int node = queue.poll();

			for (int nodes : adj.get(node)) {
				indegree[nodes]--;
				if (indegree[nodes] == 0) {
					queue.add(nodes);
				}
			}
			ans[i++] = node;
		}

		return ans;
	}
}
