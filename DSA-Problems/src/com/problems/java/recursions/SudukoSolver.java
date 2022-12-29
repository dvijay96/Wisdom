package com.problems.java.recursions;

public class SudukoSolver {

	public static void main(String[] args) {

		char[][] board = { { '9', '5', '7', '.', '1', '3', '.', '8', '4' },
				{ '4', '8', '3', '.', '5', '7', '1', '.', '6' }, { '.', '1', '2', '.', '4', '9', '5', '3', '7' },
				{ '1', '7', '.', '3', '.', '4', '9', '.', '2' }, { '5', '.', '4', '9', '7', '.', '3', '6', '.' },
				{ '3', '.', '9', '5', '.', '8', '7', '.', '1' }, { '8', '4', '5', '7', '9', '.', '6', '1', '3' },
				{ '.', '9', '1', '.', '3', '6', '.', '7', '5' }, { '7', '.', '6', '1', '8', '5', '4', '.', '9' } };

		for (char[] chAr : board) {
			for (char ch : chAr) {
				if (ch == '.')
					System.out.print("0" + " ");
				else
					System.out.print(ch + " ");
			}
		}
		System.out.println("\nBefore: ");
		printBoard(board);

		solveSudokuOptimized(board, 0, 0);

		System.out.println("After: ");
		printBoard(board);

	}

	private static void printBoard(char[][] board) {
		for (int i = 0; i < 9; i++) {
			System.out.print("\t");
			for (int j = 0; j < 9; j++)
				System.out.print(board[i][j] + "  ");
			System.out.println();
		}
	}

	public static boolean solveSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					return helpher(board, i, j);
				}
			}
		}
		return true;
	}

	private static boolean helpher(char[][] board, int row, int col) {
		for (char c = '1'; c <= '9'; c++) {
			if (isValid(board, row, col, c)) {
				board[row][col] = c;
				if (solveSudoku(board))
					return true;
				else
					board[row][col] = '.';
			}
		}
		return false;
	}

	public static boolean solveSudokuOptimized(char[][] board, int row, int col) {
		for (int i = row; i < 9; i++, col = 0) {
			for (int j = col; j < 9; j++) {
				if (board[i][j] == '.') {
					return helpher2(board, i, j);
				}
			}
		}
		return true;
	}

	private static boolean helpher2(char[][] board, int row, int col) {
		for (char c = '1'; c <= '9'; c++) {
			if (isValid(board, row, col, c)) {
				board[row][col] = c;

				if (solveSudokuOptimized(board, row, col + 1))
					return true;
				else
					board[row][col] = '.';
			}
		}

		return false;
	}

	public static boolean isValid(char[][] board, int row, int col, char c) {
		int blockRow = 3 * (row / 3);
		int blockCol = 3 * (col / 3);
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == c || board[row][i] == c || board[blockRow + i / 3][blockCol + i % 3] == c)
				return false;
		}
		return true;
	}

}
