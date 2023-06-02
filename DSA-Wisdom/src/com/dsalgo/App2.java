package com.dsalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class App2 {

	public static void main(String[] args) {
		int ans = 0;

		for (int i = 1; i <= 25; i++) {
			ans ^= i;
			System.out.println(i + " - > " + ans);
		}
		int[] arr = { 2, 3, 3, 3, 6 };

		System.out.println(Arrays.binarySearch(arr, 3));

		String str = "dwmodizxvvbosxxw";
		String[] strArr = { "ox", "lb", "diz", "gu", "v", "ksv", "o", "nuq", "r", "txhe", "e", "wmo", "cehy", "tskz",
				"ds", "kzbu" };
		Arrays.sort(strArr, (a, b) -> b.length() - a.length());
		System.out.println(minExtraChar(str, strArr));

		System.out.println(minExtraCharBrute(str, strArr));
	}

	public static int minExtraCharBrute(String s, String[] dictionary) {

		for (String word : dictionary) {
			s = s.replaceAll(word, "");
		}

		return s.length();
	}

	public static int minExtraChar(String s, String[] dictionary) {

		for (String word : dictionary) {
			int i = -1;
			if ((i = s.indexOf(word)) != -1) {
				StringBuilder str = new StringBuilder();

				for (int j = 0; j < i; j++) {
					str.append(s.charAt(j));
				}
				int i1 = 0;
				while (i1 < word.length() && s.charAt(i) == word.charAt(i1)) {
					i++;
					i1++;
				}

				for (int j = i; j < s.length(); j++) {
					str.append(s.charAt(j));
				}

				s = str.toString();
			}
		}

		return s.length();
	}

	public boolean isBipartite(int[][] graph) {

		int n = graph.length;

		char[] color = { 'R', 'G' };

		boolean[] vis = new boolean[n];

		char[] mark = new char[n];

		Queue<Integer> queue = new LinkedList<>();

		queue.add(0);
		int i = 0;
		while (!queue.isEmpty()) {
			int node = queue.poll();
			vis[node] = true;
			mark[node] = color[i];
			i = (i + 1) % color.length;
			for (int adj : graph[node]) {
				if (!vis[adj]) {
					queue.add(adj);
				} else if (mark[adj] != color[i]) {
					return false;
				}
			}
		}

		return true;
	}

	private void sample() {
		try {
			List<Integer> list = new ArrayList<>();

			for (int i = 1; i <= 10; i++)
				list.add(i);

			Set<Integer> set1 = new TreeSet<>();
			Set<Integer> set2 = new TreeSet<>();

			Thread t1 = new Thread() {

				@Override
				public void run() {
					for (int i : list) {
						String name = Thread.currentThread().getName();
						try {
							add(set1, set2, i, name);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			};

			Thread t2 = new Thread() {

				@Override
				public void run() {
					for (int i : list) {
						String name = Thread.currentThread().getName();
						try {
							add(set1, set2, i, name);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};

			t1.start();
			t2.start();

			t1.join();
			t2.join();
			System.out.println(set1);
			System.out.println(set2);
		} catch (InterruptedException e) {
			Thread.dumpStack();
		}
	}

	private void add(Set<Integer> set1, Set<Integer> set2, int num, String tName) throws InterruptedException {
		if (num % 2 == 0) {
			System.out.println(tName + " accessing set1");
			set1.add(num);
		} else {
			System.out.println(tName + " accessing set2");
			set2.add(num % 11);
		}
	}
}
