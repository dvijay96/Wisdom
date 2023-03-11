package com.problems.java.gfg.array.medium;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {

//	Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
//
//		In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
//
//										1					--- 0
//									  1   1					--- 1
//									1   2   1				--- 2
//								  1   3   3   1				--- 3
//								1   4   6   4   1			--- 4
//			 
//
//			Example 1:
//
//			Input: rowIndex = 3
//			Output: [1,3,3,1]
//			
//			Example 2:
//
//			Input: rowIndex = 0
//			Output: [1]
//			
//			Example 3:
//
//			Input: rowIndex = 1
//			Output: [1,1]

	public static void main(String[] args) {
		System.out.println(getRow(5));
	}

//	Approach:- 
//			-> Intuition is of prefix sum.
//			-> Backward prefix sum.
	public static List<Integer> getRow(int rowIndex) {

		int[] arr = new int[rowIndex + 1];
		arr[0] = 1;

		for (int i = 1; i <= rowIndex; i++) {
			for (int j = i; j > 0; j--) {
				arr[j] += arr[j - 1];
			}
		}

		List<Integer> res = new ArrayList<>();
		for (int a : arr) {
			res.add(a);
		}
		return res;
	}
}
