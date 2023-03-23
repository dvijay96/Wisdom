package com.problems.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfDistinctIslands {

	public static void main(String[] args) {

	}

//	Approach:- 
//			-> We traverse the grid, and when we find a 1, we start dfs/bfs for all connected 1's 
//			-> We need to store the above traversed part in a set for avoiding duplicates.
//			-> How to store a traversed part along with its shape?
//					We calculate the distance of each connected 1 from the source 1 i.e where we first got the 1.
//					we store these distances in a list and this list we store in the SET.
//					
					
//					So if we again encounter any traversal of above kind, we wont be duplicating it in our final ans.
//			-> The no. of such distinct traversals collected in the set will the ans.
//	
//	TC:- O(n*m)
//	SC:- O(n*m)
	int countDistinctIslands(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		Set<List<String>> set = new HashSet<>();
		int[][] dirs = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

		boolean[][] vis = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1 && !vis[i][j]) {
					List<String> distance = new ArrayList<>();
					int[] src = { i, j };

					dfs(i, j, grid, vis, distance, dirs, src);		// n*m*4

					set.add(distance);
				}
			}
		}

		return set.size();
	}

	private void dfs(int x, int y, int[][] grid, boolean[][] vis, List<String> distance, int[][] dirs, int[] src) {
		vis[x][y] = true;
		int dx = x - src[0];
		int dy = y - src[1];
		distance.add(dx + " " + dy);

		for (int[] dir : dirs) {
			int i = x + dir[0];
			int j = y + dir[1];

			if (i == -1 || i == grid.length || j == -1 || j == grid[0].length || vis[i][j] || grid[i][j] == 0) {
				continue;
			}

			dfs(i, j, grid, vis, distance, dirs, src);
		}
	}
}
