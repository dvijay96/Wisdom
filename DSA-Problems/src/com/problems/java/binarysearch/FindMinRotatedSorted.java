package com.problems.java.binarysearch;

import java.util.Scanner;

public class FindMinRotatedSorted {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
		}

		System.out.println(findMin(arr, n));

		System.out.println(findMin(arr));
		scan.close();
	}

	static int findMin(int arr[], int n) {

		int pivot = findPivot(arr);

		if (pivot != -1)
			return arr[pivot];
		else
			return arr[0];
	}

	/**
	 * Recursive
	 */
	static int findPivot(int[] arr, int low, int high) {
		if (low > high)
			return -1;

		if (low == high)
			return low;

		int mid = low + (high - low) / 2;

		if (mid < high && arr[mid] > arr[mid + 1])
			return mid + 1;
		if (mid > low && arr[mid] < arr[mid - 1])
			return mid;
		if (arr[low] >= arr[mid]) {
			return findPivot(arr, low, mid - 1);
		} else {
			return findPivot(arr, mid + 1, high);
		}
	}

	/**
	 * Iterative
	 */
	static int findPivot(int[] arr) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {

			int mid = low + (high - low) / 2;

			if (mid < high && arr[mid] > arr[mid + 1])
				return mid + 1;

			if (mid > low && arr[mid - 1] > arr[mid])
				return mid;

			if (arr[low] >= arr[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	static int findMin(int[] arr) {
		int low = 0;
		int high = arr.length - 1;

		while (low < high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] < arr[high])
				high = mid;
			else if (arr[mid] > arr[high])
				low = mid + 1;
		}
		return arr[low];
	}
}
