package com.problems.graphs;

import com.problems.java.utility.ArrayUtils;

public class NumberOfIslands {

	public static void main(String[] args) {
		char[][] grid = new char[5][4];

		for (char[] g : grid) {
			ArrayUtils.fillRandomRanges(g, '0', '1');
		}

		for (char[] g : grid) {
			ArrayUtils.print(g);
			System.out.println();
		}

		System.out.println(numIslands(grid));
	}

//	Approach:-
//			-> Consider each cell with 1 as a node.
//			-> When that node is found, perform a BFS/DFS traversal in all 8 directions.
//			-> Mark the visited nodes.
//			-> Whenever such node is found, we increment the island count.
//			-> We need to find such starting points for each island traversal.
//	
//	TC:- O(N*M)  since , outer loop runs n*m and dfs also in worst case if all cells are 1 will traverse over whole grid.
//	SC:- O(N*M)	 since, in worst case if all are 1's then stack space would be that much
	public static int numIslands(char[][] grid) {
		// boolean[][] visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) { // n*m
				if (grid[i][j] == '1') {
					count++;
					dfs(i, j, grid);
				}
			}
		}
		return count;
	}

	private static void dfs(int row, int col, char[][] grid) {
		grid[row][col] = '2';

		int n = grid.length;
		int m = grid[0].length;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) { // 3 * 3
				int nrow = row + i;
				int ncol = col + j;

				if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && grid[nrow][ncol] == '1') {
					dfs(nrow, ncol, grid);
				}
			}
		}

		// //right
		// if(j+1<grid[i].length && grid[i][j+1]=='1'){
		// dfs(i, j+1, grid);
		// }

		// //bottom
		// if(i+1<grid.length && grid[i+1][j]=='1'){
		// dfs(i+1, j, grid);
		// }

		// //left
		// if(j-1>=0 && grid[i][j-1]=='1'){
		// dfs(i, j-1, grid);
		// }

		// //top
		// if(i-1>=0 && grid[i-1][j]=='1'){
		// dfs(i-1, j, grid);
		// }

		// //top left Diagonal
		// if(i-1>=0 && j-1>=0 && grid[i-1][j-1]=='1'){
		// dfs(i-1, j-1, grid);
		// }

		// //top right diagonal
		// if(i-1>=0 && j+1<grid[i].length && grid[i-1][j+1]=='1'){
		// dfs(i-1, j+1, grid);
		// }

		// //bottom left diagonal
		// if(i+1<grid.length && j-1>=0 && grid[i+1][j-1]=='1'){
		// dfs(i+1, j-1, grid);
		// }

		// //bottom right diagonal
		// if(i+1<grid.length && j+1<grid[i].length && grid[i+1][j+1]=='1'){
		// dfs(i+1, j+1, grid);
		// }
	}
}
