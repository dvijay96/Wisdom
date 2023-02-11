package com.dsalgo.bitwise;

public class CheckSetBit {
//	Check if kth bit (starting from 0 LSB) is a set bit or not for the given num n.
	public static void main(String[] args) {
		int n = 14;
		System.out.println(isSetBit(n, 4));

	}

	static boolean isSetBit(int n, int k) {
		int mask = 1 << k;
		return (mask & n) != 0;
	}
}
