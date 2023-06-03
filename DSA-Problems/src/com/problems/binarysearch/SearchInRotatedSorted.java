package com.problems.binarysearch;

public class SearchInRotatedSorted {

	public static void main(String[] args) {
		int[] arr = { 3, 1 };

		System.out.println(search(arr, 1));

	}

	// TC: O(logn)
	// 1. Using binary search
	// 2. check if target at mid, then return mid
	// 3. if not, check which half is sorted , right or left
	// 4. check if target is in range of the sorted half
	// 5. If not shift to the other half.
	// 6. Continue the process till low<=high
	public static int search(int[] nums, int target) {
		int low = 0;
		int high = nums.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (nums[mid] == target)
				return mid;

			if (nums[low] <= nums[mid]) {
				if (nums[low] <= target && nums[mid] >= target) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (nums[mid] <= target && nums[high] >= target)
					low = mid + 1;
				else
					high = mid - 1;
			}
		}
		return -1;
	}
}
