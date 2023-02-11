package com.problems.java.slidingwindow;

import java.util.Arrays;

import com.problems.java.utility.ArrayUtils;

public class MaxPoints {

//	There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
//
//	In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
//
//	Your score is the sum of the points of the cards you have taken.
//
//	Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
//
//	 
//	Example 1:
//
//	Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//	Output: 12
//	Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
//	Example 2:
//
//	Input: cardPoints = [2,2,2], k = 2
//	Output: 4
//	Explanation: Regardless of which two cards you take, your score will always be 4.
//	Example 3:
//
//	Input: cardPoints = [9,7,7,9,7,7,9], k = 7
//	Output: 55
//	Explanation: You have to take all the cards. Your score is the sum of points of all cards.

	public static void main(String[] args) {

		int[] arr = new int[7];

		ArrayUtils.fillRandomRange(arr, 1, 10);
		ArrayUtils.print(arr);
		System.out.println(maxScore(arr, 3));
	}

//	Approach 1:- 
//		for example 1, points = {1,2,3,4,5,6,1}
//		checking all possible combos
//		1+2+3 = 6			select 3 from front, 0 from back
//		1+2+1 = 4			select 2 from front, 1 from back
//		1+1+6 = 8			select 1 from front, 2 from back
//		1+6+5 = 12			select 0 from front, 3 from back
//	
//		Every time we select any combo out of above ones, the left over elements form a sub-array of size n-k.
//		We can create a window of these n-k elements each time and calculate the actual k cards sum.
//		
//		To do that we calculate prefix sum.
//		the window sum = prefSum[right] - prefSum[left-1]
//		And the required k elements sum = total sum - windowSum.
	public static int maxScore(int[] cardPoints, int k) {

		int n = cardPoints.length;
		int[] prefSum = new int[n];
		int sum = 0;

		for (int i = 0; i < n; i++) {
			sum += cardPoints[i];
			prefSum[i] = sum;
		}

		if (k == n)
			return prefSum[n - 1];
		int score = 0;
		int left = 0;
		int right = n - k - 1;

		while (right < n) {
			int windowSum = 0;
			if (left == 0)
				windowSum = prefSum[right];
			else {
				windowSum = prefSum[right] - prefSum[left - 1];
			}
			score = Math.max(score, prefSum[n - 1] - windowSum);
			left++;
			right++;
		}
		return score;
	}

//	Approach 2:-
//	
//		Similar approach to 1 but slight change
//		
//		Instead of calculating the prefix sum for all, we can calculate prefSum of selecting first k elements
//		and then selecting last k elements.
//		Thus we get 
//		 leftSum[] -> 0,1,2,...k from left
//		rightSum[] -> 0,1,2,...k from right
//	
//		Then iterate over every possibility of choosing the k elements. i.e leftSum[i] + rightSum[k-i]
//		Find the max out of those combos.
	public static int maxScoreII(int[] cardPoints, int k) {
		// If no cards or number of cards to pick more than available just return 0
		if (cardPoints == null || k <= 0 || k > cardPoints.length)
			return 0;

		// If k equals total number of cards simply sum up all cards
		if (k == cardPoints.length)
			return Arrays.stream(cardPoints).sum();

		// Initialize max points as min val
		int maxPoints = Integer.MIN_VALUE;

		// Prefix sum of left cards - we can pick 0 or 1 or 2 ... k cards from left
		int[] left = new int[k + 1];
		for (int i = 1; i <= k; i++) {
			left[i] = left[i - 1] + cardPoints[i - 1];
		}
		// Prefix sum from right - Can pick 0, 1, ... k number of cards from right
		int[] right = new int[k + 1];
		for (int i = 1; i <= k; i++) {
			right[i] = right[i - 1] + cardPoints[cardPoints.length - i];
		}

		// Iterate over possibilities - 0 from left and remaining from right and so on
		for (int i = 0; i <= k; i++) {
			maxPoints = Math.max(maxPoints, left[i] + right[k - i]);
		}
		return maxPoints;
	}

//	Same above approach but without using any extra space.
	public int maxScoreIII(int[] cardPoints, int k) {
		int n = cardPoints.length, lSum = 0;
		for (int i = 0; i < k; ++i) {
			lSum += cardPoints[i];
		}
		int max = lSum;
		for (int rSum = 0, i = 0; i < k; ++i) {
			rSum += cardPoints[n - 1 - i]; // selecting 1 element from last
			lSum -= cardPoints[k - 1 - i]; // removing last k element from front
			max = Math.max(max, lSum + rSum);
		}
		return max;
	}
}
