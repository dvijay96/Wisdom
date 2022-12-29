package com.dsalgo.recursion;

public class Basics {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(nthFibonacci(48));
		System.out.println((System.currentTimeMillis() - start) / 1000.0f + " secs");
	}

	static void print(String s, int n) {
		if (n <= 0)
			return;
		System.out.println(s + " -> " + n);
		print(s, n - 1);
	}

	// Using Backtracking
	static void printII(String s, int n) {
		if (n <= 0)
			return;
		printII(s, n - 1);
		System.out.println(s + " -> " + n);
	}

	static void printDesc(int n, int m) {
		if (n > m)
			return;
		printDesc(n + 1, m);
		System.out.print(n + " ");
	}

	static int sumOfN(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return n + sumOfN(n - 1);
	}

	static void reverseArr(int[] arr, int start, int end) {
		if (start < 0 || end > arr.length - 1) {
			return;
		}
		if (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			reverseArr(arr, start + 1, end - 1);
		}
	}

	static boolean isPalindrome(String s, int left, int right) {
		if (s == null || left < 0 || right > s.length() - 1)
			return false;
		else if (s.length() <= 1)
			return true;
		else if (left < right) {
			if (s.charAt(left) == s.charAt(right))
				return isPalindrome(s, left + 1, right - 1);
			else
				return false;
		}
		return true;
	}

	static long nthFibonacci(int n) {
		if (n <= 1)
			return n;
		return nthFibonacci(n - 1) + nthFibonacci(n - 2);
	}
}
