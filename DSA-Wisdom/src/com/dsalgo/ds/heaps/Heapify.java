package com.dsalgo.ds.heaps;

import java.util.PriorityQueue;
import java.util.Queue;

import com.dsalgo.utility.ArrayUtils;

public class Heapify {

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Queue<Integer> maxHeap = new PriorityQueue<>();

		for (int i : arr) {
			maxHeap.add(i);
		}

		ArrayUtils.print(arr);

		System.out.println(maxHeap);

		minHeapify(arr);

		ArrayUtils.print(arr);

		arr[0] = arr[arr.length - 1];
		arr[arr.length - 1] = 100;
		minHeapify(arr, 0);

		ArrayUtils.print(arr);
	}

	public static void maxHeapify(int[] arr) {
		int n = arr.length;

		for (int i = n / 2; i >= 0; i--) {
			maxHeapify(arr, i);
		}
	}

	private static void maxHeapify(int[] arr, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = left + 1;

		if (left < arr.length && arr[left] > arr[largest]) {
			largest = left;
		}

		if (right < arr.length && arr[right] > arr[largest]) {
			largest = right;
		}

		if (largest != i) {
			swap(arr, i, largest);
			maxHeapify(arr, largest);
		}

	}

	public static void minHeapify(int[] arr) {
		int n = arr.length;

		for (int i = n / 2; i >= 0; i--) {
			minHeapify(arr, i);
		}
	}

	private static void minHeapify(int[] arr, int i) {
		int samllest = i;
		int left = 2 * i + 1;
		int right = left + 1;

		if (left < arr.length && arr[left] < arr[samllest]) {
			samllest = left;
		}

		if (right < arr.length && arr[right] < arr[samllest]) {
			samllest = right;
		}

		if (samllest != i) {
			swap(arr, i, samllest);
			minHeapify(arr, samllest);
		}

	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
