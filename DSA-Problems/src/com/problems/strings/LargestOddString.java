package com.problems.strings;

public class LargestOddString {

	// You are given a string num, representing a large integer. Return the
	// largest-valued odd integer (as a string) that is a non-empty substring of
	// num, or an empty string "" if no odd integer exists.

// Example 1:
//	Input: num = "52"
//	Output: "5"
//	Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.

// Example 2:
//	Input: num = "4206"
//	Output: ""
// Explanation: There are no odd numbers in "4206".

// Example 3:
//	Input: num = "35427"
//	Output: "35427"
//	Explanation: "35427" is already an odd number.
	public static void main(String[] args) {
		String str = "505570";
		System.out.println(largestOddNumber(str));

		str = "504";
		System.out.println(largestOddNumber(str));
	}

	// find the right most odd digit and the whole left part would be odd
	// TC:- O(N)
	// SC:- O(1)
	public static String largestOddNumber(String num) {
		for (int i = num.length() - 1; i >= 0; i--) {
			if ((num.charAt(i) - 32) % 2 != 0) {
				return num.substring(0, i + 1);
			}
		}
		return "";
	}

	// TC:- O(N)
	// Got TLE on LC
	@Deprecated
	public String largestOddNumberLC(String num) {
		String ans = "";
		String temp = "";
		for (int i = 0; i < num.length(); i++) {
			temp += num.charAt(i);
			if (num.charAt(i) % 2 == 1)
				ans = temp;
		}
		return ans;
	}

}
