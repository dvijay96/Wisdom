package com.problems.stacks;

import java.util.Stack;

import com.problems.java.utility.ArrayUtils;

public class AsteroidCollision {
	
//	We are given an array asteroids of integers representing asteroids in a row.
//
//	For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
//
//	Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
//
//	 
//
//	Example 1:
//
//	Input: asteroids = [5,10,-5]
//	Output: [5,10]
//	Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
//	Example 2:
//
//	Input: asteroids = [8,-8]
//	Output: []
//	Explanation: The 8 and -8 collide exploding each other.

	public static void main(String[] args) {
		int[] arr = new int[7];

		ArrayUtils.fillRandomNegPos(arr);
		ArrayUtils.print(arr);

		int[] ans = asteroidCollision(arr);

		ArrayUtils.print(ans);
	}

	public static int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<>();
		int n = asteroids.length;
		for (int i = 0; i < n; i++) {
			int astSize = asteroids[i];
			// collision will happen only when negative comes
			if (astSize < 0) {
				collide(stack, astSize);
			} else {
				stack.push(astSize);
			}
		}

		return stack.stream().mapToInt(i -> i).toArray();
	}

	private static void collide(Stack<Integer> stack, int astSize) {
		while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(astSize) > stack.peek()) {
			stack.pop();
		}
		if (stack.isEmpty()) {
			stack.push(astSize);
		} else {
			if (stack.peek() > 0 && Math.abs(astSize) == stack.peek()) {
				stack.pop();
			} else if (stack.peek() < 0) {
				stack.push(astSize);
			}
		}
	}
}
