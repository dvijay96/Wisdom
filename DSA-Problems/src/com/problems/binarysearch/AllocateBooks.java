package com.problems.binarysearch;

public class AllocateBooks {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		System.out.println(findPages(arr, 5));
	}

	public static int findPages(int[] A, int M) {
		// Your code here

		int low = A[0];
		int high = 0;

		for (int a : A) {
			low = Math.max(a, low);
			high += a;
		}
//		int res = -1;
		while (low < high) {
			int mid = low + (high - low) / 2;

			int allocatedStudents = getAllocatedStudents(mid, A);

			if (allocatedStudents > M) {
//				res = mid;
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	static int getAllocatedStudents(int capacity, int[] arr) {
		int allocatedStuds = 1;
		int pages = 0;

		for (int a : arr) {
			pages += a;
			if (pages > capacity) {
				pages = a;
				allocatedStuds++;
			}
		}

		return allocatedStuds;
	}
}
