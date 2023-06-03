package com.problems.recursions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PalindromePartioning {

	public static void main(String[] args) {
		List<List<String>> ans = palindromicPartitions("ababbbabbababa");
		ans.sort(new Comparator<List<String>>() {

			@Override
			public int compare(List<String> o1, List<String> o2) {
				return o1.size()-o2.size();
			}
		});
		System.out.println(ans);
	}

	static List<List<String>> palindromicPartitions(String str) {
		List<List<String>> ans = new ArrayList<>();
		solve(0, ans, str, new ArrayList<>());
		return ans;
	}

	private static void solve(int idx, List<List<String>> ans, String str, List<String> list) {
		if (idx == str.length()) {
			ans.add(new ArrayList<>(list));
		} else {
			for (int i = idx; i < str.length(); i++) {
				if (isPalindrome(str, idx, i)) {
					list.add(str.substring(idx, i + 1));
					solve(i + 1, ans, str, list);
					list.remove(list.size() - 1);
				}
			}
		}
	}

	private static boolean isPalindrome(String str, int start, int end) {
		while (start < end) {
			if (str.charAt(start++) != str.charAt(end--))
				return false;
		}
		return true;
	}

}
