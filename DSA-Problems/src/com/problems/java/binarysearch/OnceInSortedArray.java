package com.problems.java.binarysearch;

import java.util.HashMap;
import java.util.Map;

public class OnceInSortedArray {

	public static void main(String[] args) {

		int[] arr = { 1, 1, 2, 2, 5, 5, 6, 7, 7 };

		System.out.println(findOnce(arr));
		System.out.println(findOnce(arr, arr.length));
		System.out.println(findOnceOptimal(arr));
	}

	static int findOnce(int[] arr) {
		int n = arr.length;

		if (n == 1)
			return arr[0];

		if (n > 2) {
			if (arr[0] != arr[1])
				return arr[0];
			if (arr[n - 1] != arr[n - 2])
				return arr[n - 1];
		}

		for (int i = 0; i < n - 1; i++) {
			if (arr[i] == arr[i + 1])
				i++;
			else
				return arr[i];
		}

		return -1;
	}

	static int findOnce(int arr[], int n) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i : arr) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1)
				return entry.getKey();
		}

		return -1;
	}

	static int findOnceOptimal(int[] arr) {
		int low = 0;
		int n = arr.length;
		int high = n - 1;

		if (n == 1)
			return arr[0];

		if (arr[low] != arr[low + 1])
			return arr[low];

		if (arr[n - 1] != arr[n - 2])
			return arr[n - 1];

		while (low < high - 1) {
			int mid = low + (high - low) / 2;

			if (mid % 2 == 0) {
				if (arr[mid] != arr[mid - 1])
					low = mid;
				else
					high = mid;
			} else {
				if (arr[mid] == arr[mid + 1])
					high = mid;
				else
					low = mid;
			}
		}
		return arr[low];
	}
}
