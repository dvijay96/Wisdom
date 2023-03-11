package com.problems.leetcode.daily.array;

import java.util.LinkedList;
import java.util.Queue;

public class AsFarFromLandAsPossible {

//	Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, 
//	find a water cell such that its distance to the nearest land cell is maximized, and return the distance. 
//			If no land or water exists in the grid, return -1.
//			
//	The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and 
//	(x1, y1) is |x0 - x1| + |y0 - y1|.
//
//			 
//
//	Example 1:
//								1 0 1
//								0 0 0
//								1 0 1
//
//			Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
//			Output: 2
//			Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
//			
//	
//	Example 2:
//								1 0 0
//								0 0 0
//								0 0 0
//
//			Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
//			Output: 4
//			Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.

	public static void main(String[] args) {

		int[][] arr = { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } };

		System.out.println(maxDistance(arr));
	}

//	Approach:-
//	
//	-> To use BFS to check how many levels it takes to turn all water cells to land cells.
//	-> Since at least one cell is land cell then the ans would be levels - 1.
//	-> Starting with, we need to push all the positions of land cells into the queue.
//	-> Then while bfs traversal, if in any direction of the land cell , water cell is found , convert it to land cell 
//		and push into queue.
	public static int maxDistance(int[][] grid) {

		int n = grid.length;
		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					queue.add(new int[] { i, j });
				}
			}
		}

		// No Land or no water
		if (queue.isEmpty() || queue.size() == n * n) {
			return -1;
		}

		return bfs(grid, n, queue);
	}

	private static int bfs(int[][] grid, int n, Queue<int[]> queue) {
		int res = -1;

		int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int x = 0; x < size; x++) {
				int[] pos = queue.poll();

				for (int[] dir : dirs) {
					int i = pos[0] + dir[0];
					int j = pos[1] + dir[1];

					if (i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 0) {
						grid[i][j] = 1;
						queue.add(new int[] { i, j });
					}
				}
//				// right
//				if (j + 1 < n && grid[i][j + 1] == 0) {
//					grid[i][j + 1] = 1;
//					queue.add(new int[] { i, j + 1 });
//				}
//				// left
//				if (j - 1 >= 0 && grid[i][j - 1] == 0) {
//					grid[i][j - 1] = 1;
//					queue.add(new int[] { i, j - 1 });
//				}
//				// bottom
//				if (i + 1 < n && grid[i + 1][j] == 0) {
//					grid[i + 1][j] = 1;
//					queue.add(new int[] { i + 1, j });
//				}
//				// top
//				if (i - 1 >= 0 && grid[i - 1][j] == 0) {
//					grid[i - 1][j] = 1;
//					queue.add(new int[] { i - 1, j });
//				}
			}
			res++;
		}

		return res;
	}
}
