package com.problems.java.stacks;

import java.util.Arrays;
import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class NextGreaterElement {

	public static void main(String[] args) {

		int[] arr = new int[7];

		ArrayUtils.fillRandom(arr);

		System.out.println(Arrays.toString(arr));

		System.out.println(Arrays.toString(nextGreaterRight(arr)));
	}

	static int[] nextGreaterRight(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		int[] ans = new int[arr.length];
//		Arrays.fill(arr, -1);
		for (int i = arr.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				ans[i] = -1;
			} else {
				ans[i] = arr[stack.peek()];
			}
			stack.push(i);
		}
		return ans;
	}
}
