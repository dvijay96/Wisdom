package com.problems.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

import com.problems.java.utility.ArrayUtils;

public class MaxSumCombo {

	public static void main(String[] args) {
		int[] arr1 = ArrayUtils.getIntArray(10);
		int[] arr2 = ArrayUtils.getIntArray(10);

		System.out.println(maxCombinationsBruteForce(5, arr1, arr2));

		System.out.println(optimalSolution(5, arr1, arr2));
	}

	static List<Integer> maxCombinationsBruteForce(int K, int A[], int B[]) {

		Arrays.sort(A);
		Arrays.sort(B);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = A.length - 1; i >= 0; i--) {
			for (int j = B.length - 1; j >= 0; j--) {
				pq.add(A[i] + B[j]);

				if (pq.size() > K) {
					pq.poll();
				}
			}
		}

		List<Integer> ans = new ArrayList<>();

		while (!pq.isEmpty()) {
			ans.add(pq.poll());
		}
		Collections.reverse(ans);
		return ans;
	}

//	static List<Integer> maxCombinationsBruteForceII(int k, int[] a, int[] b) {
//
//		TreeSet<Integer> pq1 = new TreeSet<>();
//		for (int i : a) {
//			pq1.add(i);
//			if (pq1.size() > k) {
//				pq1.pollFirst();
//			}
//		}
//
//		TreeSet<Integer> pq2 = new TreeSet<>();
//		for (int i : b) {
//			pq2.add(i);
//			if (pq2.size() > k) {
//				pq2.pollFirst();
//			}
//		}
//
//		PriorityQueue<Integer> kMax = new PriorityQueue<>();
//
//		Iterator<Integer> itr1 = pq1.iterator();
//
//		while (itr1.hasNext()) {
//			Integer e1 = itr1.next();
//
//			Iterator<Integer> itr2 = pq2.iterator();
//
//			while (itr2.hasNext()) {
//				kMax.add(e1 + itr2.next());
//				if (kMax.size() > k) {
//					kMax.poll();
//				}
//			}
//
//		}
//		List<Integer> ans = new ArrayList<>();
//		while (!kMax.isEmpty()) {
//			ans.add(kMax.poll());
//		}
//
//		Collections.reverse(ans);
//		return ans;
//	}

	static List<Integer> optimalSolution(int k, int[] a, int[] b) {

		Arrays.sort(a);
		Arrays.sort(b);

		List<Integer> ans = new ArrayList<>();

		PriorityQueue<PairSum> pq = new PriorityQueue<>((x, y) -> y.sum - x.sum);
		Set<Pair> set = new HashSet<>();

		int aLen = a.length - 1;
		int bLen = b.length - 1;

		Pair pair = new Pair(aLen, bLen);
		pq.add(new PairSum(a[aLen] + b[bLen], pair));
		set.add(pair);

		while (ans.size() != k) {
			PairSum pairSum = pq.poll();

			ans.add(pairSum.sum);
			Pair prev = pairSum.pair;

			Pair p1 = new Pair(prev.i, prev.j - 1);
			Pair p2 = new Pair(prev.i - 1, prev.j);

			if (p1.i >= 0 && p1.j >= 0 && !set.contains(p1)) {
				pq.add(new PairSum(a[p1.i] + b[p1.j], p1));
				set.add(p1);
			}

			if (p2.i >= 0 && p2.j >= 0 && !set.contains(p2)) {
				pq.add(new PairSum(a[p2.i] + b[p2.j], p2));
				set.add(p2);
			}

		}

		return ans;
	}

}

class Pair {
	int i;
	int j;

	Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		return i == other.i && j == other.j;
	}

}

class PairSum {
	int sum;
	Pair pair;

	PairSum(int sum, Pair pair) {
		this.sum = sum;
		this.pair = pair;
	}
}
