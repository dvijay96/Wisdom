package com.problems.recursions;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinary {

	public static void main(String[] args) {
		System.out.println(generateBinaryStrings(3));
		System.out.println(generateBinaryStringsII(3));
	}

	private static List<String> generateBinaryStringsII(int n) {
		List<String> ans = new ArrayList<>();
		generateBinariesII(ans, new StringBuilder(), n);
		return ans;
	}

	private static void generateBinariesII(List<String> ans, StringBuilder str, int n) {
		if (str.length() == n) {
			ans.add(str.toString());
		} else {
			for (char c = '1'; c >= '0'; c--) {
				if (str.length() > 0 && str.charAt(str.length() - 1) == '1' && c == '1')
					continue;
				str.append(c);
				generateBinariesII(ans, str, n);
				str.deleteCharAt(str.length() - 1);
			}
		}
	}

	public static List<String> generateBinaryStrings(int n) {
		List<String> ans = new ArrayList<>();
		generateBinaries(ans, new StringBuilder(), n);
		return ans;
	}

	private static void generateBinaries(List<String> ans, StringBuilder str, int n) {
		if (str.length() == n) {
			ans.add(str.toString());
			return;
		}

		str.append('0');
		generateBinaries(ans, str, n);
		str.deleteCharAt(str.length() - 1);
		if (str.length() > 0 && str.charAt(str.length() - 1) == '1')
			return;
		str.append('1');
		generateBinaries(ans, str, n);
		str.deleteCharAt(str.length() - 1);
	}

}
