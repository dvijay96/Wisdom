package com.problems.java.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

	public static void main(String[] args) {
		NQueens obj = new NQueens();

		List<List<String>> ans = obj.solveNQueensOptimized(4);

		for (int i = 0; i < ans.size(); i++) {
			System.out.println("Result " + (i + 1));
			for (String str : ans.get(i)) {
				for (int j = 0; j < str.length(); j++) {
					System.out.print(str.charAt(j) + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

//	Using hashing for isSafe func
//	A row map to store which row the queen has been placed
//	Suppose, Q is placed at 0th row 1st , then we make rowMap[0]=True,
//	then next time we can check if rowMap[0]!=True then place Q.
//	
//	Diagonal row-col Maps
//	ex: a 4x4 matrix
//	
//	i/j  0 1 2 3
//	  0| 0 1 2 3
//	  1| 1 2 3 4
//	  2| 2 3 4 5
//	  3| 3 4 5 6
	public List<List<String>> solveNQueensOptimized(int n) {
		List<List<String>> ans = new ArrayList<>();
		if (n == 1) {
			ans.add(Arrays.asList("Q"));
			return ans;
		}
		char[][] board = new char[n][n];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = '.';
		}
		boolean[] rowMap = new boolean[n];
		boolean[] upperDiagonalMap = new boolean[2 * n - 1];
		boolean[] lowerDiagonalMap = new boolean[2 * n - 1];

		solveQueens(0, ans, board, rowMap, upperDiagonalMap, lowerDiagonalMap);
		return ans;
	}

	private void solveQueens(int col, List<List<String>> ans, char[][] board, boolean[] rowMap,
			boolean[] upperDiagonalMap, boolean[] lowerDiagonalMap) {
		int n = board.length;
		if (col == n) {
			List<String> pos = new ArrayList<>();
			for (char[] ar : board) {
				pos.add(new String(ar));
			}
			ans.add(pos);
		} else {
			for (int row = 0; row < n; row++) {
				if (!rowMap[row] && !lowerDiagonalMap[col + row] && !upperDiagonalMap[n - 1 + col - row]) {
					rowMap[row] = true;
					lowerDiagonalMap[col + row] = true;
					upperDiagonalMap[n - 1 + col - row] = true;
					board[row][col] = 'Q';
					solveQueens(col + 1, ans, board, rowMap, upperDiagonalMap, lowerDiagonalMap);
					rowMap[row] = false;
					lowerDiagonalMap[col + row] = false;
					upperDiagonalMap[n - 1 + col - row] = false;
					board[row][col] = '.';
				}
			}
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 */

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ans = new ArrayList<>();
		if (n == 1) {
			ans.add(Arrays.asList("Q"));
			return ans;
		}
		char[][] board = new char[n][n];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = '.';
		}

		solveQueens(0, ans, board);
		return ans;
	}

	private void solveQueens(int col, List<List<String>> ans, char[][] board) {

		if (col == board.length) {
			List<String> pos = new ArrayList<>();
			for (char[] ar : board) {
				pos.add(new String(ar));
			}
			ans.add(pos);
		} else {
			for (int row = 0; row < board.length; row++) {
				if (isSafe(board, col, row)) {
					board[row][col] = 'Q';
					solveQueens(col + 1, ans, board);
					board[row][col] = '.';
				}
			}
		}
	}

	private boolean isSafe(char[][] board, int col, int row) {
		int tRow = row;
		int tCol = col;

		// left side in the same row
		while (col >= 0) {
			if (board[row][col] == 'Q')
				return false;
			col--;
		}

		// left upper diagonal
		col = tCol;
		while (row >= 0 && col >= 0) {
			if (board[row][col] == 'Q')
				return false;
			row--;
			col--;
		}

		// left lower diagonal
		col = tCol;
		row = tRow;
		while (row < board.length && col >= 0) {
			if (board[row][col] == 'Q')
				return false;
			col--;
			row++;
		}

		return true;
	}
}
