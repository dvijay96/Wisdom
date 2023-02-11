package com.problems.java.stacks;

import java.util.LinkedList;
import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class LargestRectangleInHistogram {

//	Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, 
//	return the area of the largest rectangle in the histogram.
//
//	Example 1:
//
//
//	Input: heights = [2,1,5,6,2,3]
//	Output: 10
//	Explanation: The above is a histogram where width of each bar is 1.
//	The largest rectangle is shown in the red area, which has an area = 10 units.

	public static void main(String[] args) {

		int[] arr = new int[7];

		ArrayUtils.fillRandom(arr);

		ArrayUtils.print(arr);

		System.out.println(largestRectangleArea(arr));

		System.out.println(largestRactangleAreaOptimized(arr));
	}

	public static int largestRactangleAreaOptimized(int[] heights) {
		LinkedList<Integer> stack = new LinkedList<>();
		int n = heights.length;
		int area = 0;

		for (int i = 0; i <= n; i++) {
			int currVal = i == n ? 0 : heights[i];
			while (!stack.isEmpty() && heights[stack.peek()] >= currVal) {
				int top = stack.pop();
				int left = stack.isEmpty() ? -1 : stack.peek(); // left boundary for top element, right boundary is
																// nothing but i
				int width = i - left - 1;
				area = Math.max(area, width * heights[top]); // finding out the area for the top element
			}
			stack.push(i);
		}
		return area;
	}

	public static int largestRectangleArea(int[] heights) {
		int n = heights.length;
		int max = 0;
		int[] left = new int[n];
		int[] right = new int[n];
		left[0] = -1;
		right[n - 1] = n;

		for (int i = 1; i < n; i++) {
			int jump = i - 1;
			while (jump >= 0 && heights[jump] >= heights[i]) {
				jump = left[jump];
			}

			left[i] = jump;
		}

		for (int i = n - 2; i >= 0; i--) {
			int jump = i + 1;
			while (jump < n && heights[jump] >= heights[i]) {
				jump = right[jump];
			}
			right[i] = jump;
		}

		for (int i = 0; i < n; i++) {
			int width = right[i] - left[i] - 1;

			max = Math.max(max, width * heights[i]);
		}

		return max;
	}

	public static long getMaxArea(long[] heights) {
		int[] leftMin = new int[heights.length];
		int[] rightMin = new int[heights.length];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < heights.length; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
				stack.pop();
			}

			leftMin[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}

		stack.clear();

		for (int i = heights.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
				stack.pop();
			}

			rightMin[i] = stack.isEmpty() ? heights.length : stack.peek();
			stack.push(i);
		}

		long area = 0;

		for (int i = 0; i < heights.length; i++) {
			long width = (i - leftMin[i]) + (rightMin[i] - i) - 1;

			area = Math.max(heights[i] * width, area);
		}

		return area;
	}
}
