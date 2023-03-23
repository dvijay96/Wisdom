package com.problems.java.utility;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public interface ArrayUtils {

	/**
	 * Fills random +ve integers in the given array where </br>
	 * <code> 1<=arr[i]<=20 </code>
	 * 
	 * @param arr
	 */
	static void fillRandom(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int num = ThreadLocalRandom.current().nextInt(1, 21);
			arr[i] = num;
		}
	}

	/**
	 * Fills random +ve integers
	 * 
	 * @param arr
	 */
	static void fillRandomII(int[] arr) {
		int start = 1;
		for (int i = 0; i < arr.length; i++) {
			int num = ThreadLocalRandom.current().nextInt(1, 20);
			arr[i] = start + num % 7;
		}
	}

	/**
	 * Fills random integers in the given array where -20<=arr[i]<=20
	 * 
	 * @param arr
	 */
	static void fillRandomNegPos(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
//			int num = -20 + (int) (Math.random() * (40) + 1);
			int num = ThreadLocalRandom.current().nextInt(-20, 21);
			arr[i] = num;
		}
	}

	/**
	 * Fills random +ve integers in the given array where </br>
	 * <code> start <= arr[i] <= end </code>
	 * 
	 * @param arr
	 * @param start - start range (inclusive)
	 * @param end   - end range (inclusive)
	 */
	static void fillRandomRange(int[] arr, int start, int end) {
		for (int i = 0; i < arr.length; i++) {
			int num = ThreadLocalRandom.current().nextInt(start, end + 1);
			arr[i] = num;
		}
	}

	/**
	 * Prints the elements of the array
	 * 
	 * @param arr
	 */
	static void print(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	/**
	 * Prints the elements of the array grid
	 * 
	 * @param arr
	 */
	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Prints the elements of the array grid
	 * 
	 * @param arr
	 */
	static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Fills the given char array with random character elements ranging from A-Z
	 * 
	 * @param ch
	 */
	static void fillRandom(char[] ch) {
		for (int i = 0; i < ch.length; i++) {
			ch[i] = (char) (ThreadLocalRandom.current().nextInt('A', 'Z' + 1));
		}
	}

	/**
	 * Fills the given char array with random character elements ranging from the
	 * given ranges
	 * 
	 * @param ch
	 * @param start - starting character
	 * @param end   - ending character (inclusive)
	 */
	static void fillRandomRanges(char[] ch, char start, char end) {
		for (int i = 0; i < ch.length; i++) {
			ch[i] = (char) (ThreadLocalRandom.current().nextInt(start, end + 1));
		}
	}

	/**
	 * Prints the character array
	 * 
	 * @param ch
	 */
	static void print(char[] ch) {
		for (char c : ch) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

	/**
	 * To get filled array of given size
	 * 
	 * @param size
	 * @return
	 */
	static int[] getIntArray(int size) {
		int[] arr = new int[size];
		fillRandom(arr);
		return arr;
	}

	/**
	 * To get filled array of given size with the given range of numbers
	 * 
	 * @param size
	 * @param start (inclusive)
	 * @param end   (inclusive)
	 * @return
	 */
	static int[] getIntArray(int size, final int start, final int end) {
		int[] arr = new int[size];
		fillRandomRange(arr, start, end);
		return arr;
	}

	/**
	 * To get filled array of given size, elements ranging from 1 to size
	 * 
	 * @param size
	 * @return
	 */
	static int[] getIntArrayUnique(int size) {
		int[] arr = new int[size];
		Set<Integer> set = new HashSet<>();
		ThreadLocalRandom current = ThreadLocalRandom.current();
		while (set.size() < size) {
			set.add(current.nextInt(1, 21));
		}
		int i = 0;
		for (Integer num : set) {
			arr[i++] = num;
		}
		return arr;
	}

	/**
	 * Shuffles the array randomly
	 * 
	 * @param arr
	 */
	static void shuffle(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int j = ThreadLocalRandom.current().nextInt(0, arr.length);
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	/**
	 * Gets a randomly filled array
	 * 
	 * @param size
	 * @return
	 */
	static char[] getCharArray(int size) {
		char[] arr = new char[size];
		fillRandom(arr);
		return arr;
	}

	/**
	 * Gets a randomly filled array where start <= arr[i] <= end
	 * 
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	static char[] getCharArrayRange(int size, char start, char end) {
		char[] arr = new char[size];
		fillRandomRanges(arr, start, end);
		return arr;
	}
}
