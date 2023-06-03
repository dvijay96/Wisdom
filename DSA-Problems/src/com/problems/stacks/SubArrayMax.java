package com.problems.stacks;

import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class SubArrayMax {

	public static void main(String[] args) {
		int[] arr = new int[7];
		ArrayUtils.fillRandomNegPos(arr);
		ArrayUtils.print(arr);
		System.out.println(bruteForce(arr));
		System.out.println(subArrayMax(arr, arr.length));
	}

	static long bruteForce(int[] arr) {
		long sum = 0;

		for (int i = 0; i < arr.length; i++) {
			int max = arr[i];
			for (int j = i; j < arr.length; j++) {
				max = Math.max(max, arr[j]);
				sum += max;
			}
		}
		return sum;
	}

	static long subArrayMax(int[] arr, int n) {
		int[] leftMax = new int[n];
		int[] rightMax = new int[n];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				stack.pop();
			}
			leftMax[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}
		stack.clear();

		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
				stack.pop();
			}
			rightMax[i] = stack.isEmpty() ? n : stack.peek();
			stack.push(i);
		}

		long sum = 0;

		for (int i = 0; i < n; i++) {
			sum += 1L * arr[i] * (i - leftMax[i]) * (rightMax[i] - i);
		}
		return sum;
	}
}
