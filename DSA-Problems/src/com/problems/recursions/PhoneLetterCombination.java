package com.problems.recursions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneLetterCombination {

	private static Map<Integer, char[]> map = new HashMap<>();

	static {
		map.put(2, new char[] { 'a', 'b', 'c' });
		map.put(3, new char[] { 'd', 'e', 'f' });
		map.put(4, new char[] { 'g', 'h', 'i' });
		map.put(5, new char[] { 'j', 'k', 'l' });
		map.put(6, new char[] { 'm', 'n', 'o' });
		map.put(7, new char[] { 'p', 'q', 'r', 's' });
		map.put(8, new char[] { 't', 'u', 'v' });
		map.put(9, new char[] { 'w', 'x', 'y', 'z' });
	}

	public static void main(String[] args) {
		PhoneLetterCombination obj = new PhoneLetterCombination();
//		int[] arr = { 1 };
//		System.out.println(obj.letterCombinations(arr));
//		System.out.println(obj.letterCombinations("23"));
		System.out.println(obj.letterCombinationsIterative("1"));
	}

	List<String> letterCombinations(int[] arr) {
		List<String> ans = new ArrayList<>();
		wordsCombos(arr, ans, new StringBuilder(), 0);
		return ans;
	}

	private static void wordsCombos(int[] arr, List<String> ans, StringBuilder str, int idx) {
		if (str.length() == arr.length) {
			ans.add(str.toString());
		} else {
			char[] chars = map.get(arr[idx]);
			for (char ch : chars) {
				str.append(ch);
				wordsCombos(arr, ans, str, idx + 1);
				str.deleteCharAt(str.length() - 1);
			}
		}
	}

	List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>();
		wordsCombos(digits, ans, new StringBuilder(), 0);
		return ans;
	}

	private static void wordsCombos(String digits, List<String> ans, StringBuilder str, int idx) {
		if (str.length() == digits.length()) {
			ans.add(str.toString());
		} else {
			char[] chars = map.get(digits.charAt(idx) - '0');
			for (char ch : chars) {
				str.append(ch);
				wordsCombos(digits, ans, str, idx + 1);
				str.deleteCharAt(str.length() - 1);
			}
		}
	}

	public List<String> letterCombinationsIterative(String digits) {
		LinkedList<String> ans = new LinkedList<>();
		if (digits.isEmpty())
			return ans;
		ans.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = digits.charAt(i) - '0';
			while (ans.peek().length() == i) {
				String t = ans.remove();
				for (char s : map.get(x))
					ans.add(t + s);
			}
		}
		return ans;
	}
}
