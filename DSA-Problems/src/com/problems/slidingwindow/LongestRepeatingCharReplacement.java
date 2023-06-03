package com.problems.slidingwindow;

import com.problems.java.utility.ArrayUtils;

public class LongestRepeatingCharReplacement {

//	You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. 
//	You can perform this operation at most k times.
//	Return the length of the longest substring containing the same letter you can get after performing the above operations.
//
//	 
//
//	Example 1:
//
//	Input: s = "ABAB", k = 2
//	Output: 4
//	Explanation: Replace the two 'A's with two 'B's or vice versa.
//	Example 2:
//
//	Input: s = "AABABBA", k = 1
//	Output: 4
//	Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//	The substring "BBBB" has the longest repeating letters, which is 4.

	public static void main(String[] args) {

		char[] ch = new char[7];

		ArrayUtils.fillRandomRanges(ch, 'A', 'D');

		ArrayUtils.print(ch);

		System.out.println(characterReplacement(new String(ch), 2));

	}

//	The idea is to find the longest sub string with same characters and/plus also with at most k extra different chars.

//	The lenght of such longest sub string would be 
//			len = max frequency of a char + k
//		=>   k  = len - max freq of a char			--- eq 1
//				
//		So a valid sub string would have  k <= len - max freq of a char

//		We will use two pointer sliding window approach,
//		Whenever the above condition (eq 1) fails, we will shrink our window from the left.
//	
	public static int characterReplacement(String s, int k) {
		int ans = 0;
		int maxFreq = 0;
		int letterToReplace = 0;
		int[] chars = new int[26];

		for (int l = 0, r = 0; r < s.length(); r++) {
			chars[s.charAt(r) - 'A']++;

			maxFreq = Math.max(maxFreq, chars[s.charAt(r) - 'A']);

			letterToReplace = (r - l + 1) - maxFreq; // total substr length - maxFreq

			if (letterToReplace > k) { // shrink window size
				chars[s.charAt(l) - 'A']--;
				l++;
			}

			ans = Math.max(ans, r - l + 1);
		}
		return ans;
	}
}
