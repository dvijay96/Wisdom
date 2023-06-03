package com.problems.strings;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

	public static void main(String[] args) {
		String s1 = "aaby";
		String s2 = "xxya";
		System.out.println(areIsomorphic(s1, s2));
		System.out.println(areIsomorphicBidirectional(s1, s2));
		System.out.println(isIsomorphic(s1, s2));
		
	}

	public static boolean isIsomorphic(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		Map<Character, Character> map1 = new HashMap<>();
		Map<Character, Character> map2 = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			char ch1 = s.charAt(i);
			char ch2 = t.charAt(i);

			if ((map1.containsKey(ch1) && map1.get(ch1) != ch2) || (map2.containsKey(ch2) && map2.get(ch2) != ch1)) {
				return false;
			}
			map1.put(ch1, ch2);
			map2.put(ch2, ch1);
		}
		
		return true;
	}

	public static boolean areIsomorphic(String str1, String str2) {

		if (str1.length() != str2.length()) {
			return false;
		}

		Map<Character, Character> map12 = new HashMap<>();
		Map<Character, Character> map21 = new HashMap<>();

		for (int i = 0; i < str1.length(); i++) {
			char ch1 = str1.charAt(i);
			char ch2 = str2.charAt(i);

			if (map12.containsKey(ch1)) {
				if (map21.get(ch2) == null || map21.get(ch2) != ch1) {
					return false;
				}
			} else {
				if (map21.containsKey(ch2) && (map21.get(ch2) == null || map21.get(ch2) != ch1)) {
					return false;
				} else {
					map12.put(ch1, ch2);
					map21.put(ch2, ch1);
				}
			}
		}
		return true;
	}

	public static boolean areIsomorphicBidirectional(String str1, String str2) {

		if (str1.length() != str2.length()) {
			return false;
		}

		Map<Character, Character> map12 = new HashMap<>();
		Map<Character, Character> map21 = new HashMap<>();

		for (int i = 0; i < str1.length(); i++) {
			char ch1 = str1.charAt(i);
			char ch2 = str2.charAt(i);

			if (map12.containsKey(ch1)) {
				if (map21.get(ch2) == null || map21.get(ch2) != ch1) {
					return false;
				}
			} else {
				if (map21.containsKey(ch2) && (map21.get(ch2) == null || map21.get(ch2) != ch1)) {
					return false;
				} else if (map21.containsKey(ch1) && (map21.get(ch1) == null || map21.get(ch1) != ch2)) {
					return false;
				} else {
					map12.put(ch1, ch2);
					map21.put(ch2, ch1);
				}
			}
		}
		return true;
	}
}
