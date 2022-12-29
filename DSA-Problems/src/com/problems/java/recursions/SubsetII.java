package com.problems.java.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetII {

	public static void main(String[] args) {
		int[] arr = { 3,1,2 };
//		System.out.println(printUniqueSubsets(arr));
//
//		Set<List<Integer>> set = new HashSet<>();
//		getUniqueSubsets(arr, 0, new ArrayList<>(), set);
//		System.out.println(set);
		
		List<List<Integer>> ans = new ArrayList<>();
		getUniqueSubsets(arr, 0, new ArrayList<>(), ans);
		System.out.println(ans);
	}

	public static ArrayList<ArrayList<Integer>> printUniqueSubsets(int[] nums) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		Set<List<Integer>> set = new HashSet<>();
		getUniqueSubsets(nums, 0, ans, new ArrayList<>(), set);
		return ans;
	}

	static void getUniqueSubsets(int[] arr, int idx, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> list,
			Set<List<Integer>> set) {
		if (set.contains(list))
			return;
		if (idx == arr.length) {
			ArrayList<Integer> subset = new ArrayList<>(list);
			ans.add(subset);
			set.add(subset);
			return;
		}
		list.add(arr[idx]);
		getUniqueSubsets(arr, idx + 1, ans, list, set);
		list.remove(list.size() - 1);
		getUniqueSubsets(arr, idx + 1, ans, list, set);
	}

	static void getUniqueSubsets(int[] arr, int i, List<Integer> list, Set<List<Integer>> set) {
		if (i >= arr.length) {
			set.add(new ArrayList<>(list));
			return;
		}
		list.add(arr[i]); // take
		getUniqueSubsets(arr, i + 1, list, set); // move for the next one
		list.remove(list.size() - 1); // remove what was taken
		getUniqueSubsets(arr, i + 1, list, set); // move for the next one
	}

	static void getUniqueSubsets(int[] arr, int idx, List<Integer> list, List<List<Integer>> ans) {
		ans.add(new ArrayList<>(list));
		for (int i = idx; i < arr.length; i++) {
			if (i > idx && arr[i] == arr[i - 1])
				continue;
			list.add(arr[i]); // take
			getUniqueSubsets(arr, i + 1, list, ans); // move for the next one
			list.remove(list.size() - 1); // remove what was taken
		}
	}
}
