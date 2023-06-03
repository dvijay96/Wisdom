package com.problems.array.medium;

import com.problems.java.utility.ArrayUtils;

public class SpiralMatrixII {

//	Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

	public static void main(String[] args) {

		SpiralMatrixII obj = new SpiralMatrixII();

		int[][] arr1 = obj.generateMatrix(3);

		int[][] arr2 = obj.generateMatrixSol2(3);

		ArrayUtils.print(arr1);

		ArrayUtils.print(arr2);

	}

	public int[][] generateMatrix(int n) {
		int[][] ans = new int[n][n];

		int left = 0;
		int right = n - 1;
		int top = 0;
		int bottom = n - 1;

		int count = 0;

		while (count < n * n) {
			for (int i = left; i <= right && count < n * n; i++) {
				ans[top][i] = ++count;
			}
			top++;

			for (int i = top; i <= bottom && count < n * n; i++) {
				ans[i][right] = ++count;
			}
			right--;

			for (int i = right; i >= left && count < n * n; i--) {
				ans[bottom][i] = ++count;
			}
			bottom--;

			for (int i = bottom; i >= top && count < n * n; i--) {
				ans[i][left] = ++count;
			}

			left++;
		}

		return ans;
	}

//	Approach:

//	We have to walk in 4 directions forming a layer. We use an array dir that stores the changes in x and y co-ordinates in each direction.
//	Example
//
//	In left to right walk ( direction #1 ), x co-ordinates remains same and y increments (x=0x = 0x=0, y=1y = 1y=1).
//
//	In right to left walk ( direction #3 ), x remains same and y decrements (x=0x = 0x=0, y=−1y = -1y=−1).
//
//	Using this intuition, we pre-define an array dir having x and y co-ordinate changes for each direction. There are a total of 4 
//	directions as discussed in the previous approach.
//
//	The row\text{row}row and col variables represent the current x and y co-ordinates respectively. It updates based on the direction 
//	in which we are moving.
//	How do we know when we have to change the direction?
//
//	When we find the next row or column in a particular direction has a non-zero value, we are sure it is already traversed and we change the 
//	direction.
//
//	Let ddd be the current direction index. We go to next direction in array dir using (d+1)%4(d+ 1) \% 4(d+1)%4. Using this we could go
//	back to direction 1 after completing one circular traversal from direction 1 to direction 4 .
//	
	public int[][] generateMatrixSol2(int n) {
		int[][] result = new int[n][n];
		int cnt = 1;
		int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int d = 0;
		int row = 0;
		int col = 0;
		while (cnt <= n * n) {
			result[row][col] = cnt++;
			int r = Math.floorMod(row + dir[d][0], n);
			int c = Math.floorMod(col + dir[d][1], n);

			// change direction if next cell is non zero
			if (result[r][c] != 0)
				d = (d + 1) % 4;

			row += dir[d][0];
			col += dir[d][1];
		}
		return result;
	}
}
