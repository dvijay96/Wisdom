package com.dsalgo.bitwise;

public class SingleOccurrence {

	public static void main(String[] args) {
		int[] arr = { 6, 2, 1, 2, 4, 5, 4, 5, 6 };

		System.out.println(occurringOnce(arr));
	}

	// This algo only works when there are elements which are occurring twice except
	// for one element which is occurring
	// once.
	static int occurringOnce(int[] arr) {
		int ans = 0;
		for (int num : arr) {
			ans = ans ^ num;
		}
		return ans;
	}
}
