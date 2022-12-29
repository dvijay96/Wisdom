package com.problems.java.strings;

public class AreRotations {

	public static void main(String[] args) {

		String s1 = "geeks";
		String s2 = "eksge";

		System.out.println(rotateString(s1, s2));
		System.out.println(areRotations(s1, s2));
	}

	public static boolean rotateString(String s, String goal) {
		if (s.length() != goal.length()) {
			return false;
		}

		s += s;

		if (s.indexOf(goal) != -1) {
			return true;
		}
		return false;
	}

	public static boolean areRotations(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		StringBuilder temp = new StringBuilder(s1);

		for (int i = 0; i < s1.length(); i++) {
			temp.deleteCharAt(0);
			temp.append(s1.charAt(i));
			if (temp.toString().equals(s2)) {
				return true;
			}
		}
		return false;
	}
}
