package com.dsalgo.ds.design;

import java.util.PriorityQueue;

//	Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//	
//	Implement KthLargest class:
//	
//	* KthLargest(int k, int[] nums) - Initializes the object with the integer k and the stream of integers nums.
//	
//	* int add(int val) - Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
//	 
//	
//	Example 1:
//	
//	Input
//	["KthLargest", "add", "add", "add", "add", "add"]
//	[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
//	Output
//	[null, 4, 5, 5, 8, 8]
//	
//	Explanation
//	KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
//	kthLargest.add(3);   // return 4
//	kthLargest.add(5);   // return 5
//	kthLargest.add(10);  // return 5
//	kthLargest.add(9);   // return 8
//	kthLargest.add(4);   // return 8	
public class KthLargestElementInStream {

	private int[] stream;
	private int k;
	private PriorityQueue<Integer> pq;

//	Initializes the object with the integer k and the stream of integers nums.
	public KthLargestElementInStream(int k, int[] nums) {
		this.stream = nums;
		this.k = k;
		this.pq = new PriorityQueue<>();
		init();
	}

	private void init() {
		for (int i : stream) {
			pq.add(i);

			while (!pq.isEmpty() && pq.size() > k) {
				pq.poll();
			}
		}
	}

//	Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
	public int add(int val) {
		if (pq.size() < k) {
			pq.add(val);
		} else if (val > pq.peek()) {
			pq.poll();
			pq.add(val);
		}
		return pq.peek();
	}
}
