package com.problems.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumMultiplicationsToReachEnd {

//	Given start, end and an array arr of n numbers. At each step, start is multiplied with any number in the array 
//	and then mod operation with 100000 is done to get the new start.
//
//	Your task is to find the minimum steps in which end can be achieved starting from start. If it is not possible 
//	to reach end, then return -1.
//
//	Example 1:
//
//	Input:
//	arr[] = {2, 5, 7}
//	start = 3, end = 30
//	Output:
//	2
//	Explanation:
//	Step 1: 3*2 = 6 % 100000 = 6 
//	Step 2: 6*5 = 30 % 100000 = 30
//	Example 2:
//
//	Input:
//	arr[] = {3, 4, 65}
//	start = 7, end = 66175
//	Output:
//	4
//	Explanation:
//	Step 1: 7*3 = 21 % 100000 = 21 
//	Step 2: 21*3 = 6 % 100000 = 63 
//	Step 3: 63*65 = 4095 % 100000 = 4095 
//	Step 4: 4095*65 = 266175 % 100000 = 66175

//	Constraints:
//
//		1 <= n and n <= 10^4
//		1 <= arr[i] and arr[i] <= 10^4
//		1 <= start, end < 10^5

	public static void main(String[] args) {
		MinimumMultiplicationsToReachEnd obj = new MinimumMultiplicationsToReachEnd();
		int[] arr = { 3, 4, 65 };
		int start = 7;
		int end = 66175;

		System.out.println(obj.minimumMultiplications(arr, start, end));
	}

//	Approach:-
//			-> Why this is a graph problem? Consider start and end as nodes source and distination nodes and all other numbers 
//					before/between/after them as nodes too.
//			-> We need to start from the source and need to find the next possible node by multiplying nums from the given array.
//			-> Since it is given as to mod by 10^5 in Qs, we can consider the nodes as from 0 to 10^5 - 1.
//			-> We need to perform Dikjstra's algo to find the min steps to reach the end node.
//			-> since steps are increasing in a uniformly fashion, we can use normal queue instead of PQ.
//			-> Using the above algo we need to find the min steps we can reach to end, if not then returning -1.
//	
//	TC:- O(n * 10^5)	Where ‘100000’ are the total possible numbers generated by multiplication (hypothetical) and N = size of the array 
//						with numbers of which each node could be multiplied.
//						
//	SC:- O(n * 10^5)	Where ‘100000’ are the total possible numbers generated by multiplication (hypothetical) and N = size of the array 
//						with numbers of which each node could be multiplied. 100000 * N is the max possible queue size. The space complexity 
//						of the dist array is constant.
	int minimumMultiplications(int[] arr, int start, int end) {

		int mod = 100000;
		int[] steps = new int[mod];

		Arrays.fill(steps, mod);

		Queue<int[]> pq = new LinkedList<>();

		steps[start] = 0;

		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] pair = pq.poll();
			int node = pair[0];
			int currSteps = pair[1];

			for (int n : arr) {
				int newNode = (n * node) % mod;

				if (currSteps + 1 < steps[newNode]) {
					steps[newNode] = currSteps + 1;

					if (newNode == end) {
						return currSteps + 1;
					}

					pq.add(new int[] { newNode, currSteps + 1 });
				}
			}
		}

		return -1;
	}
}
