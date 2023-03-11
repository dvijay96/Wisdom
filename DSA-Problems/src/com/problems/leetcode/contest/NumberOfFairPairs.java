package com.problems.leetcode.contest;

import java.util.Arrays;

public class NumberOfFairPairs {

//	Given a 0-indexed integer array nums of size n and two integers lower and upper, 
//	return the number of fair pairs.
//
//	A pair (i, j) is fair if:
//
//		* 0 <= i < j < n, and
//		* lower <= nums[i] + nums[j] <= upper
//			 
//
//	Example 1:
//
//			Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
//			Output: 6
//			Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
//			
//	Example 2:
//
//			Input: nums = [1,7,9,2,5], lower = 11, upper = 11
//			Output: 1
//			Explanation: There is a single fair pair: (2,3).
	public static void main(String[] args) {
		int[] arr = { 0, 1, 7, 4, 4, 5 };

		System.out.println(countFairPairs(arr, 3, 6));

	}

//	Approach:-
//			-> Pretty straight forward to run two loops to check every possible pair and check its sum range.
//	
//		TC:- O(n^2)
	static long countFairPairsNaive(int[] nums, int lower, int upper) {
		long ans = 0;
		long low = lower;
		long high = upper;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				long sum = nums[i] + nums[j] + 0L;
				if (sum <= high && sum >= low)
					ans++;
			}
		}
		return ans;
	}

//	Approach:-
//			-> The 1st condition i.e.  0 <= i < j < n can be ignored as here we are required to select any two numbers regardless of their position,
//				becuz its not mentioned that nums[i] < nums[j], its just i<j.
//			-> Thus to optimize naive approach, we can 1st sort the array.
//			-> Once sorted, we can start looking for pairs generating at most upper and lower bound sum.
//			-> To find the the pairs, we start from 0th idx ( let say i )and try to find pair from the last idx (let say j ) val.
//			-> If at any pos^n j the pair of nums[i] + nums[j] <= upper or lower. we count its subsequent pairs as j-i for that ith element.
//			-> Similarly we try to find the pairs for next ith idx with the existing jth index and so on.
//			-> We do not reset j becuz as the array is sorted, it is clear that no number at pos greater than j can make pair that is <= lower/upper.
//			-> The final answer will be the difference of the no. of pairs <= upper and no. of pairs <= lower - 1.
//						ans = ( no. of pairs <= upper) - (no. of pairs <= lower - 1 )
//	
//		TC:- O( nLogn )
	static long countFairPairs(int[] nums, int lower, int upper) {
		Arrays.sort(nums); // n log n
		return smaller(nums, upper) - smaller(nums, lower - 1);
	}

	private static long smaller(int[] nums, int sum) {
		long res = 0;
		for (int i = 0, j = nums.length - 1; i < j; i++) {
			while (i < j && nums[i] + nums[j] > sum) // find the first pos from back where i,j sum <= lower/upper.
				j--;
			res += j - i;
		}
		return res;
	}
}
