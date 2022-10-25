package com.problems.java.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArraySumZero {

	public static void main(String[] args) {

		LongestSubArraySumZero obj = new LongestSubArraySumZero();

		int[] arr = { 15, -2, 2, -8, 1, 7, 10, 23 };

		System.out.println(obj.maxLen(arr, arr.length));
	}

	public int maxLen(int[] arr, int n) {
		Map<Integer, Integer> map = new HashMap<>();

		int sum = 0;
		int max = 0;
		map.put(0, -1);
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if (map.containsKey(sum)) {
				int start = map.get(sum);
				int end = i;
				if (max < end - start) {
					max = end - start;
				}
			} else {
				map.put(sum, i);
			}
		}
		return max;
	}
}
