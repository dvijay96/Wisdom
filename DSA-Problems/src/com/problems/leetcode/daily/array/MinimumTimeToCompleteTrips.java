package com.problems.leetcode.daily.array;

public class MinimumTimeToCompleteTrips {

//	You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
//
//	Each bus can make multiple trips successively; that is, the next trip can start immediately after completing 
//	the current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the 
//	trips of any other bus.
//
//	You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. 
//	Return the minimum time required for all buses to complete at least totalTrips trips.
//
//	 
//
//	Example 1:
//
//	Input: time = [1,2,3], totalTrips = 5
//	Output: 3
//	Explanation:
//	- At time t = 1, the number of trips completed by each bus are [1,0,0]. 
//	  The total number of trips completed is 1 + 0 + 0 = 1.
//	- At time t = 2, the number of trips completed by each bus are [2,1,0]. 
//	  The total number of trips completed is 2 + 1 + 0 = 3.
//	- At time t = 3, the number of trips completed by each bus are [3,1,1]. 
//	  The total number of trips completed is 3 + 1 + 1 = 5.
//	So the minimum time needed for all buses to complete at least 5 trips is 3.
//	
//	Example 2:
//
//	Input: time = [2], totalTrips = 1
//	Output: 2
//	Explanation:
//	There is only one bus, and it will complete its first trip at t = 2.
//	So the minimum time needed to complete 1 trip is 2.
//	 
//
//	Constraints:
//
//	1 <= time.length <= 105
//	1 <= time[i], totalTrips <= 107
//	
	public static void main(String[] args) {
		int[] time = { 39, 82, 69, 37, 78, 14, 93, 36, 66, 61, 13, 58, 57, 12, 70, 14, 67, 75, 91, 1, 34, 68, 73, 50,
				13, 40, 81, 21, 79, 12, 35, 18, 71, 43, 5, 50, 37, 16, 15, 6, 61, 7, 87, 43, 27, 62, 95, 45, 82, 100,
				15, 74, 33, 95, 38, 88, 91, 47, 22, 82, 51, 19, 10, 24, 87, 38, 5, 91, 10, 36, 56, 86, 48, 92, 10, 26,
				63, 2, 50, 88, 9, 83, 20, 42, 59, 55, 8, 15, 48, 25 };

		System.out.println(minimumTime(time, 4187));
		System.out.println(minimumTimeBruteForce(time, 4187));
	}

	public static long minimumTime(int[] time, int totalTrips) {
		long low = Long.MAX_VALUE;
		long high = 0;
		for (int i : time) {
			low = Math.min(i, low);
		}
		high = totalTrips * low;

		while (low < high) {
			long mid = low + (high - low) / 2;
			long trips = countTrips(time, mid);
			if (trips >= totalTrips) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static long minimumTimeBruteForce(int[] time, int totalTrips) {
		long ans = 0;

		int t = 1;
		while (true) {
			long trips = countTrips(time, t);
			if (trips >= totalTrips) {
				ans = t;
				break;
			}
			t++;
		}
		return ans;
	}

	private static long countTrips(int[] arr, long time) {
		long sum = 0;
		for (int t : arr) {
			sum += (time / t * 1L);
		}
		return sum;
	}
}
