package com.problems.strings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.problems.java.utility.ArrayUtils;

public class KthNonRepeatingChar {

	public static void main(String[] args) {

		char[] arr = ArrayUtils.getCharArray(10);

		KthNonRepeatingChar obj = new KthNonRepeatingChar();

		String str = new String(arr);
		System.out.println("str = " + str);
		System.out.println(obj.kthNonRepeatingChar(str, 3));
	}

	public char kthNonRepeatingChar(String str, int k) {

		char ans = '0';
		Map<Character, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < str.length(); i++) {
			map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
		}

		List<Character> chars = new ArrayList<>();

		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				chars.add(entry.getKey());
			}
		}

		if (chars.size() >= k) {
			return chars.get(k - 1);
		}

		return ans;
	}
}
