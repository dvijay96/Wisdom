package com.problems.java.binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KthPositiveMissingNumber {

	// Given an array arr of positive integers sorted in a strictly increasing
	// order, and an integer k.

	// Return the kth positive integer that is missing from this array.

//	Example 1:
//
//		Input: arr = [2,3,4,7,11], k = 5
//		Output: 9
//		Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. 
//					 The 5th missing positive integer is 9.

//	Example 2:
//
//		Input: arr = [1,2,3,4], k = 2
//		Output: 6
//		Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

	public static void main(String[] args) {

		int[] arr = { 2, 3, 4, 7, 11 };
		int k = 7;
		
		double d = 10e-6;
		System.out.println(d);

		System.out.println(kthMissingNumberNaive(arr, k));

		System.out.println(kthMissingNumberBetter(arr, k));

		System.out.println(kthMissingNumberBetterII(arr, k));

		System.out.println(kthMissingNumberOptimal(arr, k));
	}

	// Naive approach
	// TC:- O(MlogN) , M-> range from 1 to max element in the array
	// SC:- O(1)
	private static int kthMissingNumberNaive(int[] arr, int k) {
		int i = 1;
		for (; i <= arr[arr.length - 1]; i++) {
			if (Arrays.binarySearch(arr, i) < 0) {
				k--;
			}
			if (k == 0)
				return i;
		}
		return (i - 1) + k;
	}

	// Slightly Better approach ***
	// Placing the array elements into a Set and then searching for the missing
	// number
	// this reduces the search from O(logN) to O(1).

	// TC:- O(2N) ~ O(N)
	// SC:- O(N)
	private static int kthMissingNumberBetter(int[] arr, int k) {
		Set<Integer> set = new HashSet<>();
		for (int i : arr)
			set.add(i);

		for (int i = 1; i <= arr[arr.length - 1]; i++) {
			if (!set.contains(i)) {
				k--;
			}
			if (k == 0)
				return i;
		}
		return arr[arr.length - 1] + k;
	}

	// Slightly Better approach.
	// No Need of placing the array elements into a Set and then searching for the
	// missing number.
	// This reduces the extra space to 1.

	// We assume k is the missing number and whenever we find the ith element to be
	// greater than k,
	// we return k as our answer else we increment k.

	// TC:- O(N)
	// SC:- O(1)
	private static int kthMissingNumberBetterII(int[] arr, int k) {
		if (arr[0] > k)
			return k;
		for (int i : arr) {
			if (i > k)
				return k;
			k++;
		}
		return k;
	}

	private static int kthMissingNumberOptimal(int[] arr, int k) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] - (mid + 1) >= k)
				high = mid;
			else
				low = mid + 1;
		}
		return low + k;

	}
}
