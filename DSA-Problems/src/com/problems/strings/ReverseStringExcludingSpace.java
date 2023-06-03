package com.problems.strings;

public class ReverseStringExcludingSpace {

	public static void main(String[] args) {

		String str = "Customize your notebook cover";

		ReverseStringExcludingSpace obj = new ReverseStringExcludingSpace();

		System.out.println(obj.reverseStringExcludingSpace(str));
		
		System.out.println(new StringBuilder(str).reverse());
	}

	public String reverseStringExcludingSpace(String str) {

		String[] arr = str.split(" ");

		int i = 0;
		int j = arr.length - 1;

		while (i <= j) {
			String iRev = reverse(arr[i]);
			String jRev = reverse(arr[j]);

			arr[i++] = jRev;
			arr[j--] = iRev;
		}

		StringBuilder rev = new StringBuilder();

		for (i = 0; i < arr.length - 1; i++) {
			rev.append(arr[i]);
			rev.append(" ");
		}
		rev.append(arr[arr.length - 1]);

		return rev.toString();
	}

	private String reverse(String string) {
		StringBuilder rev = new StringBuilder();

		for (int i = string.length() - 1; i >= 0; i--) {
			rev.append(string.charAt(i));
		}

		return rev.toString();
	}
}
