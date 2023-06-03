package com.problems.leetcode.daily.array;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.problems.java.utility.ArrayUtils;

public class NumSubseqSatisfyCondition {

//	You are given an array of integers nums and an integer target.
//
//	Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is 
//	less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
//
//	 
//
//	Example 1:
//
//	Input: nums = [3,5,6,7], target = 9
//	Output: 4
//	Explanation: There are 4 subsequences that satisfy the condition.
//	[3] -> Min value + max value <= target (3 + 3 <= 9)
//	[3,5] -> (3 + 5 <= 9)
//	[3,5,6] -> (3 + 6 <= 9)
//	[3,6] -> (3 + 6 <= 9)
//	Example 2:
//
//	Input: nums = [3,3,6,8], target = 10
//	Output: 6
//	Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
//	[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
//	Example 3:
//
//	Input: nums = [2,3,3,4,6,7], target = 12
//	Output: 61
//	Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
//	Number of valid subsequences (63 - 2 = 61).
//	 
//
//	Constraints:
//
//	1 <= nums.length <= 105
//	1 <= nums[i] <= 106
//	1 <= target <= 106
//	
	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		NumSubseqSatisfyCondition obj = new NumSubseqSatisfyCondition();

		ArrayUtils.print(arr);

		int target = ThreadLocalRandom.current().nextInt(1, 30);

		System.out.println("Target:- " + target);
		System.out.println(obj.numSubseq(arr, target));
	}

//	Approach:-
//			-> Since we are not considering returning the exact subsequence which will satisfy the condition, we can sort the array.
//			-> After sorting, we can perform similar approach like two sum to get satisfying no. of subsequences.
//			-> Since, subsequence is about pick or not pick, so between two pointers say i and j , the num of subsequences would be 
//					2^(j-i).
//			-> Keeping above calculation in mind we can place two pointers at start and end and check the condition (min+max <= target),
//				If satisfy, we can calculate the no. of subsequences, else we need to minimize the max.
//	
//	TC:- O(nLogn)
//	SC:- O(n)  could also be O(1).
	public int numSubseq(int[] nums, int target) {
		int mod = 1_000_000_000 + 7;

		int n = nums.length;
		int[] pow = new int[n];

		pow[0] = 1;

		for (int i = 1; i < n; i++) {
			pow[i] = (pow[i - 1] * 2) % mod;
		}

		int res = 0;

		Arrays.sort(nums);

		int l = 0;
		int r = n - 1;

		while (l <= r) {
			if (nums[l] + nums[r] <= target) {
				res = (res + pow[r - l]) % mod;
				l++;
			} else {
				r--;
			}
		}

		return res;
	}
}
