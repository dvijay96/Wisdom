package com.problems.strings;

public class LongestPalindromicSubstring {

//	Given a string S, find the longest palindromic substring in S. Substring of string S: S[ i . . . . j ] where 0 ≤ i ≤ j < len(S).
//	Palindrome string: A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S. 
//	Incase of conflict, return the substring which occurs first ( with the least starting index).
//
//			Example 1:
//
//			Input:
//			S = "aaaabbaa"
//			Output: aabbaa
//			Explanation: The longest Palindromic
//			substring is "aabbaa".	
	public static void main(String[] args) {

		String s = "musamadamasum";

		System.out.println(longestPalin(s));
		System.out.println(longestPalindSubString(s));
	}

	// TC:- O(N^2)
	// Optimal Approach:- Expanding the substrings at each iteration considering the
	// curr iterative char as mid
	// and expanding in left and right to check if the both sides chars are equal or
	// not
	// to make the overall substring as palindrome.
	private static String longestPalindSubString(String s) {

		String evenLenPalin = longestPalindrome(s, 0, 0);
		String oddLenPalin = longestPalindrome(s, 0, 1);

		return evenLenPalin.length() > oddLenPalin.length() ? evenLenPalin : oddLenPalin;
	}

	private static String longestPalindrome(String s, int l, int r) {
		int len = 0;
		int start = 0;
		int end = 0;

		for (int i = 0; i < s.length(); i++) {
			int left = i - l;
			int right = i + r;
			while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
				if (right - left + 1 > len) {
					len = right - left + 1;
					start = left;
					end = right + 1;
				}
				left--;
				right++;
			}
		}
		return s.substring(start, end);
	}

	// TC:- O(N^3)
	// Brute force;- Calculate all substrings ans check which one is the longest
	// palindrome
	private static String longestPalin(String s) {
		String ans = "";

		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				String substr = s.substring(i, j + 1);
				if (isPalindrome(substr) && substr.length() > ans.length()) {
					ans = substr;
				}
			}
		}
		return ans;
	}

	private static boolean isPalindrome(String str) {
		StringBuilder builder = new StringBuilder(str);
		return builder.reverse().toString().equals(str);
	}

}
