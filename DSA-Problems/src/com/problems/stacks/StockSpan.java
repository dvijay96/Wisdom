package com.problems.stacks;

import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class StockSpan {

//	The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate the span of stocks price for all n days. 
//	The span Si of the stocks price on a given day i is defined as the maximum number of consecutive days just before the given day, for which the price of the stock on the current day is less than or equal to its price on the given day.
//	For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}.
//
//	Example 1:
//
//	Input: 
//	N = 7, price[] = [100 80 60 70 60 75 85]
//	Output:
//	1 1 1 2 1 4 6
//	Explanation:
//	Traversing the given input span for 100 
//	will be 1, 80 is smaller than 100 so the 
//	span is 1, 60 is smaller than 80 so the 
//	span is 1, 70 is greater than 60 so the 
//	span is 2 and so on. Hence the output will 
//	be 1 1 1 2 1 4 6.

	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandom(arr);

		ArrayUtils.print(arr);

		int[] ans = calculateSpan(arr, arr.length);

		ArrayUtils.print(ans);
	}

	/*
	 *  Idea is to find the next greater element on the left and then calculate 
	 *  till how preceding nums are smaller than current ith num.
	 */
	public static int[] calculateSpan(int[] price, int n) {

		int[] ans = new int[n];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < price.length; i++) {
			while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
				stack.pop();
			}

			int prev = stack.isEmpty() ? -1 : stack.peek();

			ans[i] = i - prev;

			stack.push(i);
		}
		return ans;
	}
}
