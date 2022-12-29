package com.problems.java;

public class TowerOfHanoi {

	public static void main(String[] args) {
		atoi(3, "S", "H", "D");
	}

	static void atoi(int n, String source, String helper, String dest) {
		if (n == 0)
			return;
		if (n == 1) {
			System.out.println(n + " :- " + source + " -> " + dest);
			return;
		}
		atoi(n - 1, source, dest, helper);
		System.out.println(n + " :- " + source + " -> " + dest);
		atoi(n - 1, helper, source, dest);
	}

}
