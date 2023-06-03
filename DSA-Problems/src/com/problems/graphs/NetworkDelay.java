package com.problems.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelay {

//	You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as 
//	directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it 
//	takes for a signal to travel from source to target.
//
//	We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
//	If it is impossible for all the n nodes to receive the signal, return -1.
//
//			 
//
//	Example 1:
//
//	Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//	Output: 2
//			
//	Example 2:
//
//	Input: times = [[1,2,1]], n = 2, k = 1
//	Output: 1
//			
//	Example 3:
//
//	Input: times = [[1,2,1]], n = 2, k = 2
//	Output: -1
//			 
//
//	Constraints:
//
//	* 1 <= k <= n <= 100
//	* 1 <= times.length <= 6000
//	* times[i].length == 3
//	* 1 <= ui, vi <= n
//	* ui != vi
//	* 0 <= wi <= 100
//	* All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

	public static void main(String[] args) {

		NetworkDelay obj = new NetworkDelay();
		int[][] times = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
		int n = 4;
		int k = 2;

		System.out.println(obj.networkDelayTime(times, n, k));
	}

//	Approach:-
//			-> Need to find in how much time a signal send from a source node would take to reach all other nodes in the 
//				graph. If possible then return the time, if not return -1.
//			-> Performing a BFS on the graph from the source to all other nodes while applying the time taken.
//			-> Since it needs to be minimum, we could use Dijkstra's algo, i.e BFS using PQ for the time weightage.
//	
//	TC:- O( V + ELogV)
//	SC:- O( V + E)
	public int networkDelayTime(int[][] times, int n, int k) {

		List<List<Pair>> adj = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] arr : times) {
			Pair p = new Pair(arr[1], arr[2]);
			adj.get(arr[0]).add(p);
		}

		int[] time = new int[n + 1];

		Arrays.fill(time, 1000);

		PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> a.time - b.time);

		time[k] = 0;

		queue.add(new Pair(k, 0));

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();

			for (Pair p : adj.get(pair.node)) {
				if (time[pair.node] + p.time < time[p.node]) {
					time[p.node] = time[pair.node] + p.time;
					queue.add(new Pair(p.node, time[p.node]));
				}
			}
		}

		int ans = Integer.MIN_VALUE;

		for (int i = 1; i < time.length; i++) {
			if (i != k) {
				if (time[i] == 1000)
					return -1;
				else
					ans = Math.max(ans, time[i]);
			}
		}

		return ans;
	}

	private class Pair {
		int node;
		int time;

		Pair(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
}
