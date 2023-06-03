package com.problems.recursions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

//	Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
//
//			The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the 
//			frequency
//			 of at least one of the chosen numbers is different.
//
//			The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

//			Example 1:
//
//			Input: candidates = [2,3,6,7], target = 7
//			Output: [[2,2,3],[7]]
//			Explanation:
//			2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//			7 is a candidate, and 7 = 7.
//			These are the only two combinations.	
	public static void main(String[] args) {
		int[] arr = { 2, 3, 6, 7 };
		int target = 7;

		ArrayList<Integer> list = new ArrayList<>();
		for (int i : arr) {
			list.add(i);
		}
		System.out.println(combinationSum(list, target));
	}

	// Approach is same i.e pick & not pick
	// Here we can pick the same element multiple times so, if the curr element
	// could be added up to sum up to target
	// we would keep on picking it
	// else we would not pick it and move forward
	// So at each point, each element will have 2 choices to pick or not pick , but
	// also with pick, we can continue picking
	// the same element until our target is reached .

	// Ex: arr = {1} target=2
	// Recursive tree would look like
	// let say func(indx , taget, subseq)
//	
// 										fun(0,2,[])
//						picked 0th indx	____|_______not pick 0th indx
//   								   |            |
//	                                   |            |
//	                           fun(0,1,[1])        fun(1,3,[])     <-- indx == arr.length && target!=0 so stops.
//	                                   |
//	                          _________|_________
//	                         |                   |
//						     |					 |
//						fun(0,0,[1,1])		fun(1,1,[])
//							|
//							|
//						Since target == 0, thus seq [1,1] is one of our ans.

// 	TC:- O(2^t * k) where t = max no. of times pick condition was taken for a element
// 						  k = avg length of the sequence/ combination
// 	SC:- O(k*x) where x = no. of combinations
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		combinationSum(candidates, 0, target, new ArrayList<>(), ans);
		return ans;
	}

	private void combinationSum(int[] arr, int i, int target, List<Integer> list, List<List<Integer>> ans) {
//	        if(target == 0){
//	            ans.add(new ArrayList<>(list));
//	            return ; 
//	        }
		if (i == arr.length) {
			if (target == 0) {
				ans.add(new ArrayList<>(list));
			}
			return;
		}

		if (arr[i] <= target) {
			list.add(arr[i]);
			combinationSum(arr, i, target - arr[i], list, ans);
			list.remove(list.size() - 1);
		}
		combinationSum(arr, i + 1, target, list, ans);
	}

	// GFG
	// Unique Combinations in asc order
	static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
		Collections.sort(A);
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		combinationSum(A, 0, new ArrayList<>(), ans, B, new HashSet<>());
		return ans;
	}

	static void combinationSum(ArrayList<Integer> ar, int i, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ans,
			int target, Set<ArrayList<Integer>> set) {

		if (target == 0 && !set.contains(list)) {
			ArrayList<Integer> seq = new ArrayList<>(list);
			ans.add(seq);
			set.add(seq);
			return;
		}

		while (i < ar.size() && target - ar.get(i) >= 0) {
			list.add(ar.get(i));
			combinationSum(ar, i, list, ans, target - ar.get(i), set);
			i++;
			list.remove(list.size() - 1);
		}
	}
}
