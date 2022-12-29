package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubSequences {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2 };

		printSubSeqs(arr, 0, new ArrayList<>(), new HashSet<>());
		System.out.println();
		printAllSubSequences(arr, 0, new ArrayList<>());
		System.out.println();
		System.out.println(countSubSeqs(arr, 0, new ArrayList<>(), new HashSet<>()));
	}

	static void printAllSubSequences(int[] arr, int i, List<Integer> list) {
		if (i >= arr.length) {
			System.out.print(list + " ");
			return;
		}
		list.add(arr[i]); // take
		printAllSubSequences(arr, i + 1, list); // move for the next one
		list.remove(list.size() - 1); // remove what was taken
		printAllSubSequences(arr, i + 1, list); // move for the next one
	}

	static void printAllSubSequences(String s, int i, StringBuilder str) {
		if (i >= s.length()) {
			System.out.println(str.toString());
			return;
		}
		str.append(s.charAt(i));
		printAllSubSequences(s, i + 1, str);
		str.deleteCharAt(str.length() - 1);
		printAllSubSequences(s, i + 1, str);
	}

	static int countSubseqSumK(int[] arr, int i, List<Integer> list, int sum, int k) {
		if (i >= arr.length) {
			if (sum == k) {
				System.out.println("Subsequence : " + list);
				return 1;
			}
			return 0;
		}
		sum += arr[i];
		list.add(arr[i]);
		int takeCount = countSubseqSumK(arr, i + 1, list, sum, k);
		sum -= list.get(list.size() - 1);
		list.remove(list.size() - 1);
		int notTakeCount = countSubseqSumK(arr, i + 1, list, sum, k);
		return takeCount + notTakeCount;
	}

	static boolean anyOneSubSeqWithSumK(int[] arr, int i, List<Integer> list, int sum, int k) {

//		if (sum == k) {
//			System.out.println("Subsequence : " + list);
//			return true;
//		}
		if (i >= arr.length) {
			if (sum == k) {
				System.out.println("Subsequence : " + list);
				return true;
			}
			return false;
		}
		sum += arr[i];
		list.add(arr[i]);
		boolean isFound = anyOneSubSeqWithSumK(arr, i + 1, list, sum, k);
		if (!isFound) {
			sum -= list.get(list.size() - 1);
			list.remove(list.size() - 1);
			return anyOneSubSeqWithSumK(arr, i + 1, list, sum, k);
		}
		return true;
	}

	static void printSubSeqs(int[] arr, int idx, List<Integer> list, Set<List<Integer>> set) {
		if (set.contains(list))
			return;
		if (idx == arr.length) {
			System.out.print(list + " ");
			set.add(new ArrayList<>(list));
			return;
		}

		for (int i = idx; i < arr.length; i++) {
			list.add(arr[i]);
			printSubSeqs(arr, i + 1, list, set);
			list.remove(list.size() - 1);
			printSubSeqs(arr, i + 1, list, set);
		}
	}

	static int countSubSeqs(int[] arr, int idx, List<Integer> list, Set<List<Integer>> set) {
		if (set.contains(list))
			return 0;
		if (idx == arr.length) {
			set.add(new ArrayList<>(list));
			return 1;
		}
		int count = 0;
		for (int i = idx; i < arr.length; i++) {
			list.add(arr[i]);
			count += countSubSeqs(arr, i + 1, list, set);
			list.remove(list.size() - 1);
			count += countSubSeqs(arr, i + 1, list, set);
		}
		return count;
	}
}
