package com.problems.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import com.problems.java.utility.ArrayUtils;

public class MergeKSortedLists {

//	You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//	Merge all the linked-lists into one sorted linked-list and return it.
//
//	 
//
//	Example 1:
//
//	Input: lists = [[1,4,5],[1,3,4],[2,6]]
//	Output: [1,1,2,3,4,4,5,6]
//	Explanation: The linked-lists are:
//	[
//	  1->4->5,
//	  1->3->4,
//	  2->6
//	]
//	merging them into one sorted list:
//	1->1->2->3->4->4->5->6
//	Example 2:
//
//	Input: lists = []
//	Output: []
//	Example 3:
//
//	Input: lists = [[]]
//	Output: []
	public static void main(String[] args) {
		int k = 5;
		ListNode[] arr = generateTestCase(k);

		MergeKSortedLists obj = new MergeKSortedLists();

//		ListNode pqResult = obj.mergeKListsUsingPQ(arr);

		ListNode mergeAlgoResult = obj.mergeKListsUsingMergeAlgo(arr);

		System.out.println();

//		System.out.println("PriorityQueue Result -> ");
//		print(pqResult);

		System.out.println("\nMergeAlgo Result -> ");
		print(mergeAlgoResult);
	}

	/**
	 * Definition for singly-linked list.
	 * 
	 */
	public static class ListNode {
		int val;
		ListNode next;

		public ListNode() {
		}

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			ListNode temp = this;
			while (temp != null) {
				str.append(temp.val + " ");
				temp = temp.next;
			}
			return str.toString();
		}

	}

	private static ListNode[] generateTestCase(int k) {
		int[] size = new int[k];
		ArrayUtils.fillRandomRange(size, 0, 5);
		List<List<Integer>> values = new ArrayList<>();

		for (int s : size) {
			List<Integer> data = new ArrayList<>();
			for (int i = 0; i < s; i++) {
				data.add(ThreadLocalRandom.current().nextInt(1, 10));
			}
			Collections.sort(data);
			values.add(data);
		}

		System.out.println(values);

		ListNode[] arr = new ListNode[size.length];

		for (int i = 0; i < arr.length; i++) {
			ListNode head = new ListNode(-1);
			List<Integer> val = values.get(i);
			ListNode temp = head;
			for (int v : val) {
				ListNode node = new ListNode(v);
				temp.next = node;
				temp = temp.next;
			}
			arr[i] = head.next;
		}

//		for (ListNode node : arr)
//			print(node);
		return arr;
	}

	public ListNode mergeKListsUsingPQ(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

	public ListNode mergeKListsUsingMergeAlgo(ListNode[] lists) {
		if (lists.length == 0)
			return null;
		return merge(lists, 0, lists.length - 1);
	}

	private ListNode merge(ListNode[] arr, int low, int high) {
		if (low == high) {
			return arr[low];
		}
		int mid = low + (high - low) / 2;

		ListNode left = merge(arr, low, mid);
		ListNode right = merge(arr, mid + 1, high);

		if (left != null && right != null) {
			return merge(left, right);
		} else if (left == null)
			return right;
		else
			return left;
	}

	private ListNode merge(ListNode left, ListNode right) {
		ListNode head = null;
		if (left.val < right.val) {
			head = left;
			left = left.next;
		} else {
			head = right;
			right = right.next;
		}
		ListNode temp = head;

		while (left != null && right != null) {
			if (left.val < right.val) {
				temp.next = left;
				left = left.next;
			} else {
				temp.next = right;
				right = right.next;
			}
			temp = temp.next;
		}

		if (left != null) {
			temp.next = left;
		}
		if (right != null) {
			temp.next = right;
		}

		return head;
	}

	static void print(ListNode node) {
		ListNode temp = node;

		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}
