package com.problems.strings;

public class RomanInteger {

	public static void main(String[] args) {
		System.out.println(romanToInteger("MDCCCLXXXIV"));
		System.out.println(integerToRoman(1884));
	}

	public static int romanToInteger(String s) {
		int ans = 0;
		char last = '?';

		for (int i = s.length() - 1; i >= 0; i--) {
			int currVal = valueOf(s.charAt(i));
			int lastVal = valueOf(last);

			if (currVal < lastVal)
				ans -= currVal;
			else
				ans += currVal;
			last = s.charAt(i);
		}
		return ans;
	}

	static int valueOf(char ch) {
		switch (ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

	public static String integerToRoman(int num) {
		StringBuilder ans = new StringBuilder();

		Object[][] romanChars = { { 1, "I" }, { 4, "IV" }, { 5, "V" }, { 9, "IX" }, { 10, "X" }, { 40, "XL" },
				{ 50, "L" }, { 90, "XC" }, { 100, "C" }, { 400, "CD" }, { 500, "D" }, { 900, "CM" }, { 1000, "M" } };

		for (int i = romanChars.length - 1; i >= 0 && num > 0; i--) {
			if (num / (int) romanChars[i][0] != 0) {
				int count = num / (int) romanChars[i][0];
				for (int j = 0; j < count; j++) {
					ans.append((String) romanChars[i][1]);
				}
				num = num % (int) romanChars[i][0];
			}
		}

		return ans.toString();
	}
}
