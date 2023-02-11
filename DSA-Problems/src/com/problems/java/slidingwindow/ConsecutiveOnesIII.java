package com.problems.java.slidingwindow;

import com.problems.java.utility.ArrayUtils;

public class ConsecutiveOnesIII {

	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandomRange(arr, 0, 1);

		ArrayUtils.print(arr);

		System.out.println(longestOnes(arr, 3));

	}

	public static int longestOnes(int[] nums, int k) {
		int ans = 0;
		int zeroCount = 0;

		for (int l = 0, r = 0; r < nums.length; r++) {
			if (nums[r] == 0) {
				zeroCount++;
			}
			while (zeroCount > k) {
				if (nums[l] == 0)
					zeroCount--;
				l++;
			}
			ans = Math.max(ans, r - l + 1);
		}
		return ans;
	}
}
