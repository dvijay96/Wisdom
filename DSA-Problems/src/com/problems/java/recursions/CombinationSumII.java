package com.problems.java.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CombinationSumII {
//	Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
//
//	Each number in candidates may only be used once in the combination.
//
//	Note: The solution set must not contain duplicate combinations.
//
//	 
//
//	Example 1:
//
//	Input: candidates = [10,1,2,7,6,1,5], target = 8
//	Output: 
//	[
//	[1,1,6],
//	[1,2,5],
//	[1,7],
//	[2,6]
//	]
	public static void main(String[] args) {
		int[] arr = { 1, 2, 2 };
		int target = 30;
		Arrays.sort(arr);
		System.out.println(arr.length);
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println();
		long start;
//		Set<List<Integer>> ans = new LinkedHashSet<>();
//		start = System.currentTimeMillis();
//		combinationSumII(arr, 0, ans, new ArrayList<>(), target);
//		System.out.println("Time Taken : " + ((System.currentTimeMillis() - start) / 1000.0f) + " secs");
//		System.out.println(ans);
//		List<List<Integer>> ans2 = new ArrayList<>();
//		start = System.currentTimeMillis();
//		combinationSumII(arr, 0, ans2, new ArrayList<>(), target, new HashSet<>());
//		System.out.println("Time Taken : " + ((System.currentTimeMillis() - start) / 1000.0f) + " secs");
//		System.out.println(ans2);

		List<List<Integer>> ans3 = new ArrayList<>();
		start = System.currentTimeMillis();
		combinationSumII(arr, 0, ans3, new ArrayList<>(), target);
		System.out.println("Time Taken : " + ((System.currentTimeMillis() - start) / 1000.0f) + " secs");
		System.out.println(ans3);
	}

	static void combinationSumII(int[] arr, int idx, List<List<Integer>> ans, List<Integer> list, int target) {

		if (target == 0) {
			List<Integer> temp = new ArrayList<>(list);
			ans.add(temp);
			return;
		}

		for (int i = idx; i < arr.length && arr[i] <= target; i++) {
			if (i > idx && arr[i] == arr[i - 1])
				continue;
			list.add(arr[i]);
			combinationSumII(arr, i + 1, ans, list, target - arr[i]);
			list.remove(list.size() - 1);
		}
	}

	static void combinationSumII(int[] arr, int idx, List<List<Integer>> ans, List<Integer> list, int target,
			Set<List<Integer>> set) {

		if (target == 0 && !set.contains(list)) {
			List<Integer> temp = new ArrayList<>(list);
			ans.add(temp);
			set.add(temp);
			return;
		}

		while (idx < arr.length && arr[idx] <= target) {
			list.add(arr[idx]);
			combinationSumII(arr, idx + 1, ans, list, target - arr[idx], set);
			idx++;
			list.remove(list.size() - 1);
		}
	}

	static void combinationSumII(int[] arr, int idx, Set<List<Integer>> ans, List<Integer> list, int target) {
		if (idx == arr.length) {
			if (target == 0) {
				ans.add(new ArrayList<>(list));
			}
			return;
		}
		list.add(arr[idx]);
		combinationSumII(arr, idx + 1, ans, list, target - arr[idx]);
		list.remove(list.size() - 1);
		combinationSumII(arr, idx + 1, ans, list, target);
	}
}
