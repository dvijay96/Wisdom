package com.problems.java.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PerfectSum {

	public static void main(String[] args) {
		int[] arr = { 9, 7, 0, 3, 9, 8, 6, 5, 7, 6 };
		System.out.println(perfectSum(arr, 31));
		System.out.println(perfectSumII(arr, 31));
	}

	public static int perfectSumII(int[] arr, int sum) {
		Arrays.sort(arr);
		return perfectSum(arr, 0, new ArrayList<>(), sum, new HashSet<>());
	}

	public static int perfectSum(int[] arr, int sum) {
		Arrays.sort(arr);
		return perfectSum(arr, 0, new ArrayList<>(), sum);
	}

	static int perfectSum(int[] arr, int idx, List<Integer> list, int sum) {
		if (sum < 0)
			return 0;
		else if (sum == 0) {
			return 1;
		} else {
			int count = 0;
			for (int i = idx; i < arr.length; i++) {
//				if (i > idx && arr[i] == arr[i - 1])
//					continue;
				if (arr[i] <= sum) {
					list.add(arr[i]);
					count += perfectSum(arr, i + 1, list, sum - arr[i]);
					list.remove(list.size() - 1);
				}
			}
			return count;
		}
	}

	static int perfectSum(int[] arr, int idx, List<Integer> list, int sum, Set<List<Integer>> set) {
		if (idx == arr.length) {
			if (sum == 0 ) {
//				set.add(new ArrayList<>(list));
				return 1;
			}
			return 0;
		} else {
			list.add(arr[idx]);
			int left = perfectSum(arr, idx + 1, list, sum - arr[idx], set);
			list.remove(list.size() - 1);
			int right = perfectSum(arr, idx + 1, list, sum, set);
			return left + right;
		}
	}
}
