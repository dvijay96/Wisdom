package com.problems.binarysearch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SplitArrayLargetSum {

	// Given an integer array nums and an integer k, split nums into k non-empty
	// subarrays such that the largest sum of any subarray is minimized.
//Return the minimized largest sum of the split.

	// Split the array into k parts
	// From all possible combinations of spliced k subarrays, Find the least of all
	// max subarray sum from each.

	// EX: nums = [7,2,5,10,8], k = 2
	// possible sub arrays
	// {7} & {2,5,10,8} max = 25
	// {7,2} & {5,10,8} max = 23
	// {7,2,5} & {10,8} max = 18
	// {7,2,5,10} & {8} max = 24
	//
	// Answer :- Least among all max sub-array sum is 18.

	// EX: nums = [7,2,5,10,8], k = 1
	// possible sub arrays
	// {7,2,5,10,8} max = 32
	//
	// Since k = 1 , only one possible way is the whole array, so answer is total
	// sum.

	// EX: nums = [7,2,5,10,8], k = 5
	// possible sub arrays
	// {7} & {2} & {5} & {10} & {8} max = 10
	//
	// Since k = 5 i.e arr.length , only one possible way is to divide the array
	// elements,
	// so answer will be max from array elements.

	public static void main(String[] args) {
		int[] arr = { 5, 8, 2, 4, 1 };
		int k = 2;
		int minMaxSplitSum = splitArray(arr, k);
		System.out.println(minMaxSplitSum);
		System.out.println(subArraysMap.get(minMaxSplitSum));
		subArraysMap.entrySet().forEach(System.out::println);
	}

	// when k is between 1 to arr.length,
	// answer lies between max element to total sum of elements resp.
	// hence binary search could be applied to derive answer.
	// TC : O(NlogM)
	private static int splitArray(int[] nums, int k) {

		int sum = 0;
		int max = 0;

		for (int i : nums) {
			sum += i;
			if (i > max)
				max = i;
		}

		if (k == nums.length)
			return max;
		if (k == 1)
			return sum;

		int low = max;
		int high = sum;

		while (low < high) {
			int mid = low + (high - low) / 2;

			if (isKSplitPossible(nums, k, mid))
				high = mid;
			else
				low = mid + 1;
		}
		return low;
	}

	private static boolean isKSplitPossible(int[] arr, int k, int subMax) {
		int sum = 0;
		int subArrCount = 1;

		List<List<Integer>> subArrays = new ArrayList<>();
		List<Integer> subArray = new ArrayList<>();
		for (int i : arr) {
			sum += i;
			subArray.add(i);
			if (sum > subMax) {
				subArrCount++;
				subArray.remove(subArray.size() - 1);
				subArrays.add(subArray);

				sum = i;
				subArray = new ArrayList<>();
				subArray.add(i);
			}
		}
		subArrays.add(subArray);

		subArraysMap.put(subMax, subArrays);
		return subArrCount <= k;
	}

	private static Map<Integer, List<List<Integer>>> subArraysMap = new LinkedHashMap<>();
}
