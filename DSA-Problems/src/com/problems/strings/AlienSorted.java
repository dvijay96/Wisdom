package com.problems.strings;

import java.util.HashMap;
import java.util.Map;

public class AlienSorted {

//	In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. 
//	The order of the alphabet is some permutation of lowercase letters.
//
//	Given a sequence of words written in the alien language, and the order of the alphabet, 
//	return true if and only if the given words are sorted lexicographically in this alien language.
//
//	Example 1:
//
//	Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//	Output: true
//	Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
//	
//	Example 2:
//
//	Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
//	Output: false
//	Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
//	
//	Example 3:
//
//	Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
//	Output: false
//	Explanation: The first three characters "app" match, and the second string is shorter (in size.) 
//	According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character 
//	which is less than any other character

	public static void main(String[] args) {
		AlienSorted obj = new AlienSorted();

		String[] words = { "hello", "leetcode" };
		String order = "hlabcdefgijkmnopqrstuvwxyz";

		System.out.println(obj.isAlienSorted(words, order));
	}

	int[] mapping = new int[26];
	Map<Character, Integer> map = new HashMap<>();

	public boolean isAlienSorted(String[] words, String order) {
		for (int i = 0; i < order.length(); i++)
			map.put(order.charAt(i), i);
//			mapping[order.charAt(i) - 'a'] = i;
		for (int i = 1; i < words.length; i++)
			if (bigger(words[i - 1], words[i]))
				return false;
		return true;
	}

	boolean bigger(String s1, String s2) {
		int n = s1.length(), m = s2.length();
		for (int i = 0; i < n && i < m; ++i)
			if (s1.charAt(i) != s2.charAt(i))
				return map.get(s1.charAt(i)) > map.get(s2.charAt(i));
//				return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
		return n > m;
	}
}
