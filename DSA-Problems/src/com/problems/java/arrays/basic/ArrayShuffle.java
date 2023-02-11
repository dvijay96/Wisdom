package com.problems.java.arrays.basic;

import java.util.Arrays;

import com.problems.java.utility.ArrayUtils;

public class ArrayShuffle {

//	Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
//
//	Return the array in the form [x1,y1,x2,y2,...,xn,yn].
//
//	Example 1:
//
//	Input: nums = [2,5,1,3,4,7], n = 3
//	Output: [2,3,5,4,1,7] 
//	Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
//	Example 2:
//
//	Input: nums = [1,2,3,4,4,3,2,1], n = 4
//	Output: [1,4,2,3,3,2,4,1]
//	Example 3:
//
//	Input: nums = [1,1,2,2], n = 2
//	Output: [1,2,1,2]
//	
//	Constraints:
//
//	1 <= n <= 500
//	nums.length == 2n
//	1 <= nums[i] <= 10^3

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);
		ArrayUtils.print(arr);

		System.out.println("Approah 1: Out place  " + Arrays.toString(shuffle(arr, arr.length / 2)));
		System.out.println("Aproach 2: In Place   " + Arrays.toString(shuffleInPlace(arr, arr.length / 2)));
		ArrayUtils.print(arr);
	}

//	BASIC IDEA:
//	Store the pair of numbers and then
//	Retrieve each number, from that pair where they were stored, one by one and place them at their desired positions.
//	EXPLANATION:
//
//		Let's understand in general term:
//
//		Suppose we have 2 numbers num1 = 4 and num2 = 9 and maxValue = 10
//		Formula:
//		to store -> pair = (num2 × maxValue) + num1
//		to retrieve -> pair % maxValue and pair / maxValue
//
//		Storing the pair of numbers
//
//		(9 × 10) + 4 = 94 --> from above formula (num2 × maxValue) + num1
//		94 is stored
//
//		Retrieving each number one by one
//
//		94 % 10 = 4
//		we got first number as 4
//
//		94 / 10 = 9
//		we got second number as 9
//
//		Same idea goes for large cases and array as well but instead of 10, we will take 1024 
//		to store pairs and retrieve each number
//		NOTE: Here, we will take 1024 because of the given constraints 1 <= nums[i] <= 10^3 i.e. the largest number 
//		in the array will be 1000
//
//		Now,
//		Binary representation of 1000 is 1111101000, consisting of total 10 bits
//		Binary representation of 1024 is 10000000000, consisting of total 11 bits
//		and if we multiply 1000 with 1024 i.e. (1000 * 1024), we will get 1024000
//		Binary representation of 1024000 is 11111010000000000000, consisting of total 20 bits which is less than the number 
//		of bits (32bits) of int data type, so we can store the number pairs(by multiplying one number with 1024 and adding 
//		another number to it) to retrieve each indivisual number later on(by taking out the remainder and the quotient).
//	
//	The formula works when multiply by number which is greater than both of num1 and num2.
//	eg:
//	for(0 to 9) take 10(i.e. greater than 0 and 9)
//	for(0 to 19) take 20(i.e. greater than 0 and 19)
//	for(0 to 29) take 30(i.e. greater than 0 and 29) and so on

//	NOTE: For our case, we could have taken 1001(according to constraints) instead of 1024 but took 1024 because 2^10 will give 1024 and 
//	will be easier to use this value(1024) while performing bitwise operation as 1024 is a power of 2 and 
//	multiplying 1024 * 1000(max value according to constraint) fits in 32 bits of int and there will be no overflow.

//	TC:-> O(N)
//	SC:-> O(1)
	static int[] shuffleInPlace(int[] nums, int n) {
		for (int i = n; i < nums.length; i++) {
			nums[i] = nums[i] * 1024 + nums[i - n];
		}

		for (int i = n, idx = 0; i < nums.length; i++, idx += 2) {
			nums[idx] = nums[i] % 1024;
			nums[idx + 1] = nums[i] / 1024;
		}
		return nums;
	}

//	TC:- O(N)
//	SC:- O(N)
	static int[] shuffle(int[] nums, int n) {

		int[] ans = new int[nums.length];

		for (int i = 0, j = n, k = 0; i < n && j < ans.length; i++, j++) {
			ans[k++] = nums[i];
			ans[k++] = nums[j];
		}
		return ans;
	}

}
