package com.problems.java.binarysearch;

//Efficient Approach:-
//	Applying BS because of expected ans being monotonic in nature
// 	We need to find the min rate at which we can eat all the bananas inside the given hours.
// 	So starting, our least min rate can be 1 and max rate can be max of the piles[i].
//	if we took mid integer and check if time taken at this rate is less or greater.
//	if less, then it could be the desired rate but we must check for another possible less value. So we decrement max/high.
//	if greater, then our rate of eating is too low, so we must increase our rate, so we increment min/low.
public class KokoBanana {

	public static void main(String[] args) {
		int[] arr = { 805306368, 805306368, 805306368 };
		int h = 1000000000;

		System.out.println(minEatingSpeed(arr, h));
		System.out.println(naiveApproach(arr, h));

	}

	// Finding the min integer k, such that we can eat all the bananas inside the hours or exactly in those hours.
	private static int naiveApproach(int[] arr, int h) {
		int max = getMax(arr);
		int ans = 1;
		for (int i = 1; i <= max; i++) {
			if (canEatAll(arr, i, h)) {
				ans = i;
				break;
			}
		}
		return ans;
	}

	// TC: O(nlog(max))
	public static int minEatingSpeed(int[] piles, int h) {

		int min = 1;
		int max = getMax(piles); // O(n)

		// O(log(max))
		while (min <= max) {
			int mid = min + (max - min) / 2;

			// O(n)
			if (canEatAll(piles, mid, h)) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}

		}
		return min;
	}

	private static boolean canEatAll(int[] arr, int k, int h) {
		long hours = 0;

		for (int i : arr) {
			hours += i / k;
			if (i % k != 0)
				hours++;
		}
		return hours <= h;
	}

	private static int getMax(int[] arr) {
		int max = arr[0];
		for (int i : arr) {
			if (i > max)
				max = i;
		}
		return max;
	}
}
