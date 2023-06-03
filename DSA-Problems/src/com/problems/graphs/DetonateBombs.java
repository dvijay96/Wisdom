package com.problems.graphs;

import java.util.ArrayList;
import java.util.List;

public class DetonateBombs {

//	You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. 
//	This area is in the shape of a circle with the center as the location of the bomb.
//
//	The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote 
//	the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
//
//	You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its 
//	range. These bombs will further detonate the bombs that lie in their ranges.
//
//	Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate
//	only one bomb.
//
//	Example 1:
//
//	Input: bombs = [[2,1,3],[6,1,4]]
//	Output: 2
//	Explanation:
//	The above figure shows the positions and ranges of the 2 bombs.
//	If we detonate the left bomb, the right bomb will not be affected.
//	But if we detonate the right bomb, both bombs will be detonated.
//	So the maximum bombs that can be detonated is max(1, 2) = 2.
//	
//	Example 2:
//
//	Input: bombs = [[1,1,5],[10,10,5]]
//	Output: 1
//	Explanation:
//	Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
//	
//	Example 3:
//
//	Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
//	Output: 5
//	Explanation:
//	The best bomb to detonate is bomb 0 because:
//	- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
//	- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
//	- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
//	Thus all 5 bombs are detonated.

	public static void main(String[] args) {
		DetonateBombs obj = new DetonateBombs();

//		int[][] bombs = { { 38496, 37528, 4845 }, { 46272, 98187, 1365 }, { 70550, 7578, 3223 }, { 77200, 18005, 7272 },
//				{ 7648, 58155, 7628 }, { 95708, 33470, 1889 }, { 20157, 92266, 9823 }, { 52803, 2765, 6751 },
//				{ 50429, 63049, 3002 }, { 72582, 69729, 2281 }, { 49317, 35327, 1922 }, { 715, 8902, 9620 },
//				{ 21154, 58349, 8544 }, { 43935, 46296, 6868 }, { 7881, 24144, 2372 }, { 95258, 97730, 6554 },
//				{ 5525, 56971, 9191 }, { 95762, 81415, 2027 }, { 62518, 75469, 1330 }, { 83660, 4341, 6817 },
//				{ 30268, 38781, 8309 }, { 97922, 20474, 4047 }, { 39466, 40057, 2061 }, { 91983, 24242, 5451 },
//				{ 92380, 31509, 8446 }, { 12436, 8897, 5279 }, { 28386, 8556, 4702 }, { 54672, 88180, 1106 },
//				{ 17843, 95337, 4420 }, { 21956, 49924, 1839 } };

		int[][] bombs = { { 1, 2, 3 }, { 2, 3, 1 }, { 3, 4, 2 }, { 4, 5, 3 }, { 5, 6, 4 } };

		System.out.println(obj.maximumDetonation(bombs));
	}

//	Approach:- 
//			-> We need to consider each bomb as a node from 0 to n-1 bombs.
//			-> Need to check for each bomb, if it can detonate other bombs.
//			-> If a bomb(A) can detonate another bomb(B), we can draw a directed edge between A and B.
//				Stating A can detonate B.
//			-> Thus this could be represented as a graph problem.
//			-> Need to generate the adj list from step 3.
//			-> After that perform dfs from each node and check from which node can we travel max nodes.
//	
//	TC:- O(n^3)
//	SC:- O(n^2)
	public int maximumDetonation(int[][] bombs) {
		if (bombs == null || bombs.length == 0) {
			return 0;
		}
		int n = bombs.length;
		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < n; i++) {
			int radius = bombs[i][2];
			int x1 = bombs[i][0];
			int y1 = bombs[i][1];
			for (int j = 0; j < n; j++) {
				int x2 = bombs[j][0];
				int y2 = bombs[j][1];
				if (i != j && inRange(x1, x2, y1, y2, radius)) {
					adj.get(i).add(j);
				}
			}
		}

		int ans = -1;

		for (int i = 0; i < n; i++) {
			boolean[] vis = new boolean[n];
			int[] size = new int[1];
			dfs(i, adj, vis, size);
			if (size[0] == n) {
				return n;
			}
			ans = Math.max(ans, size[0]);
		}
		return ans;
	}

	private boolean inRange(int x1, int x2, int y1, int y2, int dist) {
		long x = 1l * (x2 - x1) * (x2 - x1);
		long y = 1l * (y2 - y1) * (y2 - y1);
		return x + y <= (1l * dist * dist);
	}

	private void dfs(int src, List<List<Integer>> adj, boolean[] vis, int[] size) {
		vis[src] = true;
		size[0]++;

		for (int node : adj.get(src)) {
			if (!vis[node]) {
				dfs(node, adj, vis, size);
			}
		}
	}
}
