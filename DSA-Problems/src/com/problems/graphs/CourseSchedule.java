package com.problems.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

//	There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where 
//	prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
//
//			For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//			Return true if you can finish all courses. Otherwise, return false.
//
//			 
//
//			Example 1:
//
//			Input: numCourses = 2, prerequisites = [[1,0]]
//			Output: true
//			Explanation: There are a total of 2 courses to take. 
//			To take course 1 you should have finished course 0. So it is possible.
//			
//			Example 2:
//
//			Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//			Output: false
//			Explanation: There are a total of 2 courses to take. 
//			To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
//			
	public static void main(String[] args) {
		CourseSchedule obj = new CourseSchedule();

		int[][] arr = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };

		System.out.println(obj.canFinish(4, arr));
	}

//	Approach;-
//			-> Consider each [ai, bi] as edge definition between ai and bi nodes.
//			-> We need to find out if a topological sort of the given edges graph is possible or not.
//			
//	TC:- O(V+E)
//	SC:- O(2N)
	public boolean canFinish(int numCourses, int[][] prerequisites) {

		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < numCourses; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] ar : prerequisites) {
			adj.get(ar[0]).add(ar[1]);
		}

		return !isCyclic(adj);
	}

	boolean isCyclic(List<List<Integer>> adj) {
		int[] indegree = new int[adj.size()];

		for (int i = 0; i < adj.size(); i++) {
			for (int j : adj.get(i)) {
				indegree[j]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < indegree.length; i++) {
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

		return count != adj.size();
	}
}
