package com.problems.java.strings;

import java.util.Arrays;
import java.util.Collections;

public class ReverseWords {

	public static void main(String[] args) {
		String s = "i.like.this.program.very.much";

		System.out.println(reverseWords(s));

		String str = "a good   example";

		System.out.println(reverseWordsLC(str));
		System.out.println(reverseWordsLCSimple(str));

	}

	public static String reverseWordsLCSimple(String s) {
		String[] words = s.trim().split(" +");
		Collections.reverse(Arrays.asList(words));
		return String.join(" ", words);
	}

	// TC:- O(N)
	// SC:- O(N)
	public static String reverseWordsLC1ms(String s) {
		char[] ch = s.toCharArray();
		char[] res = new char[ch.length + 1];
		int index = 0;

		for (int i = ch.length - 1; i >= 0; --i) {

			if (ch[i] == ' ') {
				continue;
			}

			int lastIndex = i;

			while (i >= 0 && ch[i] != ' ') {
				--i;
			}

			for (int firstIndex = i + 1; firstIndex <= lastIndex; ++firstIndex) {
				res[index++] = ch[firstIndex];
			}

			res[index++] = ' ';
		}

		return new String(res, 0, index - 1);
	}

	// TC:- O(N)
	// SC:- O(N)
	public static String reverseWordsLC(String s) {
		s = s.trim();
		String temp = "";
		String ans = "";

		for (int i = s.length() - 1; i >= 0; i--) {
			while (i >= 0 && s.charAt(i) == ' ')
				i--;
			while (i >= 0 && s.charAt(i) != ' ') {
				temp = s.charAt(i--) + temp;
			}
			if (i > 0)
				ans += temp + " ";
			else
				ans += temp;
			temp = "";
		}
		return ans;
	}

	// TC:- O(N)
	// SC:- O(N)
	private static String reverseWordsII(String S) {
		String temp = "";
		String ans = "";

		for (int i = S.length() - 1; i >= 0; i--) {
			if (S.charAt(i) == '.') {
				ans += temp + ".";
				temp = "";
			} else {
				temp = S.charAt(i) + temp;
			}
		}
		ans += temp;
		return ans;
	}

	// TC:- O(N)
	// SC:- O(N)
	private static String reverseWords(String S) {
		String[] words = S.split("\\.");

		int i = 0, j = words.length - 1;

		while (i < j) {
			String temp = words[i];
			words[i] = words[j];
			words[j] = temp;
			i++;
			j--;
		}
		S = "";
		for (i = 0; i < words.length - 1; i++) {
			S += words[i] + ".";
		}
		S += words[words.length - 1];
		return S;
	}
}
