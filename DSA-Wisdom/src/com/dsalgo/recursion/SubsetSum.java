package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSum {

	public static void main(String[] args) {

		int[] ar = { 2, 3 };
		ArrayList<Integer> arr = new ArrayList<>();

		for (int i : ar)
			arr.add(i);
		long start = System.currentTimeMillis();
		System.out.println(subsetSums(arr));
		System.out.println("Time take -> " + ((System.currentTimeMillis() - start) / 1000.0f) + " secs");

	}

	static ArrayList<Integer> subsetSums(ArrayList<Integer> arr) {
		ArrayList<Integer> subsetSums = new ArrayList<>();
		subsetSums(arr, 0, subsetSums, 0);
		Collections.sort(subsetSums);
		return subsetSums;
	}

	static void subsetSums(ArrayList<Integer> arr, int i, List<Integer> subsetSums, int sum) {
		if (i == arr.size()) {
			subsetSums.add(sum);
			return;
		}
		subsetSums(arr, i + 1, subsetSums, sum + arr.get(i)); // pick
		subsetSums(arr, i + 1, subsetSums, sum); // not pick
	}
}
