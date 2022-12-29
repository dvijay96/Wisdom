package com.problems.java.binarysearch;

public class NthRoot {

	public static void main(String[] args) {

		int n = 3;
		int m = 27;
		System.out.println(nthRoot(n, m));
		System.out.println(nthRootWithDecimal(n, m));
	}

	public static int nthRoot(int n, int m) {
		int low = 1;
		int high = m;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			double mul = multiply(mid, n);

			if (mul == m) {
				return mid;
			}
			if (mul < m) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	static double multiply(double num, int n) {
		double ans = 1.0;
		for (int i = 1; i <= n; i++) {
			ans *= num;
		}
		return ans;
	}

	public static double nthRootWithDecimal(int n, int m) {
		double low = 1;
		double high = m;
		double eps = 1e-7;

		while ((high - low) > eps) {
			double mid = (low + high) / 2.0;
			if (multiply(mid, n) < m) {
				low = mid;
			} else {
				high = mid;
			}
		}

		System.out.println(n + "th root of " + m + " is " + low);
		return low;
	}
}
