package com.problems.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

public class App {

	public static void main(String[] args) {

		List<Integer> list = new CopyOnWriteArrayList<>();

		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}

		for (int i : list) {
			System.out.print(i + " ");
			list.add(i * i);
		}

		System.out.println(list);
	}

	private int ans = Integer.MAX_VALUE;

	public int findShortestCycle(int n, int[][] edges) {

		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		boolean[] vis = new boolean[n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!vis[j]) {
					dfs(j, adj, vis, 0, new HashMap<>(), -1);
				}
			}
		}

		if (ans == Integer.MAX_VALUE) {
			return -1;
		}
		return ans;
	}

	boolean dfs(int src, List<List<Integer>> adj, boolean[] vis, int dis, Map<Integer, Integer> map, int parent) {
		vis[src] = true;
		map.put(src, dis);

		for (int node : adj.get(src)) {
			if (!vis[node]) {
				if (dfs(node, adj, vis, dis + 1, map, src))
					return true;
			} else if (parent != -1 && node != parent) {
				int len = dis - map.get(node) + 1;
				ans = Math.min(len, ans);
				return true;
			}
		}
		return false;
	}

	static List<List<Object>> getSubsequences(int[] arr) {
		List<List<Object>> ans = new ArrayList<>();
		subsequences(arr, ans, new ArrayList<>(), 0);
		return ans;
	}

	/**
	 * 
	 * @param arr
	 * @param ans
	 * @param list
	 * @param idx
	 */
	static void subsequences(int[] arr, List<List<Object>> ans, List<Object> list, int idx) {
		ans.add(new ArrayList<>(list));
		for (int i = idx; i < arr.length; i++) {
			list.add(arr[i]);
			subsequences(arr, ans, list, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		permute(nums, ans, 0);
		return ans;
	}

	void permute(int[] nums, List<List<Integer>> ans, int idx) {
		if (idx == nums.length) {
			List<Integer> list = new ArrayList<>();
			for (int i : nums) {
				list.add(i);
			}
			ans.add(list);
		} else {
			for (int i = idx; i < nums.length; i++) {
				swap(nums, i, idx);
				permute(nums, ans, idx + 1);
				swap(nums, idx, i);
			}
		}
	}

	void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void generate(int n, StringBuilder str) {
		if (str.length() == n) {
			if (isValid(str))
				System.out.println(str);
		} else {
			str.append('0');
			generate(n, str);
			str.deleteCharAt(str.length() - 1);
			str.append('1');
			generate(n, str);
			str.deleteCharAt(str.length() - 1);
		}
	}

	private static boolean isValid(StringBuilder str) {
		int i = 0;
		int sum1 = 0;
		while (i <= str.length() / 2) {
			sum1 += (str.charAt(i++) - '0');
		}
		int j = str.length() / 2 + 1;
		int sum2 = 0;
		while (j < str.length()) {
			sum2 += (str.charAt(j++) - '0');
		}
		return sum1 == sum2;
	}

	static void generateMap(int[] arr, int start, int end, Map<Integer, Integer> map) {
		if (start <= end) {
			int mid = start + (end - start) / 2;
			map.put(arr[mid], map.getOrDefault(arr[mid], 0) + 1);
			generateMap(arr, start, mid - 1, map);
			generateMap(arr, mid + 1, end, map);
		}
	}

	static void quickSort(ArrayList<Integer> arr, int low, int high) {
		if (low < high) {
			int pivot = partition(arr, low, high);
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}

	static int partition(ArrayList<Integer> arr, int low, int high) {
		int pivot = arr.get(high);
		int i = low;
		int start = low;
		int end = high;

		while (i <= end) {
			if (arr.get(i) < pivot) {
				int temp = arr.get(i);
				arr.set(i, arr.get(start));
				arr.set(start, temp);
				start++;
				i++;
			} else if (arr.get(i) > pivot) {
				int temp = arr.get(i);
				arr.set(i, arr.get(end));
				arr.set(end, temp);
				end--;
			} else {
				i++;
			}
		}
		return start;
	}

	public static boolean wordPattern(String pattern, String s) {

		Map<Character, List<Integer>> patternMap = new LinkedHashMap<>();

		for (int i = 0; i < pattern.length(); i++) {
			if (patternMap.containsKey(pattern.charAt(i))) {
				patternMap.get(pattern.charAt(i)).add(i);
			} else {
				List<Integer> indexList = new ArrayList<>();
				indexList.add(i);
				patternMap.put(pattern.charAt(i), indexList);
			}
		}

		Map<String, List<Integer>> stringMap = new LinkedHashMap<>();

		String[] strArr = s.split(" ");

		for (int i = 0; i < strArr.length; i++) {
			if (stringMap.containsKey(strArr[i])) {
				stringMap.get(strArr[i]).add(i);
			} else {
				List<Integer> indexList = new ArrayList<>();
				indexList.add(i);
				stringMap.put(strArr[i], indexList);
			}
		}
		for (Entry<Character, List<Integer>> e : patternMap.entrySet()) {

		}
		if (patternMap.size() == stringMap.size()) {

			List<List<Integer>> patternIndices = new ArrayList<>(patternMap.values());
			List<List<Integer>> wordIndices = new ArrayList<>(stringMap.values());

			boolean isMatch = true;
			for (int i = 0; i < patternIndices.size(); i++) {
				if (!patternIndices.get(i).equals(wordIndices.get(i))) {
					isMatch = false;
				}
			}
			return isMatch;
		}

		return false;
	}

	private static void sort(List<Student> list, int s, int e) {

		if (s >= e)
			return;
		int m = s + (e - s) / 2;
		sort(list, s, m);
		sort(list, m + 1, e);
		merge(list, s, m, e);
	}

	private static void merge(List<Student> list, int s, int m, int e) {
		Student[] arr = new Student[e - s + 1];
		int i = s;
		int j = m + 1;
		int k = 0;

		while (i <= m && j <= e) {
			if (list.get(i).age < list.get(j).age)
				arr[k++] = list.get(i++);
			else
				arr[k++] = list.get(j++);
		}

		while (i <= m)
			arr[k++] = list.get(i++);

		while (j <= e)
			arr[k++] = list.get(j++);

		for (i = 0, j = s; i < arr.length; i++, j++) {
			list.remove(j);
			list.add(j, arr[i]);
		}

	}

	public static String checkEvenSubArrSumZero(int[] arr) {
		for (int i = 0; i < arr.length - 1; i = i + 2) {
			if (arr[i] + arr[i + 1] != 0)
				return "NO";
		}
		return "YES";
	}

}

class Student {
	String name;
	int age;

	@Override
	public String toString() {
		return "{name=" + name + ", age=" + age + "}";
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(age, name);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Student other = (Student) obj;
//		return age == other.age && Objects.equals(name, other.name);
//	}

}