package com.problems.java.recursions;

import java.util.Scanner;

public class WordSearch {

	public static void main(String[] args) {

		WordSearch obj = new WordSearch();

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int m = scan.nextInt();

		char[][] board = new char[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = scan.next().charAt(0);
			}
		}
		System.out.println();
		System.out.print("  ");
		for (int j = 0; j < m; j++)
			System.out.print(j + " ");
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < m; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

		scan.close();

		System.out.println(obj.isWordExist(board, "mdbad"));
	}

	public boolean isWordExist(char[][] board, String word) {
		int wp = 0;
		int row = board.length;
		int col = board[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == word.charAt(wp)) {
					if (searchNext(board, word, i, j, wp, row, col))
						return true;
				}
			}
		}
		return false;
	}

	boolean searchNext(char[][] board, String word, int row, int col, int wp, int n, int m) {

		if (wp == word.length())
			return true;

		if (row < 0 || row == n || col < 0 || col == m || board[row][col] != word.charAt(wp))
			return false;

		board[row][col] = '#';

		boolean top = searchNext(board, word, row - 1, col, wp + 1, n, m);
		boolean bottom = searchNext(board, word, row + 1, col, wp + 1, n, m);
		boolean left = searchNext(board, word, row, col - 1, wp + 1, n, m);
		boolean right = searchNext(board, word, row, col + 1, wp + 1, n, m);

		board[row][col] = word.charAt(wp);

		return top || bottom || left || right;
	}
}
