package com.problems.strings;

import java.util.HashMap;
import java.util.Map;

public class BeautySum {

	public static void main(String[] args) {
		String s = "geeksforgeeks";
		System.out.println(beautySum(s));
		System.out.println(beautySumII(s));
	}

	public static int beautySumII(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			int[] charArr = new int[26];
			int size = 0;
			for (int j = i; j < s.length(); j++) {
				int max = 0;
				int min = Integer.MAX_VALUE;
				if (charArr[s.charAt(j) - 'a'] == 0)
					size++;
				charArr[s.charAt(j) - 'a']++;
				for (int a : charArr) {
					if (a > max)
						max = a;
					if (a != 0 && a < min)
						min = a;
				}
				if (size > 1)
					count += (max - min);
			}
		}
		return count;
	}

	public static int beautySum(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			Map<Character, Integer> map = new HashMap<>();
			for (int j = i; j < s.length(); j++) {
				int max = 0;
				int min = Integer.MAX_VALUE;
				map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
				for (Integer a : map.values()) {
					if (a > max)
						max = a;
					if (a < min)
						min = a;
				}
				if (map.size() > 1)
					count += (max - min);
			}
		}
		return count;
	}
}
