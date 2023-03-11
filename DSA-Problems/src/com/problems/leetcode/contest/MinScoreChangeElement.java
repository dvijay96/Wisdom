package com.problems.leetcode.contest;

import java.util.Arrays;

import com.problems.java.utility.ArrayUtils;

public class MinScoreChangeElement {

//	You are given a 0-indexed integer array nums.
//
//	The low score of nums is the minimum value of |nums[i] - nums[j]| over all 0 <= i < j < nums.length.
//	The high score of nums is the maximum value of |nums[i] - nums[j]| over all 0 <= i < j < nums.length.
//	The score of nums is the sum of the high and low scores of nums.
//	To minimize the score of nums, we can change the value of at most two elements of nums.
//
//	Return the minimum possible score after changing the value of at most two elements of nums.
//
//	Note that |x| denotes the absolute value of x.
//	
//	Example 1:
//
//		Input: nums = [1,4,3]
//		Output: 0
//		Explanation: Change value of nums[1] and nums[2] to 1 so that nums becomes [1,1,1]. Now, the value of |nums[i] - nums[j]| is always equal to 0, so we return 0 + 0 = 0.
//		
//	Example 2:
//
//		Input: nums = [1,4,7,8,5]
//		Output: 3
//		Explanation: Change nums[0] and nums[1] to be 6. Now nums becomes [6,6,7,8,5].
//		Our low score is achieved when i = 0 and j = 1, in which case |nums[i] - nums[j]| = |6 - 6| = 0.
//		Our high score is achieved when i = 3 and j = 4, in which case |nums[i] - nums[j]| = |8 - 5| = 3.
//		The sum of our high and low score is 3, which we can prove to be minimal.
//		 
//
//	Constraints:
//
//		3 <= nums.length <= 105
//		1 <= nums[i] <= 109

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);
		ArrayUtils.print(arr);
		System.out.println(minimizeSum(arr));
	}

//	Approach:-
//			-> We need to find the min diff between any two elements from the array and the max diff between any two elements.
//			-> And generate a score i.e score = max - min, which should be the min from this array.
//			-> But also we have the provision to change at-most 2 elements to any other value.
//			-> We need to change the 2 elements in such way that the score minimizes.
//			-> To get a min diff, we can for sure change 1 element to an existing element from the array to create duplicate
//				thus the diff will be 0. Hence the min diff will always be 0 for duplicates.
//			-> Now the problem remains with finding out the minimum max diff.
//			-> Since its the absolute diff that we are looking for, we can sort the array for better complexity than n^2.
//			-> After sorting, we know that the max diff is arr[n-1] - arr[0].
//			-> Since we can change atmost 2 elements, to minimize the max diff, we can choose & do either of the followings:
//					1. change 0 and n-1 position , thus the new max diff will be btw n-2 & 1.
//					2. change 0 and 1 pos, thus the new max diff will be btw n-1 & 2.
//					3. change n-1 and n-2 pos, thus the new max diff will be btw n-3 & 0. 
//			-> The ans will min of all above 3 diffs.
	
	public static int minimizeSum(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		return Math.min(Math.min(nums[n - 2] - nums[1], nums[n - 1] - nums[2]), nums[n - 3] - nums[0]);
	}
}
