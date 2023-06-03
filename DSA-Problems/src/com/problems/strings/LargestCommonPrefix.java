package com.problems.strings;

import java.util.Arrays;

public class LargestCommonPrefix {

	public static void main(String[] args) {
		String[] strArr = { "aabc", "aaaaaa" };

		System.out.println(longestCommonPrefix(strArr, 0));

	}

	// TC:- O(M*N)
	// SC:- O(1)
	static String longestCommonPrefix(String strs[], int n) {

		String ans = "";

		for (int i = 0; i < strs[0].length(); i++) {

			char c = strs[0].charAt(i);

			boolean isMatch = true;

			for (int j = 1; j < strs.length; j++) {
				if (i >= strs[j].length() || strs[j].charAt(i) != c) {
					isMatch = false;
					break;
				}
			}

			if (!isMatch)
				break;
			ans += c;
		}
		return ans.length() == 0 ? "-1" : ans;
	}

	// TC:- O(NlogN) + O(M) , M is the size of prefix
	// SC:- O(1)
	static String longestCommonPrefix(String arr[]) {

		if (arr.length == 1)
			return arr[0];
		Arrays.sort(arr);
		String first = arr[0];
		String last = arr[arr.length - 1];

		String ans = "";

		int i = 0;

		while (i < first.length() && i < last.length()) {
			if (first.charAt(i) == last.charAt(i)) {
				ans += first.charAt(i++);
			} else {
				break;
			}
		}
		return ans.length() == 0 ? "-1" : ans;
	}
}
