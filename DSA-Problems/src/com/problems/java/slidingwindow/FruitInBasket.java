package com.problems.java.slidingwindow;

import com.problems.java.utility.ArrayUtils;

public class FruitInBasket {

	public static void main(String[] args) {
		int[] arr = new int[10];

		ArrayUtils.fillRandomRange(arr, 0, 3);

		ArrayUtils.print(arr);

		System.out.println(totalFruits(arr));
	}

	public static int totalFruits(int[] fruits) {
		int ans = 0;
		int count = 0;
		int first = 0;
		int sec = -1;

		for (int i = 0; i < fruits.length; i++) {
			count++;
			if (fruits[i] == fruits[first]) {
				first = i;
			} else if (sec == -1 || fruits[i] == fruits[sec]) {
				sec = i;
			} else {
				ans = Math.max(ans, count - 1);
				count = Math.abs(first - sec) + 1;
				first = i - 1;
				sec = i;
			}
		}

		return Math.max(ans, count);
	}
}
