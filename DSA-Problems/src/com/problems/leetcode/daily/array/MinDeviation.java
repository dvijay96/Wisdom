package com.problems.leetcode.daily.array;

import java.util.PriorityQueue;

public class MinDeviation {

//	You are given an array nums of n positive integers.
//
//	You can perform two types of operations on any element of the array any number of times:
//
//	If the element is even, divide it by 2.
//	For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
//	If the element is odd, multiply it by 2.
//	For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
//	The deviation of the array is the maximum difference between any two elements in the array.
//
//	Return the minimum deviation the array can have after performing some number of operations.
//
//	 
//
//	Example 1:
//
//	Input: nums = [1,2,3,4]
//	Output: 1
//	Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
//	
//	Example 2:
//
//	Input: nums = [4,1,5,20,3]
//	Output: 3
//	Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
//	
//	Example 3:
//
//	Input: nums = [2,10,8]
//	Output: 3

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

//	-> The Basic Intuition is to minimize the maxDifference between two numbers and we can decrease it by decreasing the 
//		maximum value. 
//	-> We can decrease a even value by dividing by 2 but for odd number we can only multiply by 2 so which 
//		increases the value. 
//	-> So initially we tried to convert all the odd numbers to even numbers by multiplying with 2 so that afterwards 
//		we can decrease it by dividing it by 2. 
//	-> So initially I multiplied all the odd numbers with 2 and I was also keeping a track of minimum number . I inserted 
//		all the numbers into the Priority Queue that sorts according to Max Heap. 
//	-> So after I inserted all element into Priority Queue. Then I polled out the top element of the Priority Queue 
//		and then computed the difference with the minimum value so that we can know that it's the minimum Deviation than
//		previous or not and after that I checked if it's even then i divided it by 2 to reduce the value and then checked if 
//		its the new minimum value or not. If it's the new minimum Value I updated the minimum Value. Then I inserted that top 
//		element (top element of Priority Queue divided by 2) into the Priority Queue. 
//	-> Similarly, We then repeatedly pop the maximum value from the priority queue, which guarantees that we are always 
//		reducing the maximum value in the array. 
//	-> If the maximum value is odd, we can no longer divide it by 2, so we break out of the loop. Otherwise, we divide the 
//		maximum value by 2, which reduces the maximum value, and update the minimum value accordingly. 
//		Then at last I returned the minimum Deviation.
//	
//	TC:- O(nLogn)
	public int minimumDeviation(int[] nums) {

		int diff = Integer.MAX_VALUE;
		int min = diff;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
		for (int n : nums) {
			if (n % 2 == 1) {
				n = n * 2;
			}
			maxHeap.add(n);
			min = Math.min(min, n);
		}

		while (!maxHeap.isEmpty()) {
			int max = maxHeap.poll();
			diff = Math.min(diff, max - min);
			if (max % 2 == 1) {
				break;
			}
			maxHeap.add(max / 2);
			min = Math.min(min, max / 2);
		}
		return diff;
	}
}
