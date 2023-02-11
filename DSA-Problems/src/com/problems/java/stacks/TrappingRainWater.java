package com.problems.java.stacks;

import com.problems.java.utility.ArrayUtils;

public class TrappingRainWater {

//	Given n non-negative integers representing an elevation map where the width of each bar is 1, 
//	compute how much water it can trap after raining.

//	Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//	Output: 6
//	Explanation: 
//	
//	  									   ___
//	  					   ___            |   |___     ___
//				   ___    |   |_*_  *  _*_|   |   |_*_|   |___
//				  |   | * |   |   | * |   |   |   |   |   |   |
//				0	1	0	2	1	0	1	3	2	1	2	1
//	
//										   __
//				The above elevation map ( |  | section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
//				In this case, 6 units of rain water (* section) are being trapped.
	
	
//	Input: height = {2, 5, 3, 4, 3, 2, 6}
//	Output: 8
//	Explanation: 
//
//	
//
//									   ___
//				   ___				  |   |
//				  |   | *  _*_ 	*	* |   |
//	 			  |   |_*_|   |_*_  * |   |
//			   ___|   |   |   |   |_*_|   |
//	 		  |   |   |   |   |   |   |   |
//			  |	  |   |   |   |   |   |   |
//				2	5	3	4	3	2	6
//	
//	
	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandom(arr);

		ArrayUtils.print(arr);

		System.out.println(trap(arr));
		System.out.println(trapI(arr));
	}

//	Approach:-
//			At each point, find the leftMax & the rightMax to the current num.
//			The amount of water that would be stored on top of current wall is  Min ( leftMax, rightMax ) - curr. 
	// Brute force
	// O(N^2)
	static int trapI(int[] arr) {
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int leftMax = arr[i];
			for (int j = i; j >= 0; j--) {
				leftMax = Math.max(arr[j], leftMax);
			}

			int rightMax = arr[i];

			for (int j = i; j < arr.length; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}

			res += Math.min(leftMax, rightMax) - arr[i];

		}
		return res;
	}

	// Optimal solution
//      O(N)
	public static int trap(int[] height) {
		long count = 0;
		int left = 0;
		int right = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;

		while (left <= right) {
			if (height[left] <= height[right]) {
				if (height[left] > leftMax) {
					leftMax = height[left];
				} else {
					count += leftMax - height[left];
				}
				left++;
			} else {
				if (height[right] > rightMax) {
					rightMax = height[right];
				} else {
					count += rightMax - height[right];
				}
				right--;
			}
		}
		return (int) (count % Integer.MAX_VALUE);
	}
}
