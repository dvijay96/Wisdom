package com.problems.java.binarysearch;

public class SearchInsertPos {

	public static void main(String[] args) {

		int[] arr = { 1, 3, 5, 6 };

		System.out.println(searchInsertK(arr, arr.length, 4));

	}

	static int searchInsertK(int[] arr, int n, int k) {
		int low = 0;
		int high = n - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == k)
				return mid;
			if (arr[mid] < k) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}
}
