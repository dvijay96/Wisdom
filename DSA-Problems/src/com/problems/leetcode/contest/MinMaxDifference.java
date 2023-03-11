package com.problems.leetcode.contest;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MinMaxDifference {

//	You are given an integer num. You know that Danny Mittal will sneakily remap one of the 10 possible digits (0 to 9) 
//	to another digit.
//
//	Return the difference between the maximum and minimum values Danny can make by remapping exactly one digit in num.

//	Example 1:
//
//		Input: num = 11891
//		Output: 99009
//		Explanation: 
//		To achieve the maximum value, we can remap the digit 1 to the digit 9 to yield 99899.
//		To achieve the minimum value, we can remap the digit 1 to the digit 0, yielding 890.
//		The difference between these two numbers is 99009.
//		
//	Example 2:
//
//		Input: num = 90
//		Output: 99
//		Explanation:
//		The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that 
//		can be returned by the function is 0 (if 9 is replaced by 0).
//		Thus, we return 99.

	public static void main(String[] args) {
		int num = ThreadLocalRandom.current().nextInt(1, 1_000_000_00);

		System.out.println(num);
		System.out.println(minMaxDifference(num));
	}

	public static int minMaxDifference(int num) {
		int[] arr = new int[9];
		Arrays.fill(arr, -1);

		int i = arr.length - 1;
		while (num > 0) {
			arr[i--] = num % 10;
			num /= 10;
		}
		++i;
		int high = -1;
		int low = -1;
		while (i < arr.length) {
			if (high == -1 && arr[i] != 9) {
				high = arr[i];
			}
			if (low == -1 && arr[i] != 0) {
				low = arr[i];
			}
			if (high != -1 && low != -1) {
				break;
			}
			i++;
		}

		int mul = 1;
		int max = 0;
		int min = 0;
		// System.out.println(Arrays.toString(arr));
		// System.out.println("High: "+high);
		// System.out.println("Low: "+low);
		for (i = arr.length - 1; i >= 0 && arr[i] != -1; i--) {
			if (arr[i] == high) {
				max += 9 * mul;
			} else {
				max += arr[i] * mul;
			}
			if (arr[i] == low) {
				min += 0 * mul;
			} else {
				min += arr[i] * mul;
			}
			mul = mul * 10;
		}
		// System.out.println(max);
		// System.out.println(min);
		return max - min;
	}

}
