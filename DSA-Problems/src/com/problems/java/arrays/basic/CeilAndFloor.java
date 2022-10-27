package com.problems.java.arrays.basic;

import java.util.Arrays;

public class CeilAndFloor {

	public static void main(String[] args) {

		int[] arr = { 5, 6, 8, 9, 6, 5, 5, 6 };

		int x = 7;

		int[] ans = getFloorAndCeil(arr, arr.length, x);

		System.out.println(Arrays.toString(ans));
	}

	static int[] getFloorAndCeil(int[] arr, int n, int x) {

		int diff = Integer.MAX_VALUE;
		int floor = -1;

		for (int i : arr) {
			if (i <= x && x - i < diff) {
				floor = i;
				diff = x - i;
			}
		}

		diff = Integer.MAX_VALUE;
		int ceil = -1;
		for (int i : arr) {
			if (i >= x && i - x < diff) {
				ceil = i;
				diff = i - x;
			}
		}

		return new int[] { floor, ceil };
	}
}
