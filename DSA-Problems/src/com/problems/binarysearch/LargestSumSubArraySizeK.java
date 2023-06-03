package com.problems.binarysearch;

public class LargestSumSubArraySizeK {

	public static void main(String[] args) {

		long[] arr = { -4, -2, 1, -3 };

		System.out.println(maxSumWithK(arr, 0, 2));
	}

	public static long maxSumWithK(long arr[], long n, long k) {

		int[] maxSubSum = new int[arr.length];
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			maxSubSum[i] = sum;
			if (sum < 0)
				sum = 0;
		}

		long exactKSum = 0;
		long max = Long.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			exactKSum += arr[i];
		}

		if (exactKSum > max)
			max = exactKSum;
		for (int i = (int) k; i < arr.length; i++) {
			exactKSum += arr[i] - arr[i - (int) k];
			long atleastKSum = exactKSum + maxSubSum[i - (int) k];
			if (exactKSum > max)
				max = exactKSum;
			if (atleastKSum > max)
				max = atleastKSum;
		}

		return max;
	}
}
