package com.problems.java.slidingwindow;

import java.util.HashMap;
import java.util.Map;

import com.problems.java.utility.ArrayUtils;

public class MinWindowSubString {

//	Given two strings s and t of lengths m and n respectively, return the minimum window 
//			substring
//			 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//			The test cases will be generated such that the answer is unique.
//
//			 
//
//			Example 1:
//
//			Input: s = "ADOBECODEBANC", t = "ABC"
//			Output: "BANC"
//			Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
//			Example 2:
//
//			Input: s = "a", t = "a"
//			Output: "a"
//			Explanation: The entire string s is the minimum window.

	public static void main(String[] args) {

		char[] arr1 = new char[10];
		char[] arr2 = new char[3];
		ArrayUtils.fillRandomRanges(arr1, 'A', 'D');
		ArrayUtils.fillRandomRanges(arr2, 'A', 'D');

		String s = new String(arr1);
		String t = new String(arr2);

		System.out.println("str1 = " + s);
		System.out.println("str2 = " + t);

		System.out.println(minWindow(s, t));
	}

//	Approach:-
//	
//	Acquire and release
//		-> We will collect the char freq map for second string.
//		-> Iterate over the 1st string with two pointers l & r, and count the no. of chars that matches 2nd string's chars.
//		-> When count of matched chars reaches freq sum of second string chars. We can consider it as a potential substr.
//			Then try reducing the window and check if any smaller substr exists within this.
//				Ex:- 
//						str1 = "BABCABCBAB"		str2 = "CCA"
//	
//						Till "BABCABC", we got 2 A's & 2 C's, which satisfies our condition and 
//						could be considered as a potential substr.
//						Then if we reduce it by removing 1st char i.e B
//						"ABCABC" is also a substr which satisfies our condition is smaller than previous one.
//				Similarly, we need to reduce the window and check.
//	
//		-> While reducing, keep track of removed chars, if chars freq goes below to that of its freq in second string, stop
//			reducing.
//					For above ex, 
//								 At "ABCABC", we can remove A and "BCABC" would still be satisfying our condition.
//								Similarly, remove B and "CABC" would still be satisfying our condition.
//								But if we remove C now, the freq of C decreases and thus it is not a proper substr.
//					From here, we can start increasing our window from right and check for next matching substrs.
	public static String minWindow(String s, String t) {

		if (t == null || t.isEmpty() || s == null || s.isEmpty())
			return t;

		Map<Character, Integer> map2 = new HashMap<>();

		for (char c : t.toCharArray()) {
			map2.put(c, map2.getOrDefault(c, 0) + 1);
		}

		int count = 0;
		int len = Integer.MAX_VALUE;
		int[] range = new int[] { -1, -1 };

		Map<Character, Integer> map1 = new HashMap<>();

		for (int l = 0, r = 0; r < s.length(); r++) {
			char ch = s.charAt(r);
			if (map2.containsKey(ch)) {
				map1.put(ch, map1.getOrDefault(ch, 0) + 1);

				if (map1.get(ch) <= map2.get(ch))
					count++;

				if (count == t.length()) {
					while (count == t.length()) {
						if (len > r - l + 1) {
							len = r - l + 1;
							range[0] = l;
							range[1] = r;
						}
						char left = s.charAt(l++);
						if (map2.containsKey(left)) {
							map1.put(left, map1.get(left) - 1);
							if (map1.get(left) < map2.get(left)) {
								count--;
							}
						}
					}
				}
			}
		}

		if (range[0] > -1 && range[1] > -1) {
			return s.substring(range[0], range[1] + 1);
		}
		return "";
	}

	public String minWindowII(String s, String t) {
		if (s == null || s.length() < t.length() || s.length() == 0) {
			return "";
		}
		Map<Character, Integer> map = new HashMap<>();
		for (char c : t.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int left = 0;
		int minLeft = 0;
		int minLen = s.length() + 1;
		int count = 0;
		for (int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) - 1);
				if (map.get(ch) >= 0) {
					count++;
				}
				while (count == t.length()) {
					if (right - left + 1 < minLen) {
						minLeft = left;
						minLen = right - left + 1;
					}
					if (map.containsKey(s.charAt(left))) {
						map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
						if (map.get(s.charAt(left)) > 0) {
							count--;
						}
					}
					left++;
				}
			}
		}
		if (minLen > s.length()) {
			return "";
		}

		return s.substring(minLeft, minLeft + minLen);
	}
}
