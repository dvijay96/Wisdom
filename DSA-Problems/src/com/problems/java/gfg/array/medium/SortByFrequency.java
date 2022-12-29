package com.problems.java.gfg.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortByFrequency {

	public static void main(String[] args) {

		int[] arr = { 5, 5, 6, 4, 4 };

		Map<Integer, Integer> map = new LinkedHashMap<>();

		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		int i = 0;
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
		map.entrySet().forEach(t -> list.add(t));

		list.sort(new Comparator<Map.Entry<Integer, Integer>>() {

//			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		for (Map.Entry<Integer, Integer> ent : list) {
			for (int j = 0; j < ent.getValue(); j++)
				arr[i++] = ent.getKey();
		}

		System.out.println(Arrays.toString(arr));
	}

}
