package com.problems.graphs;

import java.util.concurrent.ThreadLocalRandom;

import com.problems.java.utility.ArrayUtils;

public class NumberOfEnclaves {

//	You are given an n x m binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
//
//	A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the 
//	boundary of the grid.
//
//	Find the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
//
//	Example 1:
//
//	Input:
//	grid[][] = {{0, 0, 0, 0},
//	            {1, 0, 1, 0},
//	            {0, 1, 1, 0},
//	            {0, 0, 0, 0}}
//	Output:
//	3
//	Explanation:
//	0 0 0 0
//	1 0 1 0
//	0 1 1 0
//	0 0 0 0
//	The highlighted cells represents the land cells.
//
//	Example 2:
//
//	Input:
//	grid[][] = {{0, 0, 0, 1},
//	            {0, 1, 1, 0},
//	            {0, 1, 1, 0},
//	            {0, 0, 0, 1},
//	            {0, 1, 1, 0}}
//	Output:
//	4
//	Explanation:
//	0 0 0 1
//	0 1 1 0
//	0 1 1 0
//	0 0 0 1
//	0 1 1 0
//	The highlighted cells represents the land cells.

	public static void main(String[] args) {
		NumberOfEnclaves obj = new NumberOfEnclaves();

		int[][] mat = new int[5][5];

		obj.createExample(mat);

		ArrayUtils.print(mat);

		System.out.println("\n" + obj.numberOfEnclaves(mat));
	}

//	Approach:-
//			-> Travel on the boundary and for each 1 on the boundary traverse all connected 1's and mark them visited.
//			-> Travel non boundary cells, check if it is 1 and not being visited by boundary cell 1, then count that in.
//			-> return such count.
//	
//	TC:- O(N*M)
//	SC:- O(N*M)
	int numberOfEnclaves(int[][] grid) {
		boolean[][] vis = new boolean[grid.length][grid[0].length];
		int[][] dirs = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {

				if ((i == 0 || j == 0 || i == grid.length - 1 || j == grid[i].length - 1) && grid[i][j] == 1) {
					dfs(i, j, grid, vis, dirs);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1 && !vis[i][j])
					count++;
			}
		}

		return count;
	}

	private void dfs(int x, int y, int[][] grid, boolean[][] vis, int[][] dirs) {
		vis[x][y] = true;

		for (int[] dir : dirs) {
			int i = x + dir[0];
			int j = y + dir[1];

			if (i == -1 || i == grid.length || j == -1 || j == grid[0].length || vis[i][j] || grid[i][j] == 0) {
				continue;
			}

			dfs(i, j, grid, vis, dirs);
		}
	}

	private void createExample(int[][] board) {
		char[] ar = { 1, 0 };
		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ar[random.nextInt(0, 2)];
			}
		}
	}
}
