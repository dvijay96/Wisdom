package com.problems.java.math;

import java.util.ArrayList;
import java.util.List;

public class GoodNumbersII {

//	Given two positive integers L, R and a digit D, find out all the good numbers in the range [L, R], which do not contain the digit D.
//	A number is a good number if it's every digit is larger than the sum of digits which are on the right side of that digit. 
//	9620  is good as (2 > 0, 6 > 2+0, 9 > 6+2+0)
//
//	Example 1:
//
//	Input:
//	L=200
//	R=700
//	D=4
//	Output:
//	{210, 310, 320, 510, 520, 521, 530, 531,
//	610, 620, 621, 630, 631, 632, 650}
//	Explanation:
//	These are the only good numbers in the range
//	[200,700]

	public static void main(String[] args) {
		System.out.println(goodNumbers(1, 50, 4));
	}

	static List<Integer> goodNumbers(int left, int right, int digit) {
		ArrayList<Integer> ans = new ArrayList<>();
		for (int num = left; num <= right; num++) {
			if (isGood(num, digit))
				ans.add(num);
		}
		return ans;
	}

	static boolean isGood(int num, int digit) {
		int sum = -1;
		while (num > 0) {
			int currDigit = num % 10;
			if (currDigit == digit)
				return false;
			if (sum == -1)
				sum = currDigit;
			else {
				if (currDigit <= sum)
					return false;
				sum += currDigit;
			}
			num = num / 10;
		}
		return true;
	}
}
