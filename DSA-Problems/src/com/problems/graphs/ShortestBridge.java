package com.problems.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {

//	You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
//
//	An island is a 4-directionally connected group of 1's not connected to any other 1's. 
//	There are exactly two islands in grid.
//
//	You may change 0's to 1's to connect the two islands to form one island.
//
//	Return the smallest number of 0's you must flip to connect the two islands.
//
//	 
//
//	Example 1:
//
//	Input: grid = [[0,1],[1,0]]
//	Output: 1
//	Example 2:
//
//	Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
//	Output: 2
//	Example 3:
//
//	Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
//	Output: 1

	public static void main(String[] args) {
		ShortestBridge obj = new ShortestBridge();

		int[][] grid = { { 0, 0, 1, 0, 1 }, { 0, 1, 1, 0, 1 }, { 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 } };

		System.out.println(obj.shortestBridge(grid));
	}

	private int[][] dirs = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

//	Approach:
//			-> Traverse only the first land found and keep storing its positions.
//			-> Traverse from each of the above position in BFS fashion until a 1 is not encountered.
//			-> The no. of steps until a 1 is not found will be the ans.
//			
//	TC:- O(n^2)
//	SC:- O(n^2)		

	public int shortestBridge(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		boolean[][] vis = new boolean[n][m];
		Queue<int[]> queue = new LinkedList<>();
		boolean flag = false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!vis[i][j] && grid[i][j] == 1) {
					dfs(i, j, grid, vis, queue);
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}
		return bfs(grid, vis, queue);
	}

	private int bfs(int[][] grid, boolean[][] vis, Queue<int[]> queue) {
		int ans = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				int[] curr = queue.poll();
				int i = curr[0];
				int j = curr[1];

				for (int[] dir : dirs) {
					int row = i + dir[0];
					int col = j + dir[1];

					if (row == -1 || row == grid.length || col == -1 || col == grid[i].length || vis[row][col])
						continue;
					if (grid[row][col] == 1)
						return ans;
					queue.add(new int[] { row, col });
					vis[row][col] = true;
				}
			}
			ans++;
		}
		return -1;
	}

	private void dfs(int i, int j, int[][] grid, boolean[][] vis, Queue<int[]> queue) {
		vis[i][j] = true;
		queue.add(new int[] { i, j });
		for (int[] dir : dirs) {
			int row = i + dir[0];
			int col = j + dir[1];

			if (row == -1 || row == grid.length || col == -1 || col == grid[i].length || grid[row][col] == 0
					|| vis[row][col])
				continue;
			dfs(row, col, grid, vis, queue);
		}
	}
}
