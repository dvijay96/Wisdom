package com.problems.strings;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isAnagram(String s, String t) {

		if (s.length() != t.length()) {
			return false;
		}

		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		}

		for (int i = 0; i < t.length(); i++) {
			if (map.containsKey(t.charAt(i))) {
				map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
				if (map.get(t.charAt(i)) == 0) {
					map.remove(t.charAt(i));
				}
			} else {
				return false;
			}
		}

		return map.isEmpty();
	}

	public boolean isAnagramII(String s, String t) {

		if (s.length() != t.length()) {
			return false;
		}

		int[] chars = new int[26];

		for (int i = 0; i < s.length(); i++) {
			chars[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < t.length(); i++) {
			chars[t.charAt(i) - 'a']--;
		}

		for (int i = 0; i < 26; i++) {
			if (chars[i] != 0)
				return false;
		}
		return true;
	}
}
