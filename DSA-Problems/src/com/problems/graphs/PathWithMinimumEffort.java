package com.problems.graphs;

import com.problems.java.utility.ArrayUtils;

public class PathWithMinimumEffort {

//	You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, 
//	where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), 
//	and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, 
//	or right, and you wish to find a route that requires the minimum effort.
//
//	A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//	Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
//	 
//
//	Example 1:
//
//	Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//	Output: 2
//	Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//	This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
//	
//	Example 2:
//
//	Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//	Output: 1
//	Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better 
//	than route [1,3,5,3,5].
//	
//	Example 3:
//
//	Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//	Output: 0
//	Explanation: This route does not require any effort.

	public static void main(String[] args) {

		PathWithMinimumEffort obj = new PathWithMinimumEffort();
		int[][] grid = new int[5][5];

		for (int[] arr : grid) {
			ArrayUtils.fillRandomRange(arr, 1, 20);
		}

		for (int[] arr : grid) {
			ArrayUtils.print(arr);
		}

		System.out.println(obj.minimumEffortPath(grid));
	}

	private int[][] dirs = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
	private int n;
	private int m;

//	Intuition:-
//			Finding a path with least maximum absolute difference between adjacent cells in the grid.
//			For this we can use BinarySearchBinary SearchBinarySearch to find out the minimum difference out of all 
//			maximum difference path.
//
//	Approach:-
//			-> Keep low as 0 and high as 10^6 (given max possible difference).
//			-> Start the BS with finding the mid as one of the possible min diff.
//			-> With the above diff, perform DFS on the grid and check if path exists from source to destination 
//				with the difference not greater than the diff considered from BS.
//			-> If path exists, try to find least diff by shrinking the search space i.e high = mid or low = mid + 1,
//				accordingly.
//			
//	Complexity:-
//			Time complexity: O((m+n)*log(10^6))
//			Space complexity: O(n*m)
	public int minimumEffortPath(int[][] heights) {
		int low = 0;
		int high = 10000000;
		n = heights.length;
		m = heights[0].length;

		while (low < high) {
			int mid = low + (high - low) / 2;

			boolean[][] vis = new boolean[n][m];

			if (dfs(0, 0, heights, vis, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	boolean dfs(int i, int j, int[][] heights, boolean[][] vis, int dist) {
		vis[i][j] = true;

		if (i == n - 1 && j == m - 1) {
			return true;
		}

		for (int[] dir : dirs) {
			int x = i + dir[0];
			int y = j + dir[1];

			if (x == -1 || x == n || y == -1 || y == m || vis[x][y] || Math.abs(heights[i][j] - heights[x][y]) > dist) {
				continue;
			} else if (dfs(x, y, heights, vis, dist)) {
				return true;
			}
		}
		return false;
	}
}
