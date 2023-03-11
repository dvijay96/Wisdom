package com.problems.gfg.contest;

import java.util.Arrays;

public class BestPrice {

	public static void main(String[] args) {
		int n = 2;
		int[][] price = { { 1, 3 }, { 2, 4 } };
		int k = 2;
		int q = 1;
		int[][] queries = { { 1, 4 } };

		System.out.println(Arrays.toString(bestPrice(n, price, k, q, queries)));
	}

	static int[] bestPrice(int n, int[][] price, int k, int q, int[][] queries) {

		int max = 0;

		for (int i = 0; i < price.length; i++) {
			max = Math.max(price[i][0], price[i][1]);
		}
		for (int i = 0; i < queries.length; i++) {
			max = Math.max(queries[i][0], queries[i][1]);
		}
//		System.out.println("MAX: " + max);
		int[] prefSum = new int[max + 2];

		for (int i = 0; i < price.length; i++) {
			int l = price[i][0];
			int r = price[i][1];

			prefSum[l]++;
			prefSum[r + 1]--;
		}

		for (int i = 1; i < prefSum.length; i++) {
			prefSum[i] += prefSum[i - 1];
		}

		for (int i = 0; i < prefSum.length; i++) {
			if (prefSum[i] >= k) {
				prefSum[i] = 1;
			} else {
				prefSum[i] = 0;
			}
		}
		for (int i = 1; i < prefSum.length; i++) {
			prefSum[i] += prefSum[i - 1];
		}

		int[] ans = new int[q];

		for (int i = 0; i < queries.length; i++) {
			int l = queries[i][0];
			int r = queries[i][1];

			ans[i] = prefSum[r] - prefSum[l - 1];
		}

		return ans;
	}
}
