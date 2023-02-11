package com.problems.java.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStrWithDistinctChars {

//	Given a string s, find the length of the longest 
//	substring without repeating characters.
//
//	Example 1:
//
//	Input: s = "abcabcbb"
//	Output: 3
//	Explanation: The answer is "abc", with the length of 3.
//	Example 2:
//
//	Input: s = "bbbbb"
//	Output: 1
//	Explanation: The answer is "b", with the length of 1.
//	
//	Input: s = "abba"
//	Output: 2
//	Explanation: The answer is "ab" or "ba".

	public static void main(String[] args) {
		String str = "geeksforgeeks";

		System.out.println(bruteForce(str));
		System.out.println(lengthOfLongestSubstring(str));
	}

//	Aproach:
//		The basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values, 
//	and keep two pointers which define the max substring. move the right pointer to scan through the string , 
//	and meanwhile update the hashmap. If the character is already in the hashmap, 
//	then move the left pointer to the right of the same character last found. 
//	Note that the two pointers can only move forward.

	// TC:- O(n)
	// SC:- O(n)
	public static int lengthOfLongestSubstring(String s) {
		int ans = 0;

		Map<Character, Integer> map = new HashMap<>();

		for (int l = 0, r = 0; r < s.length(); r++) {
			if (map.containsKey(s.charAt(r))) {
				l = Math.max(l, map.get(s.charAt(r)) + 1);
				// l = map.get(s.charAt(r))+1;
			}
			map.put(s.charAt(r), r);
			ans = Math.max(ans, r - l + 1);
		}
		return ans;
	}

	// TC:- O(n^2)
	// SC:- O(n)
	public static int bruteForce(String str) {
		int ans = 0;
		for (int i = 0; i < str.length(); i++) {
			Set<Character> set = new HashSet<>();
			for (int j = i; j < str.length(); j++) {
				if (set.contains(str.charAt(j)))
					break;
				set.add(str.charAt(j));
			}
			ans = Math.max(set.size(), ans);
		}
		return ans;
	}
}
