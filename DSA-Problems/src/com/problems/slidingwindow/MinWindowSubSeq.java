package com.problems.slidingwindow;

public class MinWindowSubSeq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String minWindow(String str1, String str2) {
		String window = "";
		int j = 0, min = str1.length() + 1;
		int[] range = new int[] { -1, -1 };
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) == str2.charAt(j)) {
				j++;
				if (j == str2.length()) {
					int end = i + 1;
					j--;
					while (j >= 0) {
						if (str1.charAt(i) == str2.charAt(j))
							j--;
						i--;
					}
					j++;
					i++;
					if (end - i < min) {
						min = end - i;
						range[0] = i;
						range[1] = end;
					}
				}
			}
		}
		if(range[0]>-1) {
			return str1.substring(range[0],range[1]);
		}
		return window;
	}
}
