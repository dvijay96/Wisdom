package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

import com.problems.java.utility.ArrayUtils;

public class CountSubArraysWithKDistinct {

	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandomRange(arr, 1, 5);

		ArrayUtils.print(arr);

		System.out.println(subarraysWithKDistinct(arr, 2));

	}

	public static int subarraysWithKDistinct(int[] nums, int k) {
		return atmost(nums, k) - atmost(nums, k - 1);
	}

	private static int atmost(int[] arr, int k) {
		int ans = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int l = 0, r = 0; r < arr.length; r++) {
			map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);

			while (map.size() > k) {
				map.put(arr[l], map.get(arr[l]) - 1);
				if (map.get(arr[l]) == 0)
					map.remove(arr[l]);
				l++;
			}
			ans += r - l + 1;
		}
		return ans;
	}

	private static int atmostII(int[] arr, int k) {
		int ans = 0;
		int[] map = new int[2 * 1_000_1];
		for (int l = 0, r = 0; r < arr.length; r++) {
			if (map[arr[r]]++ == 0)
				k--;

			while (k < 0) {
				if (--map[arr[l]] == 0)
					k++;
				l++;
			}
			ans += r - l + 1;
		}
		return ans;
	}
}
