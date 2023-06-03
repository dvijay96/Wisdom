package com.problems.graphs;

import java.util.HashSet;
import java.util.Set;

import com.problems.graphs.template.DisjointSet;

public class MakeLargeIsland {

//	You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
//
//	Return the size of the largest island in grid after applying this operation.
//
//	An island is a 4-directionally connected group of 1s.
//
//
//	Example 1:
//
//	Input: grid = [[1,0],[0,1]]
//	Output: 3
//	Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
//	
//	Example 2:
//
//	Input: grid = [[1,1],[1,0]]
//	Output: 4
//	Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
//	
//	Example 3:
//
//	Input: grid = [[1,1],[1,1]]
//	Output: 4
//	Explanation: Can't change any 0 to 1, only one island with area = 4.

	public static void main(String[] args) {

		MakeLargeIsland obj = new MakeLargeIsland();
		int[][] grid = { { 1, 0 }, { 0, 1 } };

		System.out.println(obj.largestIslandOptimized(grid));

	}

//	Approach:- 
//			-> From each 0 cell, we'll traverse connected 1 cells and mark the count of total cells for this traversal.
//			-> Like this we can find the largest island.
//			
//	TC:- O(n*m*k) , k = no. of 0s in the grid.
//	SC:- O(n*m)
	public int largestIslandBruteForced(int[][] grid) {
		int n = grid.length;
		int maxSize = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					int[] size = new int[1];
					dfs(i, j, grid, new boolean[n][n], size);
					maxSize = Math.max(size[0], maxSize);
				}
			}
		}

		return maxSize == 0 ? n * n : maxSize;
	}

	private int[][] dirs = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	private void dfs(int i, int j, int[][] grid, boolean[][] vis, int[] size) {
		size[0]++;
		vis[i][j] = true;

		for (int[] dir : dirs) {
			int row = i + dir[0];
			int col = j + dir[1];

			if (isNotValid(row, col, grid, vis)) {
				continue;
			}

			dfs(row, col, grid, vis, size);
		}
	}

	private boolean isNotValid(int row, int col, int[][] grid, boolean[][] vis) {
		return row == -1 || row == grid.length || col == -1 || col == grid.length || grid[row][col] == 0
				|| vis[row][col];
	}

	/***************************************************************************/

//	Approach:
	public int largestIslandOptimized(int[][] grid) {

		int n = grid.length;

		DisjointSet dsu = new DisjointSet(n * n);

		boolean zeroFlag = connectOnes(grid, n, dsu);

		if (!zeroFlag)
			return n * n;

		return calcMaxLand(grid, n, dsu);
	}

	private int calcMaxLand(int[][] grid, int n, DisjointSet dsu) {
		int ans = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					Set<Integer> pSet = new HashSet<>();
					for (int[] dir : dirs) {
						int row = i + dir[0];
						int col = j + dir[1];

						if (isValid(row, col, grid)) {
							int adj = n * row + col;
							pSet.add(dsu.findParent(adj));
						}
					}
					int size = 0;

					for (int s : pSet) {
						size += dsu.getSize()[s];
					}
					ans = Math.max(size + 1, ans);
				}
			}
		}
		return ans;
	}

	private boolean connectOnes(int[][] grid, int n, DisjointSet dsu) {
		boolean zeroFlag = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					int node = n * i + j;

					for (int[] dir : dirs) {
						int row = i + dir[0];
						int col = j + dir[1];

						if (isValid(row, col, grid)) {
							int adj = n * row + col;
							dsu.unionBySize(node, adj);
						}
					}
				} else {
					zeroFlag = true;
				}
			}
		}
		return zeroFlag;
	}

	private boolean isValid(int row, int col, int[][] grid) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid.length && grid[row][col] == 1;
	}
}
