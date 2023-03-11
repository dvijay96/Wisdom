package com.problems.leetcode.contest;

import java.util.HashSet;
import java.util.Set;

import com.problems.java.utility.ArrayUtils;

public class MinimumImpossibleOR {

//	You are given a 0-indexed integer array nums.
//
//	We say that an integer x is expressible from nums if there exist some integers 0 <= index1 < index2 < ... < indexk 
//	< nums.length for which nums[index1] | nums[index2] | ... | nums[indexk] = x. In other words, an integer is expressible
//	if it can be written as the bitwise OR of some subsequence of nums.
//
//	Return the minimum positive non-zero integer that is not expressible from nums.
//
//	 
//
//	Example 1:
//
//		Input: nums = [2,1]
//		Output: 4
//		Explanation: 1 and 2 are already present in the array. We know that 3 is expressible, since 
//		nums[0] | nums[1] = 2 | 1 = 3. Since 4 is not expressible, we return 4.
//	
//	Example 2:
//
//		Input: nums = [5,3,2]
//		Output: 1
//		Explanation: We can show that 1 is the smallest number that is not expressible.

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(7);
		ArrayUtils.print(arr);
		System.out.println(minImpossibleOR(arr));
	}

//	Approach:- 
//			-> Let A be a binary with N bits and B be a binary with M bits. 
//			-> then, A|B will always results in a binary C which will have max(N,M) bits.
//			-> Thus by this property, we can say that performing OR on any two numbers of n & m bits will guarantee that,
//				the greater number.
//			-> Now for the problem, we have to find a number which can be created by performing OR on any number of elements
//				of the array and the resultant number is not part of the array itself.
//				i.e OR op for any subsequences of the array Elements.
//			-> Since for the problem we need to find a non zero integer, we start from 1 as the missing number.
//			-> If missing number is available in the array, that means all numbers below the missing number can be generated 
//				by performing OR btwn subsequences of array.
//			-> Thus we increment the missing number by mul by 2. Since 
//					0 - 3 => OR op results in max 2 bits
//					4 - 6 => Or op result in max 3 bits.
//					ans so on.
//					
//					ex:-  2 | 3 = 3		(     1 1 )
//						  4 | 6 = 6		(   1 1 0 )
//						  4 | 7 = 7		(   1 1 1 )
//						  and
//						  5 | 8 = 13	( 1 1 0 1 )

	public static int minImpossibleOR(int[] nums) {
		Set<Integer> set = new HashSet<>();

		for (int n : nums) {
			set.add(n);
		}

		int missingNumber = 1;

		while (set.contains(missingNumber)) {
			missingNumber *= 2; // missingNumber = missingNumber << 1;
		}
		return missingNumber;
	}
}
