package com.problems.java.math;

public class IntToRoman {

	public static void main(String[] args) {

		IntToRoman obj = new IntToRoman();
		
		System.out.println(obj.intToRoman(45));
	}

	public String intToRoman(int num) {
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
