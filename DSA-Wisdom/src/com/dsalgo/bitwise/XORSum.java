package com.dsalgo.bitwise;

import java.util.Arrays;

public class XORSum {

// 	1. Given n, print XOR of all integer between 1 to n.

//	2. You are given two integers L and R, your task is to find the XOR of elements of the range [L, R].
//
//		Example:
//
//		Input: 
//			L = 4, R = 8 
//		Output:
//			8 
//		Explanation:
//			4 ^ 5 ^ 6 ^ 7 ^ 8 = 8

	public static void main(String[] args) {

//		int n = 1_095_000_689;
//		
//		long start = System.currentTimeMillis();
//		System.out.println(getXorSumNaive(n));
//		System.out.println("Time Taken = " + ((System.currentTimeMillis() - start) / 1000.0f) + "secs");
//		
//		start = System.currentTimeMillis();
//		System.out.println(getXorSumOptimized(n));
//		System.out.println("Time Taken = " + ((System.currentTimeMillis() - start) / 1000.0f) + "secs");

		int ans = 0;
		int[] arr = { 0 };
		for (int i = 3; i <= 11; i++) {
			ans ^= i;
			arr[0] = ans;
			System.out.println(i + " -> \t" + Arrays.toString(arr));
		}
	}

	// TC:- O(n)
	static int getXorSumNaive(int n) {
		int ans = 0;

		for (int i = 1; i <= n; i++)
			ans ^= i;
		return ans;
	}

	static int getXorSumOptimized(int n) {
		if (n % 4 == 0)
			return n;
		else if (n % 4 == 1)
			return 1;
		else if (n % 4 == 2)
			return n + 1;
		else
			return 0;
	}
}
