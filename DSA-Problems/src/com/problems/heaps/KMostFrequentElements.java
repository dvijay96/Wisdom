package com.problems.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;

public class KMostFrequentElements {

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(7);

		ArrayUtils.print(arr);

		ArrayUtils.print(topKFrequent(arr, 2));
		ArrayUtils.print(topKFrequentOp(arr, 2));
		ArrayUtils.print(topKFrequentOpII(arr, 2));
	}

	// TC:- O(nlogk)
	// SC:- O(n)
	public static int[] topKFrequent(int[] nums, int k) {

		Map<Integer, Integer> map = new HashMap<>();

		for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			pq.add(entry);

			if (pq.size() > k) {
				pq.poll();
			}
		}

		int[] ans = new int[k];
		int j = k - 1;

		while (!pq.isEmpty()) {
			ans[j--] = pq.poll().getKey();
		}

		return ans;
	}

	// TC:- O(n)
	// SC:- O(n)
	public static int[] topKFrequentOp(int[] nums, int k) {

		List<Integer>[] bucket = new List[nums.length + 1];

		Map<Integer, Integer> map = new HashMap<>();

		for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			Integer freq = entry.getValue();
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(entry.getKey());
		}

		int[] ans = new int[k];
		for (int i = bucket.length - 1, j = 0; i >= 0 && j < k; i--) {
			if (bucket[i] != null) {
				for (int x = 0; x < bucket[i].size() && j < k; x++) {
					ans[j++] = bucket[i].get(x);
				}
			}
		}

		return ans;
	}

	@SuppressWarnings("unchecked")
	public static int[] topKFrequentOpII(int[] nums, int k) {

		Queue<Integer>[] bucket = new Queue[nums.length + 1];

		Map<Integer, Integer> map = new HashMap<>();

		for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			Integer freq = entry.getValue();
			if (bucket[freq] == null) {
				bucket[freq] = new PriorityQueue<>(Collections.reverseOrder());
			}
			bucket[freq].add(entry.getKey());
		}

		int[] ans = new int[k];
		for (int i = bucket.length - 1, j = 0; i >= 0 && j < k; i--) {
			if (bucket[i] != null) {
				Queue<Integer> pq = bucket[i];
				while (j < k && !pq.isEmpty()) {
					ans[j++] = pq.poll();
				}
			}
		}

		return ans;
	}
}
