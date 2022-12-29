package com.problems.java.binarysearch;

import java.util.Arrays;

public class PeakElementII {

	public static void main(String[] args) {

		int[][] arr = { { 1, 2, 6 }, { 3, 4, 5 } };

		for (int[] a : arr) {
			for (int i : a) {
				System.out.print(i + " ");
			}
			System.out.println();
		}

		int[] ans = findPeakGrid(arr);
		System.out.println(Arrays.toString(ans));

		int[] ans2 = findPeak(arr);

		System.out.println(Arrays.toString(ans2));
	}

	public static int[] findPeakGrid(int[][] mat) {
		int startCol = 0, endCol = mat[0].length - 1;

		while (startCol <= endCol) {
			int maxRow = 0, midCol = startCol + (endCol - startCol) / 2;

			for (int row = 0; row < mat.length; row++) {
				maxRow = mat[row][midCol] >= mat[maxRow][midCol] ? row : maxRow;
			}

			boolean leftIsBig = midCol - 1 >= startCol && mat[maxRow][midCol - 1] > mat[maxRow][midCol];
			boolean rightIsBig = midCol + 1 <= endCol && mat[maxRow][midCol + 1] > mat[maxRow][midCol];

			if (!leftIsBig && !rightIsBig) // we have found the peak element
				return new int[] { maxRow, midCol };

			else if (rightIsBig) // if rightIsBig, then there is an element in 'right' that is bigger than all
									// the elements in the 'midCol',
				startCol = midCol + 1; // so 'midCol' cannot have a 'peakPlane'

			else // leftIsBig
				endCol = midCol - 1;
		}
		return null;
	}

	/*
	 * By using binary search approach -> place two pointers on 0th col and n-1 col.
	 * -> find the max element from the mid col -> Now, this boils the problem down
	 * to 1D array peak finding on the row where we found the max element in mid
	 * col.
	 */
	public static int[] findPeak(int[][] mat) {

		int colLow = 0;
		int colHigh = mat[0].length - 1;
		int maxRow = 0;
		while (colLow <= colHigh) {
			int mid = colLow + (colHigh - colLow) / 2;
			int mid1 = mid + 1;

			for (int i = 0; i < mat.length; i++) {
				if (mat[i][mid] > mat[maxRow][mid])
					maxRow = i;
			}

			if (mat[maxRow][mid] < mat[maxRow][mid1])
				colLow = mid1;
			else
				colHigh = mid;
		}

		return new int[] { maxRow, colLow };
	}
}
