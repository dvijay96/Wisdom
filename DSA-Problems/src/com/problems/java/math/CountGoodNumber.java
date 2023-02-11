package com.problems.java.math;

public class CountGoodNumber {
//	A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
//
//	For example, "2582" is good because the digits (2 and 8) at even positions are even and the digits (5 and 2) at odd positions are prime. However, "3245" is not good because 3 is at an even index but is not even.
//	Given an integer n, return the total number of good digit strings of length n. Since the answer may be large, return it modulo 109 + 7.
//
//	A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
//	 
//
//	Example 1:
//
//	Input: n = 1
//	Output: 5
//	Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".

	private int mod = 1_000_000_007;

	public static void main(String[] args) {
		CountGoodNumber obj = new CountGoodNumber();
		
		System.out.println(obj.countGoodNumbers(2));
	}

	int countGoodNumbers(int size) {
		return (int)pow(20, size / 2) * (size % 2 == 1 ? 5 : 1);
	}

	long pow(long x, long n) {
		long ans = 1;
		while (n > 0) {
			if (n % 2 == 0) {
				x = (x * x) % mod;
				n = n / 2;
			} else {
				ans = (ans * x) % mod;
				n = n - 1;
			}
		}
		return ans;
	}
}
