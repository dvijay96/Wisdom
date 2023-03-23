package com.problems.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.problems.java.utility.ArrayUtils;

public class Test {

	public static void main(String[] args) throws ParseException {

		int[] nums = { 26, 76, 24, 96, 82, 97, 97, 72 };
		int[][] temp = new int[nums.length][2];

		for (int i = 0; i < nums.length; i++) {
			temp[i][0] = nums[i];
		}

		Arrays.sort(temp, (a, b) -> a[0] - b[0]);

		ArrayUtils.print(temp);

		System.out.println(Integer.toBinaryString(2));

		System.out.println("ab".indexOf("."));

		System.out.println((Integer.valueOf("16") - Integer.valueOf("12")));

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date d1 = sdf.parse("23:57:55");
		Date d2 = sdf.parse("23:58:03");

		System.out.println(d1);
		System.out.println(d2);
		System.out.println((d2.getTime() - d1.getTime())/1000);
		
	}

//	private static int binaryToInteger(String str, List<Integer> list) {
//		int ans = 0;
//		int n = str.length() - 1;
//		for (int i = 0; i <= n; i++) {
//			ans += (list.get(n - i) * (str.charAt(i) - '0'));
//		}
//		return ans;
//	}
//
//	static int pow(int x, int n) {
//		int ans = 1;
//		while (n > 0) {
//			if (n % 2 == 0) {
//				x = x * x;
//				n = n / 2;
//			} else {
//				ans = ans * x;
//				n = n - 1;
//			}
//		}
//		return ans;
//	}
//
//	private static List<List<Integer>> subsets(int{} arr) {
//		List<List<Integer>> ans = new ArrayList<>();
//		subsets(arr, ans, new ArrayList<>(), 0);
//		return ans;
//	}
//
//	private static void subsets(int{} arr, List<List<Integer>> ans, List<Integer> list, int idx) {
//		ans.add(new ArrayList<>(list));
//		for (int i = idx; i < arr.length; i++) {
//			list.add(arr{i});
//			subsets(arr, ans, list, i + 1);
//			list.remove(list.size() - 1);
//		}
//	}
//
//	public static int peakElement(int{} arr, int n) {
//
//		if (n == 1) {
//			return 0;
//		}
//		if (arr{0} > arr{1}) {
//			return 0;
//		}
//		if (arr{n - 1} > arr{n - 2}) {
//			return n - 1;
//		}
//
//		int low = 0;
//		int high = n - 1;
//
//		while (low < high) {
//
//			int mid = low + (high - low) / 2;
//
//			if (arr{mid} > arr{mid - 1} && arr{mid} > arr{mid + 1}) {
//				return mid;
//			}
//			if (arr{mid - 1} > arr{mid}) {
//				high = mid - 1;
//			} else {
//				low = mid + 1;
//			}
//		}
//		return low;
//	}
}
