package com.problems.graphs;

import java.util.concurrent.ThreadLocalRandom;

import com.problems.java.utility.ArrayUtils;

public class ReplaceOWithX {

//	Given a matrix mat of size N x M where every element is either O or X.
//	Replace all O with X that are surrounded by X.
//	A O (or a set of O) is considered to be surrounded by X if there are X at locations just below, just above, just 
//	left and just right of it.
//
//	Example 1:
//
//	Input: n = 5, m = 4
//	
//	mat = {{'X', 'X', 'X', 'X'}, 
//	       {'X', 'O', 'X', 'X'}, 
//	       {'X', 'O', 'O', 'X'}, 
//	       {'X', 'O', 'X', 'X'}, 
//	       {'X', 'X', 'O', 'O'}}
//	
//	Output: ans = {{'X', 'X', 'X', 'X'}, 
//	               {'X', 'X', 'X', 'X'}, 
//	               {'X', 'X', 'X', 'X'}, 
//	               {'X', 'X', 'X', 'X'}, 
//	               {'X', 'X', 'O', 'O'}}
//	
//	Explanation: Following the rule the above 
//	matrix is the resultant matrix. 

	public static void main(String[] args) {
		ReplaceOWithX obj = new ReplaceOWithX();

		char[][] board = new char[5][5];

		obj.createExample(board);
		System.out.println("Before: ");
		ArrayUtils.print(board);

		obj.solve(board);
		System.out.println("\nAfter: ");
		ArrayUtils.print(board);
	}

//	Approach:
//			-> Since any O which is at the boudary can't be converted to X hence all O's connected to the boundary O can't be coverted to X.
//			-> For this we traverse the boundaries and if any O found, we perform DFS/BFS.
//			-> After traversing and performing DFS/BFS, we convert the O's which were not visited by the DFS/BFS.
//	
//	TC:- O(
//			
	public void solve(char[][] board) {
		int m = board.length;
		int n = board[0].length;
		boolean[][] vis = new boolean[m][n];
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		// i = 0
		for (int j = 0; j < n; j++) {
			if (board[0][j] != 'X' && !vis[0][j]) {
				dfs(0, j, board, vis, dirs);
			}
		}

		// j = 0
		for (int i = 0; i < m; i++) {
			if (board[i][0] != 'X' && !vis[i][0]) {
				dfs(i, 0, board, vis, dirs);
			}
		}

		// j = n-1
		for (int i = 0; i < m; i++) {
			if (board[i][n - 1] != 'X' && !vis[i][n - 1]) {
				dfs(i, n - 1, board, vis, dirs);
			}
		}

		// i = m-1
		for (int j = 0; j < n; j++) {
			if (board[m - 1][j] != 'X' && !vis[m - 1][j]) {
				dfs(m - 1, j, board, vis, dirs);
			}
		}

		for (int i = 1; i < m - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (!vis[i][j] && board[i][j] != 'X') {
					vis[i][j] = true;
					board[i][j] = 'X';
				}
			}
		}
	}

	void dfs(int i, int j, char[][] board, boolean[][] vis, int[][] dirs) {
		vis[i][j] = true;

		for (int[] dir : dirs) {
			int x = i + dir[0];
			int y = j + dir[1];

			if (x == -1 || x == board.length || y == -1 || y == board[i].length || vis[x][y] || board[x][y] == 'X')
				continue;

			dfs(x, y, board, vis, dirs);
		}
	}

	void createExample(char[][] board) {
		char[] ar = { 'X', 'O' };
		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ar[random.nextInt(0, 2)];
			}
		}
	}
}
