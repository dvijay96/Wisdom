package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class Subset {

	public static void main(String[] args) {
		Subset obj = new Subset();

		int[] arr = { 1,4,5,2,3,6,7 };

		long start = System.currentTimeMillis();
		System.out.println(obj.generateSubsets(arr));
		System.out.println("Time Taken = " + ((System.currentTimeMillis() - start) / 1000.0f) + "secs");

		start = System.currentTimeMillis();
		System.out.println(obj.generateSubsetsII(arr));
		System.out.println("Time Taken = " + ((System.currentTimeMillis() - start) / 1000.0f) + "secs");
	}

	List<List<Integer>> generateSubsets(int[] arr) {
		List<List<Integer>> ans = new ArrayList<>();
		subsets(arr, ans, new ArrayList<>(), 0);
		return ans;
	}

	private void subsets(int[] arr, List<List<Integer>> ans, List<Integer> list, int idx) {
		if (idx == arr.length) {
			ans.add(new ArrayList<>(list));
		} else {
			list.add(arr[idx]); // pick
			subsets(arr, ans, list, idx + 1);
			list.remove(list.size() - 1); // not pick
			subsets(arr, ans, list, idx + 1);
		}
	}

	List<List<Integer>> generateSubsetsII(int[] arr) {
		List<List<Integer>> ans = new ArrayList<>();
		subsetsII(arr, ans, new ArrayList<>(), 0);
		return ans;
	}

	// DFS approach
	private void subsetsII(int[] arr, List<List<Integer>> ans, List<Integer> list, int idx) {
		ans.add(new ArrayList<>(list));
		for (int i = idx; i < arr.length; i++) {
			list.add(arr[i]); // pick
			subsets(arr, ans, list, i + 1);
			list.remove(list.size() - 1); // not pick
		}
	}

}
