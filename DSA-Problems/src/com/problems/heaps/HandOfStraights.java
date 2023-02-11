package com.problems.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

import com.problems.java.utility.ArrayUtils;

public class HandOfStraights {

//	Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
//
//	Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
//
//	 
//
//	Example 1:
//
//	Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//	Output: true
//	Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
//	Example 2:
//
//	Input: hand = [1,2,3,4,5], groupSize = 4
//	Output: false
//	Explanation: Alice's hand can not be rearranged into groups of 4.
	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 6, 2, 3, 4, 7, 8 };

//		ArrayUtils.fillRandomII(arr);   // chances of a positive result are less with this test cases
		ArrayUtils.print(arr);

		int grpSize = 3;

		System.out.println(usingPriorityQueue(arr, grpSize));
		System.out.println(usingTreeMap(arr, grpSize));
		System.out.println(basicApproach(arr, grpSize));
	}

//	We insert all the elements in a min heap using priority queue, so that at each instance we get the min element
//	We start by removing the head of the min heap and then removing consecutive k-1 elements from the heap to form the group
//	If at any instance, a consecutive number is not found, which means we can't group the numbers accordingly.
	public static boolean usingPriorityQueue(int[] hand, int groupSize) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int a : hand) {
			pq.add(a);
		}

		while (!pq.isEmpty()) {
			int start = pq.poll();

			for (int i = 1; i < groupSize; i++) {
				if (!pq.remove(start + i))
					return false;

			}
		}
		return true;
	}

	public static boolean usingTreeMap(int[] hand, int groupSize) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int a : hand) {
			map.put(a, map.getOrDefault(a, 0) + 1);
		}

		while (!map.isEmpty()) {
			int start = map.firstKey();

			for (int i = 1; i < groupSize; i++) {
				if (!map.containsKey(start + i))
					return false;

				map.put(start + i, map.get(start + i) - 1);

				if (map.get(start + i) == 0)
					map.remove(start + i);
			}
			map.put(start, map.get(start) - 1);

			if (map.get(start) == 0)
				map.remove(start);
		}
		return true;
	}

//	WE sort the array.
//	Make use of a boolean array to keep track of which elements being used or not used for a group.
//	Start from the beginning element, which will be the 1st element of  the subsequent k size group.
//	try to find k-1 consecutive numbers in the forward direction and mark them as used.
//	If at any point we are not able to find k-1 consecutive elements and form a group, which means with this the requested group can't be formed.
//	Carry on the process for all elements as the start element and try to find k-1 consecutive elements.
	public static boolean basicApproach(int[] hand, int groupSize) {
		if (hand == null || hand.length == 0 || groupSize == 0 || hand.length % groupSize != 0)
			return false;
		Arrays.sort(hand);

		boolean[] used = new boolean[hand.length];

		for (int i = 0; i < hand.length; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			int currCard = hand[i];
			int currGroupSize = 1;
			int j = i + 1;

			while (j < hand.length && currGroupSize < groupSize) {
				if (!used[j] && hand[j] == currCard + 1) {
					currCard = hand[j];
					currGroupSize++;
					used[j] = true;
				}
				j++;
			}
			// if we got here and didn't form a full group then return false
			if (currGroupSize != groupSize)
				return false;
		}
		return true;
	}
}
