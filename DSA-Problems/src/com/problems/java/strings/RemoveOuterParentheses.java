package com.problems.java.strings;

import java.util.Stack;

public class RemoveOuterParentheses {

	public static void main(String[] args) {
		String str = "(()())";
		System.out.println(removeOuterParenthesesWStack(str));
		System.out.println(removeOuterParenthesesWithStack(str));
		System.out.println(removeOuterParenthesesWStackI(str));
	}

	// TS :- O(N)
	// SC :- O(1) , if we ignore the ans space
	public static String removeOuterParenthesesWStackI(String s) {
		String ans = "";
		int opened = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (opened > 0)
					ans += s.charAt(i);
				opened++;
			} else {
				if (opened > 1)
					ans += s.charAt(i);
				opened--;
			}
		}
		return ans;
	}

	// TS :- O(N)
	// SC :- O(N)
	public static String removeOuterParenthesesWStack(String s) {
		char[] r = s.toCharArray();
		int k = 0, n = r.length;
		for (int i = 0, l = 0; i < n; ++i) {
			if (r[i] == '(') {
				if (l++ > 0)
					r[k++] = r[i];
			} else if (--l > 0)
				r[k++] = r[i];
		}

		return new String(r, 0, k);
	}

	// TS :- O(N)
	// SC :- O(N)
	public static String removeOuterParenthesesWithStack(String s) {
		Stack<Character> stack = new Stack<>();
		int closed = 0, open = 0;
		String ans = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(s.charAt(i));
				open++;
			}
			if (s.charAt(i) == ')') {
				stack.push(s.charAt(i));
				closed++;
			}
			if (closed == open) {
				stack.pop();
				String temp = "";
				while (stack.size() != 1) {
					temp = stack.pop() + temp;
				}
				stack.pop();
				closed = open = 0;
				ans = ans + temp;
			}
		}
		return ans;
	}
}
