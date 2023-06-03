package com.dsalgo.ds.design;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class DataStreamMedian {

//	The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the 
//	mean of the two middle values.
//
//	For example, for arr = [2,3,4], the median is 3.
//	For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
//	Implement the MedianFinder class:
//
//	MedianFinder() initializes the MedianFinder object.
//	void addNum(int num) adds the integer num from the data stream to the data structure.
//	double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
//	 
//
//	Example 1:
//
//	Input
//	["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//	[[], [1], [2], [], [3], []]
//	Output
//	[null, null, null, 1.5, null, 2.0]
//
//	Explanation
//	MedianFinder medianFinder = new MedianFinder();
//	medianFinder.addNum(1);    // arr = [1]
//	medianFinder.addNum(2);    // arr = [1, 2]
//	medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//	medianFinder.addNum(3);    // arr[1, 2, 3]
//	medianFinder.findMedian(); // return 2.0
//	 
//
//	Constraints:
//
//	-105 <= num <= 105
//	There will be at least one element in the data structure before calling findMedian.
//	At most 5 * 104 calls will be made to addNum and findMedian.
//	 
//
//	Follow up:
//
//	If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
//	If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<>();

		ThreadLocalRandom random = ThreadLocalRandom.current();

		DataStreamMedian obj = new DataStreamMedian();

		while (set.size() < 10) {
			int num = random.nextInt(1, 100);
			if (num > 50) {
				if (!set.isEmpty()) {
					System.out.println("Data Stream: " + set);
					System.out.println("Median:- " + obj.getMedian());
				}
			} else {
				set.add(num);
				obj.push(num);
			}
		}

		System.out.println(obj.getMedian());
	}

	private PriorityQueue<Double> smallerHalf;
	private PriorityQueue<Double> largerHalf;

	DataStreamMedian() {
		smallerHalf = new PriorityQueue<>((a, b) -> b.compareTo(a));
		largerHalf = new PriorityQueue<>();
	}

	public void push(int num) {
		Double dNum = Double.valueOf(num);
		if (smallerHalf.isEmpty() || smallerHalf.peek().compareTo(dNum) > 0) {
			smallerHalf.add(dNum);
		} else {
			largerHalf.add(dNum);
		}

		while (Math.abs(smallerHalf.size() - largerHalf.size()) > 1) {
			if (smallerHalf.size() > largerHalf.size()) {
				largerHalf.add(smallerHalf.poll());
			} else {
				smallerHalf.add(largerHalf.poll());
			}
		}
	}

	public double getMedian() {
		int size = smallerHalf.size() + largerHalf.size();

		if (size % 2 == 0) {
			return (smallerHalf.peek() + largerHalf.peek()) / 2;
		} else {
			return smallerHalf.size() > largerHalf.size() ? smallerHalf.peek() : largerHalf.peek();
		}
	}
}
