package com.problems.graphs;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;

public class ShortestPathBinaryMatrix {

//	Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear
//			path, return -1.
//
//			A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell 
//			(i.e., (n - 1, n - 1)) such that:
//
//			All the visited cells of the path are 0.
//			All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share 
//					an edge or a corner).
//			The length of a clear path is the number of visited cells of this path.
//
//			 
//
//			Example 1:
//
//
//			Input: grid = [[0,1],[1,0]]
//			Output: 2
//			
//			Example 2:
//
//
//			Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
//			Output: 4
//			
//			Example 3:
//
//			Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
//			Output: -1
	public static void main(String[] args) {
		ShortestPathBinaryMatrix obj = new ShortestPathBinaryMatrix();
		int[][] grid = new int[5][5];

		for (int[] arr : grid) {
			ArrayUtils.fillRandomRange(arr, 0, 1);
		}

		for (int[] arr : grid) {
			ArrayUtils.print(arr);
		}

		System.out.println(obj.shortestPathBinaryMatrixBFS(grid));
		System.out.println(obj.shortestPathBinaryMatrixDFS(grid));

		Math.abs(1 - 1);
	}

	public int shortestPathBinaryMatrixBFS(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
			return -1;
		}

		boolean[][] visited = new boolean[m][n];
		visited[0][0] = true;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0 });

		return bfs(grid, m, n, visited, queue);
	}

	private int bfs(int[][] grid, int m, int n, boolean[][] visited, Queue<int[]> queue) {
		int ans = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] pop = queue.remove();
				if (pop[0] == m - 1 && pop[1] == n - 1) {
					return ans + 1;
				}
				for (int k = 0; k < 8; k++) {
					int nextX = dirs[k][0] + pop[0];
					int nextY = dirs[k][1] + pop[1];

					if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]
							&& grid[nextX][nextY] == 0) {
						queue.add(new int[] { nextX, nextY });
						visited[nextX][nextY] = true;
					}

				}
			}
			ans++;
		}

		return -1;
	}

//	TLE solution
//	
//	Reason:- 
//	   -> If we want to find a possible path, DFS will not be more efficient. Because DFS will return a possible path 
//			   if found, while it may not the shortest path.
//
//	   -> BFS will try every possible path at the same time.
//
//	   -> If we want to find the shortest of all possible paths, BFS is more efficient. It's impossible for DFS to 
//	        determine which is the shortest before trying all possible paths.

	private int ans = Integer.MAX_VALUE;

	public int shortestPathBinaryMatrixDFS(int[][] grid) {

		int n = grid.length;
		int m = grid[0].length;
		boolean[][] vis = new boolean[n][m];

		if (grid[0][0] != 0 || grid[n - 1][m - 1] != 0) {
			return -1;
		}

		dfs(0, 0, grid, vis, 1);

		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	private int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	void dfs(int i, int j, int[][] grid, boolean[][] vis, int len) {
		vis[i][j] = true;

		if (i == grid.length - 1 && j == grid[i].length - 1) {
			ans = Math.min(ans, len);
		} else {
			for (int[] dir : dirs) {
				int x = i + dir[0];
				int y = j + dir[1];

				if (x == -1 || x == grid.length || y == -1 || y == grid[i].length || vis[x][y] || grid[x][y] != 0) {
					continue;
				}
				dfs(x, y, grid, vis, len + 1);
			}
		}
		vis[i][j] = false;
	}
}
