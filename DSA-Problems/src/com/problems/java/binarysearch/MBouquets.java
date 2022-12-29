package com.problems.java.binarysearch;

import java.util.Arrays;

//Return the minimum number of days you need to wait to be able to make m bouquets with k adjacent flowers from the garden. If it is impossible to make m bouquets return -1.
public class MBouquets {

	public static void main(String[] args) {

		int[] arr = { 1, 10, 3, 10, 2 };
		int m = 3;
		int k = 1;

		System.out.println(minDays(arr, m, k));

		m = 3;
		k = 2;
		System.out.println(minDays(arr, m, k));

		m = 2;
		k = 3;
		arr = new int[] { 7, 7, 7, 7, 12, 7, 7 };
		System.out.println(minDays(arr, m, k));
	}

	/*
	 * 
	 * Naive Approach : would be to start from the day 1 and check if we could make
	 * m bouquets.
	 *  If not then increment to next next and so on till one day when we
	 * 	could make the desired bouquets hence the ans
	 */
	// We notice that the we have to return min days in which we could make m
	// bouquets with k flowers each.
	// min could be 1 and max could be the final day when the all flowers have
	// bloomed.
	// Since days are in asc increasing order we can apply BS while searching the
	// the min no. of days.
	public static int minDays(int[] bloomDay, int m, int k) {

		int min = 1;
		int max = Arrays.stream(bloomDay).max().getAsInt();

		if ((long) m * (long) k > (long) bloomDay.length)
			return -1;
		while (min <= max) {
			int mid = min + (max - min) / 2;

			int boq = 0;
			int flow = 0;

			for (int i = 0; i < bloomDay.length; i++) {
				if (bloomDay[i] <= mid)
					flow++;
				else {
					if (flow == k) {
						boq++;
					}
					flow = 0;
				}
				if (flow == k) {
					boq++;
					flow = 0;
				}
			}

			if (boq < m)
				min = mid + 1;
			else
				max = mid - 1;
		}
		return min;
	}

}
