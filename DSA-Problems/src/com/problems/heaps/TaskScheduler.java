package com.problems.heaps;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.problems.java.utility.ArrayUtils;

public class TaskScheduler {

	public static void main(String[] args) {

		char[] tasks = new char[] { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

		ArrayUtils.fillRandomRanges(tasks, 'A', 'D');
		ArrayUtils.print(tasks);

		System.out.println(leastIntervalPQApproach(2, tasks));
	}

	public static int leastIntervalPQApproach(int n, char[] tasks) {

		int[] freq = new int['Z' - 'A' + 1];

		for (char task : tasks) {
			freq[task - 'A']++;
		}

//		System.out.println(Arrays.toString(freq));

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int f : freq) {
			if (f > 0)
				pq.add(f);
		}

//		System.out.println(pq);
		LinkedList<int[]> queue = new LinkedList<>();
		int time = 0;
		while (!pq.isEmpty() || !queue.isEmpty()) {
			int process = pq.isEmpty() ? -1 : pq.poll();
			time++;
			if (process - 1 > 0) {
				queue.add(new int[] { process - 1, time + n });
			}
			if (!queue.isEmpty() && queue.peek()[1] == time) {
				pq.add(queue.poll()[0]);
			}
		}
		return time;
	}

	public static int leastIntervalGreedyApproach(char[] tasks, int k) {
		int[] counter = new int[26];
		int max = 0;
		int maxCount = 0;
		for (char task : tasks) {
			counter[task - 'A']++;
			if (max == counter[task - 'A']) {
				maxCount++;
			} else if (max < counter[task - 'A']) {
				max = counter[task - 'A'];
				maxCount = 1;
			}
		}

		int partCount = max - 1;
		int partLength = k - (maxCount - 1);
		int emptySlots = partCount * partLength;
		int availableTasks = tasks.length - max * maxCount;
		int idles = Math.max(0, emptySlots - availableTasks);

		return tasks.length + idles;
	}
}
