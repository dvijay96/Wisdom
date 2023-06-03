package com.problems.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.problems.java.utility.ArrayUtils;

public class UniqueElements {

//	Getting unique elements out of and array without using any Collection classes or framework, 
//	but only using pure core java.

	public static void main(String[] args) {

		int[] arr = ArrayUtils.getIntArray(10);

		ArrayUtils.print(arr);

		printUniques(arr);

		printUniqueInOriginalSequence(arr);

		Set<Integer> set = new LinkedHashSet<>();

		for (int num : arr) {
			set.add(num);
		}

		System.out.println(set);

	}

	private static void printUniqueInOriginalSequence(int[] arr) {
		int max = arr[0];

		for (int num : arr) {
			max = Math.max(max, num);
		}
		int[][] map = new int[max + 1][3];

		for (int[] ar : map) {
			ar[2] = -1;
		}

		for (int i = 0; i < arr.length; i++) {
			map[arr[i]][0]++;
			map[arr[i]][1] = arr[i];
			if (map[arr[i]][2] == -1)
				map[arr[i]][2] = i;
		}

		List<Integer> uniques = new ArrayList<>();

		Arrays.sort(map, (a, b) -> a[2] - b[2]);

		for (int i = 0; i < map.length; i++) {
			if (map[i][0] > 0) {
				uniques.add(map[i][1]);
			}
		}

		System.out.println(uniques);
	}

	private static void printUniques(int[] arr) {
		int max = arr[0];

		for (int num : arr) {
			max = Math.max(max, num);
		}

		int[] map = new int[max + 1];

		for (int num : arr) {
			map[num]++;
		}

		List<Integer> unique = new ArrayList<>();

		for (int i = 0; i < map.length; i++) {
			if (map[i] > 0) {
				unique.add(i);
			}
		}

		System.out.println(unique);
	}

}
