package com.problems.binarysearch;

import java.util.Arrays;

public class AggressiveCows {

//	Problem Statement: 
//			There is a new barn with N stalls and C cows. 
//			The stalls are located on a straight line at positions x1,….,xN (0 <= xi <= 1,000,000,000). 
//			We want to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. 
//			What is the largest minimum distance?
//
//			Examples:
//
//			Input: No of stalls = 5 
//			       Array: {1,2,8,4,9}
//			       And number of cows: 3
//
//			Output: One integer, the largest minimum distance 3

	public static void main(String[] args) {

		int[] stalls = { 1, 2, 8, 4, 9 };
		int cows = 3;

		System.out.println(largestMinDist(stalls, cows));
	}

//	Problem statement :- To place C cows in diff pos among N stalls. In doing so, find out
//			In all possible combinations of placing C cows , what is the min distance between the cows in that 
//			 combination and out of all such combinations min distance, what is the largest distance among them (min distances).
	private static int largestMinDist(int[] stalls, int cows) {
		Arrays.sort(stalls); // O(NlogN)
		int low = 1;
		int high = stalls[stalls.length - 1] - stalls[0];

		while (low <= high) { // logM
			int mid = low + (high - low) / 2;

			if (isMinPossible(stalls, cows, mid)) // N
				low = mid + 1;
			else
				high = mid - 1;
		}
		return high;
	}

	private static boolean isMinPossible(int[] stalls, int cows, int mid) {
		int count = 1;
		int lastCowPos = stalls[0];

		for (int i = 1; i < stalls.length; i++) {
			if (stalls[i] - lastCowPos >= mid) {
				count++;
				lastCowPos = stalls[i];
			}
		}
		if (count >= cows)
			return true;
		return false;
	}

}
