package com.dsalgo.ds.heaps;

public class MinHeapTest {

	public static void main(String[] args) {
		Heap<Integer> heap = new MinHeap<>();

		heap.insert(10);
		heap.insert(20);
		heap.insert(30);
		heap.insert(5);

		System.out.println(heap);
		
		heap.delete();
		
		System.out.println(heap);
	}

}
