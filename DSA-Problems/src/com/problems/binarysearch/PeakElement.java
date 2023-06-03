package com.problems.binarysearch;

/*
 * An array element is a peak if it is greater than its neighbors.
 * 
 * Ex:- 
 * 	Input: array[] = {10, 20, 15, 2, 23, 90, 67}
	Output: 20 or 90
	The element 20 has neighbors 10 and 15, 
	both of them are less than 20, similarly 90 has neighbors 23 and 67.
 */
public class PeakElement {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5 };

		int i = naiveApproach(arr);
		System.out.println(i + " -> " + arr[i]);
		i = optimalApproach(arr);
		System.out.println(i + " -> " + arr[i]);
		i = findPeakElement(arr);
		System.out.println(i + " -> " + arr[i]);

	}

	// TC : O(n)
	static int naiveApproach(int[] arr) {
		int n = arr.length;
		if (n == 1)
			return 0;
		int i = 0;
		for (i = 0; i < n; i++) {
			if (i == 0 && arr[i] > arr[i + 1])
				break;
			if (i == n - 1 && arr[i] > arr[i - 1])
				break;
			if (i > 0 && arr[i] > arr[i - 1] && arr[i] > arr[i + 1])
				break;
		}
		return i;
	}

	// TC: O(logn)
	static int optimalApproach(int[] arr) {
		int n = arr.length;
		if (n == 1)
			return 0;
		int low = 0;
		int high = arr.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;

			if ((mid == 0 || arr[mid] > arr[mid - 1]) && (mid == n - 1 || arr[mid] > arr[mid + 1]))
				break;

			else if (mid > 0 && arr[mid - 1] > arr[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
		return mid;
	}

	// TC : O(logn)
	static int findPeakElement(int[] num) {
		int low = 0;
		int high = num.length - 1;

		if (num.length == 1)
			return 0;
		while (low < high) {
			int mid1 = (low + high) / 2;
			int mid2 = mid1 + 1;
			if (num[mid1] < num[mid2])
				low = mid2;
			else
				high = mid1;
		}
		return low;
	}
}
