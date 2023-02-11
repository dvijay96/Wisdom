package com.dsalgo.ds.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeapTest {

	public static void main(String[] args) {
		Heap<Integer> heap = new MaxHeap<>();

		heap.insert(10);
		heap.insert(40);
		heap.insert(20);
		heap.insert(30);
		heap.insert(50);
		heap.insert(45);
		heap.insert(35);

		System.out.println(heap);

		heap.delete();
		System.out.println(heap);
		heap.delete();
		System.out.println(heap);

		heap.insert(60);
		heap.insert(50);
		System.out.println(heap);

		heap.delete();
		System.out.println(heap);

		heap.delete();
		System.out.println(heap);

		System.out.println("\n\n Assertion");
		assertWtihPQ();
	}

	private static void assertWtihPQ() {
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		queue.add(10);
		queue.add(40);
		queue.add(20);
		queue.add(30);
		queue.add(50);
		queue.add(45);
		queue.add(35);

		System.out.println(queue);

		queue.poll();
		System.out.println(queue);
		queue.poll();
		System.out.println(queue);

		queue.add(60);
		queue.add(50);
		System.out.println(queue);

		queue.poll();
		System.out.println(queue);

		queue.poll();
		System.out.println(queue);
	}

}
