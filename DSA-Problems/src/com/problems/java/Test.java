package com.problems.java;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		int[] arr = new int[n];

		for (int i = 0; i < n; i++)
			arr[i] = scan.nextInt();

		System.out.println(peakElement(arr, n));
	}

	public static int peakElement(int[] arr, int n) {

		if (n == 1) {
			return 0;
		}
		if (arr[0] > arr[1]) {
			return 0;
		}
		if (arr[n - 1] > arr[n - 2]) {
			return n - 1;
		}

		int low = 0;
		int high = n - 1;

		while (low < high) {

			int mid = low + (high - low) / 2;

			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return mid;
			}
			if (arr[mid - 1] > arr[mid]) {
				high = mid - 1 ;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}
