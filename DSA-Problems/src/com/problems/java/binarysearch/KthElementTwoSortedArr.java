package com.problems.java.binarysearch;

public class KthElementTwoSortedArr {

	public static void main(String[] args) {

		int[] arr1 = { 2, 3, 6, 7, 9};
		int[] arr2 = { 1, 4, 8, 10 };

		System.out.println(kthElement(arr1, arr2, 5));
	}

	public static long kthElement(int arr1[], int arr2[], int k) {

		int i = 0;
		int j = 0;
		int l = 0;
		int[] merge = new int[arr1.length + arr2.length];

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				merge[l++] = arr1[i++];
			} else {
				merge[l++] = arr2[j++];
			}
		}

		while (i < arr1.length) {
			merge[l++] = arr1[i++];
		}

		while (j < arr2.length) {
			merge[l++] = arr2[j++];
		}

		return merge[k - 1];
	}
}
