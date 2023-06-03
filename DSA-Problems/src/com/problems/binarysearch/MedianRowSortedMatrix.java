package com.problems.binarysearch;

import java.util.Arrays;

public class MedianRowSortedMatrix {

	public static void main(String[] args) {

		int[][] mat = { { 1, 3, 3 }, { 4, 5, 8 }, { 5, 6, 9 } };

		System.out.println(median(mat, 3, 3));
		System.out.println(naiveApproach(mat, 3, 3));
		
		long x = 9/2;
		System.out.println(x);
	}

	static int naiveApproach(int[][] matrix, int row, int col) {
		int[] arr = new int[row * col];
		int k = 0;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				arr[k++] = matrix[i][j];
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		return arr[arr.length / 2];
	}

	static int median(int matrix[][], int R, int C) {
		int min = matrix[0][0];
		int max = matrix[0][C - 1];

		for (int i = 1; i < R; i++) {
			if (matrix[i][0] < min) {
				min = matrix[i][0];
			}
			if (matrix[i][C - 1] > max) {
				max = matrix[i][C - 1];
			}
		}
		int desired = (R * C + 1) / 2;
		while (min < max) {
			int mid = min + (max - min) / 2;
			int count = 0;

			for (int i = 0; i < R; i++) {
				count += countLessThanEqual(matrix[i], mid);
			}

			if (count < desired) {
				min = mid + 1;
			} else {
				max = mid;
			}
		}

		return min;
	}

	static int countLessThanEqual(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] <= key) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}
}
