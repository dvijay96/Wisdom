package com.problems.java.recursions;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {

	public static void main(String[] args) {
		System.out.println(combinationSum(4, 20));

	}

	public static List<List<Integer>> combinationSum(int k, int n) {
		List<List<Integer>> ans = new ArrayList<>();
		if (k > n)
			return ans;
		getCombos(1, ans, new ArrayList<>(), n, k);
		return ans;
	}

	static void getCombos(int idx, List<List<Integer>> ans, List<Integer> list, int target, int size) {
		if (target < 0 || list.size() > size) {

		} else if (target == 0 && list.size() == size) {
			ans.add(new ArrayList<>(list));
		} else {
			for (int i = idx; i <= 9; i++) {
				list.add(i);
				getCombos(i + 1, ans, list, target - i, size);
				list.remove(list.size() - 1);
			}
		}
	}
}
