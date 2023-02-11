package com.problems.java.queues;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;

public class SlidingWindowMaximum {

//	Given an array arr[] of size N and an integer K. Find the maximum for each and every contiguous subarray of size K.
//
//	Example 1:
//
//	Input:
//	N = 9, K = 3
//	arr[] = 1 2 3 1 4 5 2 3 6
//	Output: 
//	3 3 4 5 5 5 6 
//	Explanation: 
//	1st contiguous subarray = {1 2 3} Max = 3
//	2nd contiguous subarray = {2 3 1} Max = 3
//	3rd contiguous subarray = {3 1 4} Max = 4
//	4th contiguous subarray = {1 4 5} Max = 5
//	5th contiguous subarray = {4 5 2} Max = 5
//	6th contiguous subarray = {5 2 3} Max = 5
//	7th contiguous subarray = {2 3 6} Max = 6

	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandomNegPos(arr);

		ArrayUtils.print(arr);

		System.out.println(maxOfSubarrays(arr, arr.length, 3));

		System.out.println(maxOfSubArrays(arr, 3));

	}

	// Brute Force
	static ArrayList<Integer> maxOfSubarrays(int[] arr, int n, int k) {

		ArrayList<Integer> ans = new ArrayList<>();

		for (int i = 0; i < n - k + 1; i++) {
			int max = arr[i];
			for (int j = i, c = 0; c < k; c++, j++) {
				max = Math.max(max, arr[j]);
			}
			ans.add(max);
		}
		return ans;
	}

//	We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.
//
//	At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means
//
//	1. If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque 
//		and elements are ordered as the sequence in the array
//	2. Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. 
//		This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] 
//		would always be a better candidate.
//
//	3. As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is 
//		the max element in [i-(k-1),i]
	static List<Integer> maxOfSubArrays(int[] arr, int k) {

		List<Integer> ans = new ArrayList<>();
		Deque<Integer> queue = new LinkedList<>();

		for (int i = 0; i < arr.length; i++) {
			while (!queue.isEmpty() && queue.peekFirst() == i - k) { // out of bound condition
				queue.pollFirst();
			}

			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) { // removing any smaller elements as only max
																			// is considered.
				queue.pollLast();
			}

			queue.addLast(i);
			if (i >= k - 1) { // checking the window size
				ans.add(arr[queue.peekFirst()]);
			}
		}
		return ans;
	}
}
