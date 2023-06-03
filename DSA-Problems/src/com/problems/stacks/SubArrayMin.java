package com.problems.stacks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class SubArrayMin {

	public static void main(String[] args) {
		int[] arr = new int[5];

		ArrayUtils.fillRandom(arr);

		System.out.println(Arrays.toString(arr));

		System.out.println(sumSubarrayMins(arr.length, arr));
	}

	public static int sumSubarrayMins(int n, int[] arr) {
		int[] leftMin = new int[n];
		int[] rightMin = new int[n];

		Stack<Integer> stack = new Stack<>();

		for (int i = arr.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				rightMin[i] = n;
			} else {
				rightMin[i] = stack.peek();
			}
			stack.push(i);
		}

		stack.clear();

		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				leftMin[i] = -1;
			} else {
				leftMin[i] = stack.peek();
			}
			stack.push(i);
		}

		long sum = 0;
		int mod = 1_000_000_007;

		for (int i = 0; i < arr.length; i++) {
			long left = i - leftMin[i];
			long right = rightMin[i] - i;

			sum += (arr[i] * left * right);
		}

		return (int) (sum % mod);
	}

	
//	Converting the above 2 pass algo into 1 pass algo
//	
//	Idea is same as finding the leftMin and rightMin, but a bit diff
//	
// idx->  0  1  2  3  4  5  6	
//	ex: { 2, 8, 3, 7, 4, 3, 4 }
	
//	considering the boundary of the curr element's PLE ( Previous Least Element ) & NLE ( Next Least Element ) positions.

//	At any point of time the stack will contains elements in a increasing order from the bottom.
//	If a new element comes & is smaller than the top element,
//	we can definitely tell that the NLE for top is curr element.
//	and the PLE for the top would be the next bottom element in the stack after it.
	
//	Thus, the left boundary for the popped(this) out element would be the new top of the stack, telling us till which point this element was min.
//	And right boundary is curr element, telling us till which point on right this element was min.
//	Let say, 
//		for above array, when calculating PLE for index 2, the stack at that time would look like.
//	
//				2 | 8
//	
//	Then we could tell for 8 (top) the NLE is 3 and PLE is 2.
//	8 would be popped out and 3 would be pushed into the stack.
//	
//				2 | 3
	public static int sumSubarrayMins(int[] arr) {
		LinkedList<Integer> stack = new LinkedList<>();
		int n = arr.length;
		long sum = 0;

		for (int i = 0; i <= n; i++) {
			int currVal = i == n ? 0 : arr[i];
			while (!stack.isEmpty() && arr[stack.peek()] >= currVal) {
				int top = stack.pop();
				int left = stack.isEmpty() ? -1 : stack.peek();			// left boundary for top element, right boundary is nothing but i 
				sum += 1L * (top - left) * (i - top) * arr[top];		// finding out the sum for the top element
			}
			stack.push(i);
		}

		return (int) (sum % 1_000_000_007);
	}

	
//	ex:- {3,1,2,4}
//	
//	index	| result (Min sum)	|	sub arrays

//	 0		|	  3				|	{3}

//	 1		|	1 + 1			|	{3,1}	{1}

//	 2		|	1 + 1 + 2		|	{3,1,2} {1,2} {2}

//	 3		|	1 + 1 + 2 + 4	|	{3,1,2,4} {1,2,4} {2,4} {4}

//	So if we see at result[2], which is nothing but result[1] + (no. of subArrays where arr[i] is min on the left) * arr[i]
//	similarly, 
//				result[3] = result[2] + (no. of subArrays where arr[i] is min on the left) * arr[i]

//	So a generalized formula would be 

//		result[i] = result[leftMin[i]] + ( i - leftMin[i] ) * arr[i];

	public int sumSubArrayMins(int[] arr) {
		int n = arr.length;
		int[] leftMin = new int[n];
		long[] result = new long[n];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				stack.pop();
			}

			leftMin[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}

		long sum = 0;
		int mod = 1_000_000_007;

		for (int i = 0; i < n; i++) {
			long prevSum = leftMin[i] == -1 ? 0 : result[leftMin[i]];

			result[i] = prevSum + (i - leftMin[i]) * arr[i];
			sum += result[i];
		}
		return (int) (sum % mod);
	}
}
