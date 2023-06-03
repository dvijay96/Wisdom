package com.problems.recursions;

import java.util.ArrayList;
import java.util.List;

public class RatInMaze {

	public static void main(String[] args) {
		RatInMaze obj = new RatInMaze();
		int[][] grid = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 } };

		System.out.println(obj.findPath(grid));

	}

	List<String> findPath(int[][] arr) {
		List<String> ans = new ArrayList<>();
		if (arr[0][0] == 1)
			solve(ans, arr, new StringBuilder(), 0, 0);
		return ans;
	}

	void solve(List<String> ans, int[][] arr, StringBuilder str, int row, int col) {
		if (row == arr.length - 1 && col == arr[0].length - 1) {
			ans.add(str.toString());
		} else {
			arr[row][col] = -1;

			// Down
			if (row < arr.length - 1 && arr[row + 1][col] == 1) {
				str.append('D');
				solve(ans, arr, str, row + 1, col);
				str.deleteCharAt(str.length() - 1);
			}

			// Left
			if (col > 0 && arr[row][col - 1] == 1) {
				str.append('L');
				solve(ans, arr, str, row, col - 1);
				str.deleteCharAt(str.length() - 1);
			}

			// Right
			if (col < arr[0].length - 1 && arr[row][col + 1] == 1) {
				str.append('R');
				solve(ans, arr, str, row, col + 1);
				str.deleteCharAt(str.length() - 1);
			}

			// Up
			if (row > 0 && arr[row - 1][col] == 1) {
				str.append('U');
				solve(ans, arr, str, row - 1, col);
				str.deleteCharAt(str.length() - 1);
			}
			
			arr[row][col]=1;
		}
	}
}
