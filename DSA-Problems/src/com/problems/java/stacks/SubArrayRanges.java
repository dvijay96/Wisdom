package com.problems.java.stacks;

import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class SubArrayRanges {
//	Given an integer array arr of size N. The Range of a subarray of arr is the difference between the largest and smaller element in the subarray.  
//	Return the sum of all subarray ranges of arr.
//
//	Example 1:
//
//	Input:
//	N = 3
//	arr[ ] = {1, 2, 3}
//	Output: 4
//	Explanation: The 6 subarrays of arr are the following :
//	{1 } , range = largest - smallest = 1 - 1 = 0 
//	{2 } , range = 2 - 2 = 0
//	{3 } , range = 3 - 3 = 0
//	{1, 2}, range = 2 - 1 = 1
//	{2, 3}, range = 3 - 2 = 1
//	{1, 2, 3}, range = 3 - 1 = 2
//	sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4

	public static void main(String[] args) {
		int[] arr = new int[7];
		
		ArrayUtils.fillRandom(arr);
		
		ArrayUtils.print(arr);

		System.out.println(subarrayRanges(arr.length, arr));
	}

//	sum ( sub array max - min ) = sum [subArray max] - sum [subArray min]
	public static long subarrayRanges(int N, int[] arr) {
		return subArrayMax(arr, N) - subArrayMin(arr, N);
	}

	static long subArrayMin(int[] arr, int n) {
		int[] leftMin = new int[n];
		int[] rightMin = new int[n];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				stack.pop();
			}
			leftMin[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}
		stack.clear();

		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}
			rightMin[i] = stack.isEmpty() ? n : stack.peek();
			stack.push(i);
		}

		long sum = 0;

		for (int i = 0; i < n; i++) {
			sum += 1L * arr[i] * (i - leftMin[i]) * (rightMin[i] - i);
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
