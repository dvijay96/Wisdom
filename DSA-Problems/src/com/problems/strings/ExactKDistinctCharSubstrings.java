package com.problems.strings;

import java.util.HashMap;
import java.util.Map;

public class ExactKDistinctCharSubstrings {

//	Given a string of lowercase alphabets, count all possible substrings (not necessarily distinct) that have exactly k distinct characters. 
//
//	Example 1:
//
//	Input:
//	S = "aba", K = 2
//	Output:
//	3
//	Explanation:
//	The substrings are:
//	"ab", "ba" and "aba".
	public static void main(String[] args) {
		String s = "aab";
		System.out.println(substrCount(s, 1));
		System.out.println(substrCountII(s, 1));
	}

	static long substrCount(String S, int K) {
		return atmost(S, K) - atmost(S, K - 1);
	}

	static long atmost(String s, int k) {
		int i = 0, j = 0;
		Map<Character, Integer> charMap = new HashMap<>();
		long count = 0;

		while (i < s.length()) {
			charMap.put(s.charAt(i), charMap.getOrDefault(s.charAt(i), 0) + 1);
			while (charMap.size() > k) {
				charMap.put(s.charAt(j), charMap.get(s.charAt(j)) - 1);
				if (charMap.get(s.charAt(j)) == 0)
					charMap.remove(s.charAt(j));
				j++;
			}

			count += (i - j + 1);
			i++;
		}
		return count;
	}

	static long substrCountII(String S, int K) {
		return atmost(S, K) - atmost(S, K - 1);
	}

	static long atmostII(String s, int k) {
		int i = 0, j = 0, size = 0;
		int[] charArr = new int[26];
		long count = 0;

		while (i < s.length()) {
			char ch = s.charAt(i);
			if (charArr[ch - 'a'] == 0)
				size++;
			charArr[ch - 'a']++;
			while (size > k) {
				charArr[s.charAt(j) - 'a']--;
				if (charArr[s.charAt(j) - 'a'] == 0)
					size--;
				j++;
			}

			count += (i - j + 1);
			i++;
		}
		return count;
	}
}
