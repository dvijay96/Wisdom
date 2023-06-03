package com.problems.leetcode.daily.array;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;

public class LongestIncreasingSubsequence {

//	Given an integer array nums, return the length of the longest strictly increasing 
//			subsequence.
//
//			Example 1:
//
//			Input: nums = [10,9,2,5,3,7,101,18]
//			Output: 4
//			Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//			Example 2:
//
//			Input: nums = [0,1,0,3,2,3]
//			Output: 4
//			Example 3:
//
//			Input: nums = [7,7,7,7,7,7,7]
//			Output: 1

	public static void main(String[] args) {

		int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };// ArrayUtils.getIntArray(3);

		ArrayUtils.print(arr);

		LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();

//		System.out.println(obj.getAllSubsequences(arr));

		System.out.println(obj.bruteForceSol(arr));

		System.out.println(obj.optimalSol(arr));
	}

//	Approach:-
//			-> Generate all subsequences and check which subsequence is having the longest increasing sequence.
//			
//	TC:- O(2^n)
//	SC:- O(2^n)
	private int bruteForceSol(int[] arr) {
		List<List<Integer>> subSeqs = getAllSubsequences(arr);
		int maxLen = 0;

		for (List<Integer> subseq : subSeqs) {
			for (int i = 1; i < subseq.size(); i++) {
				if (subseq.get(i - 1) < subseq.get(i)) {
					maxLen = Math.max(maxLen, i + 1);
				} else {
					break;
				}
			}
		}
		return maxLen;
	}

	private List<List<Integer>> getAllSubsequences(int[] arr) {
		List<List<Integer>> subseq = new ArrayList<>();
		generateSubseq(arr, subseq, new ArrayList<>(), 0);
		return subseq;
	}

	private void generateSubseq(int[] arr, List<List<Integer>> subseq, List<Integer> list, int i) {
		if (i == arr.length) {
			subseq.add(new ArrayList<>(list));
		} else {
			list.add(arr[i]);

			generateSubseq(arr, subseq, list, i + 1);

			list.remove(list.size() - 1);

			generateSubseq(arr, subseq, list, i + 1);
		}

	}

//	Approach:-
//			-> To find longest increasing subsequence, we need to generate such subsequence in which each adjacent element
//				is in strictly increasing order.
//			-> To do this, we can consider the brute force approach where we generate all subsequences by pick or not pick method.
//			-> Similarly, when we pick a element for the subsequence, we need to check if adding that element to the 
//				existing subsequence satisfies the condition or not.
//			-> If satisfies, then add the element else find the pos where it must be put into the subsequence so to satisfy the condition
//			-> Thus, we'll be creating just the required subsequence rather than creating all subsequences.
//			-> Since, the subsequence we are generating is sorted, we can find the pos of the incoming element via binary search to reduce time.
//	
//	TC:- O(nLogn)
//	SC:- O(n)

	public int optimalSol(int[] nums) {

		List<Integer> list = new ArrayList<>();

		int ans = 0;

		for (int i = 0; i < nums.length; i++) {
			if (list.isEmpty() || list.get(list.size() - 1) < nums[i]) {
				list.add(nums[i]);
				ans = Math.max(list.size(), ans);
			} else {
				int pos = upperBound(list, nums[i]);
				ans = Math.max(ans, pos + 1);
				list.set(pos, nums[i]);
			}
		}

		System.out.println("Req Subseq:- " + list);
		return ans;
	}

	int upperBound(List<Integer> list, int num) {
		int l = 0;
		int h = list.size() - 1;

		while (l < h) {
			int mid = l + (h - l) / 2;

			if (list.get(mid) >= num) {
				h = mid;
			} else {
				l = mid + 1;
			}
		}

		return l;
	}
}
