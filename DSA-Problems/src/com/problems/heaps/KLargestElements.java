package com.problems.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.problems.java.utility.ArrayUtils;

public class KLargestElements {

	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandom(arr);
		ArrayUtils.print(arr);

		int k = 3;
		System.out.println(approachI(arr, k));
		System.out.println(approachII(arr, k));
	}

	private static ArrayList<Integer> approachI(int[] arr, int k) {
		List<Integer> list = new ArrayList<>();

		for (int n : arr) {
			list.add(n);
		}

		return list.stream().sorted((a, b) -> b.compareTo(a)).limit(k).collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
	}

	private static ArrayList<Integer> approachII(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < k; i++) {
			pq.add(arr[i]);
		}

		for (int i = k; i < arr.length; i++) {
			if (arr[i] > pq.peek()) {
				pq.poll();
				pq.add(arr[i]);
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();

		while (!pq.isEmpty()) {
			ans.add(pq.poll());
		}
		Collections.reverse(ans);
		return ans;
	}
}
