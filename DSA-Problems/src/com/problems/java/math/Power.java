package com.problems.java.math;

public class Power {

	private static final int mod = 1_000_000_007;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(pow(1, 105000));
		System.out.println("Time Taken = " + ((System.currentTimeMillis() - start) / 1000.0f) + "secs");

		start = System.currentTimeMillis();
		System.out.println(pow2(1, 105000));
		System.out.println("Time Taken = " + ((System.currentTimeMillis() - start) / 1000.0f) + "secs");
	}

	// TC:- O(N)
	static long pow(long x, int n) {
		long ans = 1;
		while (n > 0) {
			ans = (ans * x) % mod;
			n--;
		}
		return ans;
	}

	// TC:- O(logN)
	static long pow2(long x, int n) {
		long ans = 1;
		while (n > 0) {
			if (n % 2 == 0) {
				x = (x * x) % mod;
				n = n / 2;
			} else {
				ans = (ans * x) % mod;
				n = n - 1;
			}
		}
		return ans;
	}
}
