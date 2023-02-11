package com.problems.java.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParanthesis {
	
	public static void main(String[] args) {
		System.out.println(ispar("({[]})"));
	}

	static boolean ispar(String x) {

		if (x.charAt(0) == ')' || x.charAt(0) == '}' || x.charAt(0) == ']')
			return false;

		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < x.length(); i++) {

			if (!stack.isEmpty() && stack.peek().equals(map.get(x.charAt(i))))
				stack.pop();
			else
				stack.push(x.charAt(i));
		}
		return stack.isEmpty();
	}
}
