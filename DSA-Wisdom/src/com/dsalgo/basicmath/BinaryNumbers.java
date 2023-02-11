package com.dsalgo.basicmath;

import java.util.Arrays;

public class BinaryNumbers {

	// this array can also be calculated as list of power of 2 from 0 to 30.
	private static final int[] BINARY_POWERS = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384,
			32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864,
			134217728, 268435456, 536870912, 1073741824 };

	public static void main(String[] args) {
		BinaryNumbers obj = new BinaryNumbers();
		char[] ch = new char[31];
		ch[0] = '1';
		Arrays.fill(ch, 1, ch.length, '0');
		String str = new String(ch);
		System.out.println(str);
		System.out.println(str.length());
		System.out.println(BINARY_POWERS.length);
		System.out.println(obj.binaryToInteger(str));
	}

	private int binaryToInteger(String str) {
		int ans = 0;
		int n = str.length() - 1;
		for (int i = 0; i <= n; i++) {
			ans += (BINARY_POWERS[n - i] * (str.charAt(i) - '0'));
		}
		return ans;
	}
}
