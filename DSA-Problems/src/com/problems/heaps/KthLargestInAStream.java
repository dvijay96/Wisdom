package com.problems.heaps;

import java.util.PriorityQueue;

import com.problems.java.utility.ArrayUtils;

public class KthLargestInAStream {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		ArrayUtils.print(arr);
		int[] ans = kthLargest(3, arr);

		ArrayUtils.print(ans);
	}

	static int[] kthLargest(int k, int[] arr) {
		if (k == 1) {
			return arr;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]);

			if (pq.size() < k) {
				arr[i] = -1;
			} else {
				while (!pq.isEmpty() && pq.size() > k) {
					pq.poll();
				}
				arr[i] = pq.peek();
			}
		}

		return arr;
	}
}
