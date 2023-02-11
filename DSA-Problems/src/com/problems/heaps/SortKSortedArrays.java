package com.problems.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.problems.java.utility.ArrayUtils;

public class SortKSortedArrays {

	public static void main(String[] args) {

		int[][] arr = new int[4][4];

		for (int i = 0; i < arr.length; i++) {
			ArrayUtils.fillRandom(arr[i]);
			Arrays.sort(arr[i]);
		}

		ArrayUtils.print(arr);

		System.out.println(mergeKArrays(arr, arr.length));
		System.out.println(mergeKArraysII(arr, arr.length));
	}

	public static List<Integer> mergeKArrays(int[][] arr, int K) {

		List<Integer> ans = new ArrayList<>();

		for (int[] a : arr) {
			for (int num : a) {
				ans.add(num);
			}
		}

		Collections.sort(ans);
		return ans;
	}

	public static List<Integer> mergeKArraysII(int[][] arr, int K) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		List<Integer> ans = new ArrayList<>();

		for (int[] a : arr) {
			for (int num : a) {
				pq.add(num);
			}
		}

		while (!pq.isEmpty()) {
			ans.add(pq.poll());
		}
		return ans;
	}

}
