package com.problems.heaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.problems.java.utility.ArrayUtils;

public class RankTransformofAnArray {

//	Given an array of integers arr, replace each element with its rank.
//
//	The rank represents how large the element is. The rank has the following rules:
//
//	Rank is an integer starting from 1.
//	The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
//	Rank should be as small as possible.
//	 
//
//	Example 1:
//
//	Input: arr = [40,10,20,30]
//	Output: [4,1,2,3]
//	Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
//	Example 2:
//
//	Input: arr = [100,100,100]
//	Output: [1,1,1]
//	Explanation: Same elements share the same rank.
	public static void main(String[] args) {
		int[] arr = new int[10];

		ArrayUtils.fillRandom(arr);
		ArrayUtils.print(arr);

		ArrayUtils.print(arrayRankTransform(arr));

		ArrayUtils.print(bruteForce(arr));
	}

//	Idea is to store a copy of the original array.
//	Sort the copy array.
//	Store each elements rank from sorted array into a map.
//	Transform the original array using the map.

//	TC:- O( n*Log(n) )		SC:- O(n)
	public static int[] arrayRankTransform(int[] arr) {
		int[] temp = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}

		Arrays.sort(temp);

		Map<Integer, Integer> rankMap = new HashMap<>();

		int[] ans = new int[arr.length];
		int rank = 1;
		for (int n : temp) {
			if (!rankMap.containsKey(n)) {
				rankMap.put(n, rank++);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			ans[i] = rankMap.get(arr[i]);
		}

		return ans;
	}

//	Calculate for each number, check how many numbers are smaller than curr number.
//	That would be his rank.
	public static int[] bruteForce(int[] arr) {
		int[] ans = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			Set<Integer> set = new HashSet<>(); // set to avoid duplicates
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] > arr[j])
					set.add(arr[j]);
			}
			ans[i] = set.size() + 1;
		}
		return ans;
	}
}
