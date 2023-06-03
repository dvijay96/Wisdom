package com.problems.leetcode.daily.array;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;

public class LongestValidObstacleCourse {

//	You want to build some obstacle courses. You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the 
//	height of the ith obstacle.
//
//	For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles such that:
//
//	You choose any number of obstacles between 0 and i inclusive.
//	You must include the ith obstacle in the course.
//	You must put the chosen obstacles in the same order as they appear in obstacles.
//	Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
//	Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as described above.
//
//	 
//
//	Example 1:
//
//	Input: obstacles = [1,2,3,2]
//	Output: [1,2,3,3]
//	Explanation: The longest valid obstacle course at each position is:
//	- i = 0: [1], [1] has length 1.
//	- i = 1: [1,2], [1,2] has length 2.
//	- i = 2: [1,2,3], [1,2,3] has length 3.
//	- i = 3: [1,2,3,2], [1,2,2] has length 3.
//	Example 2:
//
//	Input: obstacles = [2,2,1]
//	Output: [1,2,1]
//	Explanation: The longest valid obstacle course at each position is:
//	- i = 0: [2], [2] has length 1.
//	- i = 1: [2,2], [2,2] has length 2.
//	- i = 2: [2,2,1], [1] has length 1.
//	Example 3:
//
//	Input: obstacles = [3,1,5,6,4,2]
//	Output: [1,1,2,3,2,2]
//	Explanation: The longest valid obstacle course at each position is:
//	- i = 0: [3], [3] has length 1.
//	- i = 1: [3,1], [1] has length 1.
//	- i = 2: [3,1,5], [3,5] has length 2. [1,5] is also valid.
//	- i = 3: [3,1,5,6], [3,5,6] has length 3. [1,5,6] is also valid.
//	- i = 4: [3,1,5,6,4], [3,4] has length 2. [1,4] is also valid.
//	- i = 5: [3,1,5,6,4,2], [1,2] has length 2.

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		LongestValidObstacleCourse obj = new LongestValidObstacleCourse();

		ArrayUtils.print(arr);

		ArrayUtils.print(obj.longestObstacleCourseAtEachPosition(arr));

	}

//	Approach:- 
//			-> To find longest increasing subsequence, we need to generate such subsequence in which each adjacent element
//				is in increasing order.
//			-> To do this, we can consider the brute force approach where we generate all subsequences by pick or not pick method.
//			-> Similarly, when we pick a element for the subsequence, we need to check if adding that element to the 
//				existing subsequence satisfies the condition or not.
//			-> If satisfies, then add the element else find the pos where it must be put into the subsequence so to satisfy the condition
//			-> Thus, we'll be creating just the required subsequence rather than creating all subsequences.
//			-> Since, the subsequence we are generating is sorted, we can find the pos of the incoming element via binary search to reduce time.
//
//	TC:- O(nLogn)
//	SC:- O(n)

	public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {

		int[] ans = new int[obstacles.length];

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < obstacles.length; i++) {
			if (list.isEmpty() || obstacles[i] >= list.get(list.size() - 1)) {
				list.add(obstacles[i]);
				ans[i] = list.size();
			} else {
				int pos = findGreater(list, obstacles[i]);
				list.set(pos, obstacles[i]);
				ans[i] = pos + 1;
			}
		}

		return ans;
	}

	int findGreater(List<Integer> list, int num) {
		int low = 0;
		int high = list.size() - 1;

		while (low < high) {
			int mid = low + (high - low) / 2;

			if (list.get(mid) > num) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}
}
