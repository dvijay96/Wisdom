package com.problems.graphs;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;

public class RottenOranges {

//	You are given an m x n grid where each cell can have one of three values:
//
//		0 representing an empty cell,
//		1 representing a fresh orange, or
//		2 representing a rotten orange.
//		Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
//
//		Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible,
//		return -1.
//
//		 
//		Input: grid = {{0,1,2},{0,1,2},{2,1,1}}
//		Output: 1
//		
//		Explanation: The grid is-
//		0 1 2
//		0 1 2
//		2 1 1
//		Oranges at positions (0,2), (1,2), (2,0)
//		will rot oranges at (0,1), (1,1), (2,2) and 
//		(2,1) in unit time.
//
//		Example 1:
//		
//			Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//			Output: 4
//			
//			Explanation: In T1, the orange at (0,0) will rott (0,1) & (1,0).
//						 In T2, the previously rotten oranges (0,1) & (1,0) will rott (0,2) & (1,1)
//						 In T3, the prevoisly rotten oranges (0,2) & (1,1) will rott (2,1).
//						 In T4, the preiously rotten orange (2,1) will rott (2,2)
//		
//		Example 2:
//			
//			Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//			Output: -1
//		    
//			Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only
//						happens 4-directionally.
//		
//		Example 3:
//
//			Input: grid = [[0,2]]
//			Output: 0
//			
//			Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
//		 
//
//		Constraints:
//
//		m == grid.length
//		n == grid[i].length
//		1 <= m, n <= 10
//		grid[i][j] is 0, 1, or 2.
//		
	public static void main(String[] args) {

		int[][] grid = new int[3][3];

		for (int i = 0; i < grid.length; i++) {
			ArrayUtils.fillRandomRange(grid[i], 0, 2);
		}

		ArrayUtils.print(grid);
		System.out.println();

		System.out.println(orangesRotting(grid));
	}

//	Approach:- 
//			-> We are to find the total amount of time needed to rott all adjacent fresh oranges in the grid
//			-> At any time each rotten orange can rott a adjacent fresh orange that is present in his level.
//			-> We need to traverse the grid in a BFS fashion to find out how many levels it takes to rott oranges.
//	
//	TC:- O(n*m)
//	SC:- O(n*m)
	public static int orangesRotting(int[][] grid) {

		Queue<int[]> queue = new LinkedList<>();
		int[] freshCount = { 0 };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 2) {
					queue.add(new int[] { i, j });
				} else if (grid[i][j] == 1) {
					freshCount[0]++;
				}
			}
		}

		if (freshCount[0] == 0) {
			return 0;
		}

		int time = bfs(grid, queue, freshCount);

		// for(int i = 0; i< grid.length; i++){
		// for(int j=0; j<grid[i].length; j++){
		// if(grid[i][j] == 1){
		// return -1;
		// }
		// }
		// }

		return freshCount[0] == 0 ? time : -1;
	}

	private static int bfs(int[][] grid, Queue<int[]> queue, int[] freshCount) {
		int res = -1;
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size > 0) {
				int[] pos = queue.poll();

				for (int[] dir : dirs) {
					int row = pos[0] + dir[0];
					int col = pos[1] + dir[1];

					if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
						grid[row][col] = 2;
						freshCount[0]--;
						queue.add(new int[] { row, col });
					}
				}
				size--;
			}

			res++;
		}
		return res;
	}
}
