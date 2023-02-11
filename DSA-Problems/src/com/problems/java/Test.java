package com.problems.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {

	public static void main(String[] args) {
		
		Queue<Integer> list = new LinkedList<>();

		list.add(3);
		list.add(4);
		list.add(5);
		
		System.out.println(list);
		
		list.poll();
		
		System.out.println(list);
	}

	private static int binaryToInteger(String str, List<Integer> list) {
		int ans = 0;
		int n = str.length() - 1;
		for (int i = 0; i <= n; i++) {
			ans += (list.get(n - i) * (str.charAt(i) - '0'));
		}
		return ans;
	}

	static int pow(int x, int n) {
		int ans = 1;
		while (n > 0) {
			if (n % 2 == 0) {
				x = x * x;
				n = n / 2;
			} else {
				ans = ans * x;
				n = n - 1;
			}
		}
		return ans;
	}

	private static List<List<Integer>> subsets(int[] arr) {
		List<List<Integer>> ans = new ArrayList<>();
		subsets(arr, ans, new ArrayList<>(), 0);
		return ans;
	}

	private static void subsets(int[] arr, List<List<Integer>> ans, List<Integer> list, int idx) {
		ans.add(new ArrayList<>(list));
		for (int i = idx; i < arr.length; i++) {
			list.add(arr[i]);
			subsets(arr, ans, list, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public static int peakElement(int[] arr, int n) {

		if (n == 1) {
			return 0;
		}
		if (arr[0] > arr[1]) {
			return 0;
		}
		if (arr[n - 1] > arr[n - 2]) {
			return n - 1;
		}

		int low = 0;
		int high = n - 1;

		while (low < high) {

			int mid = low + (high - low) / 2;

			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return mid;
			}
			if (arr[mid - 1] > arr[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}
