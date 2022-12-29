package com.problems.java.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

//	Approach 1:
//			- we take an extra boolean array of length same as given array length to keep
//				track of visited elements.
//			- We Iterate over each element and check if it is not visited in our boolean array 
//				and pick that element in our permutation list.
//			- We recursively call our func to the next index and so on.
//			- After recursive call we remove what we added from the list and make visited as false
//				in boolean array map.

	public static void main(String[] args) {
		Permutations obj = new Permutations();

		int[] arr = { 1, 2, 3 };

//		obj.printAllPermutaions(arr);
		obj.printAllPermutationsII(arr);
	}

	void printAllPermutaions(int[] arr) {
		List<Integer> permut = new ArrayList<>();
		boolean[] pos = new boolean[arr.length];
		permutations(arr, permut, pos);
	}

	// TC:- O(n! * n)
	// SC:- O(n + n) max depth of recursive tree is n, and extra boolean array space
	// n.
	private void permutations(int[] arr, List<Integer> list, boolean[] pos) {
		if (list.size() == arr.length) {
			System.out.print(list + " ");
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (!pos[i]) {
					list.add(arr[i]);
					pos[i] = true;
					permutations(arr, list, pos);
					list.remove(list.size() - 1);
					pos[i] = false;
				}
			}
		}
	}

	void printAllPermutationsII(int[] arr) {
		permutations(arr, 0);
	}

	// TC:- O(n! * n)
	// SC:- O(n)
	private void permutations(int[] arr, int idx) {
		if (idx == arr.length) {
			System.out.println(Arrays.toString(arr));
		} else {
			for (int i = idx; i < arr.length; i++) {
				swap(i, idx, arr);
				permutations(arr, idx + 1);
				swap(i, idx, arr);
			}
		}
	}

	private void swap(int i, int idx, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[idx];
		arr[idx] = temp;
	}

}
