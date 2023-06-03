package com.problems.leetcode.daily.array;

import java.util.LinkedList;

public class RemovingStarsFromString {

//	You are given a string s, which contains stars *.
//
//	In one operation, you can:
//
//	Choose a star in s.
//	Remove the closest non-star character to its left, as well as remove the star itself.
//	Return the string after all stars have been removed.
//
//	Note:
//
//	The input will be generated such that the operation is always possible.
//	It can be shown that the resulting string will always be unique.
//	 
//
//	Example 1:
//
//	Input: s = "leet**cod*e"
//	Output: "lecoe"
//	Explanation: Performing the removals from left to right:
//	- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
//	- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
//	- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
//	There are no more stars, so we return "lecoe".
//	
//	Example 2:
//
//	Input: s = "erase*****"
//	Output: ""
//	Explanation: The entire string is removed, so we return an empty string.

	public static void main(String[] args) {
		RemovingStarsFromString obj = new RemovingStarsFromString();

		String str = "leet**cod*e";

		System.out.println(obj.removeStars(str));
		System.out.println(obj.removeStarsOptimized(str));
	}

//	Approach:- 
//			-> Using stack to insert the character from the string and when character is a *, then deleting the top of the stack.
//	
//	TC:- O(N)
//	SC:- O(N)
	public String removeStars(String s) {

		LinkedList<Character> stack = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '*') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(s.charAt(i));
			}
		}

		if (stack.size() == s.length()) {
			return s;
		}

		StringBuilder ans = new StringBuilder();

		while (!stack.isEmpty()) {
			ans.append(stack.pop());
		}

		return ans.reverse().toString();
	}

//	Approach:-
//			-> Similar stack concept, but without stack creation.
//			-> We'll use ans stringbuilder to add and delete characters.
//	
//	TC:- O(N)
//	SC:- O(1)
	public String removeStarsOptimized(String s) {
		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '*') {
				if (ans.length() > 0) {
					ans.deleteCharAt(ans.length() - 1);
				}
			} else {
				ans.append(s.charAt(i));
			}
		}

		return ans.toString();
	}
}
