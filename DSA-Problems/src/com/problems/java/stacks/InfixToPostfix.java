package com.problems.java.stacks;

import java.util.Stack;

public class InfixToPostfix {

	public static void main(String[] args) {
		String infix = "a+b*(c^d-e)^(f+g*h)-i";
		System.out.println(infixToPostfix(infix));
	}

	// 
	public static String infixToPostfix(String infix) {
		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();

		for (int i = 0; i < infix.length(); i++) {
			char ch = infix.charAt(i);

			if (Character.isLetter(ch)) {
				postfix.append(ch);
			} else if (ch == '(') {		// could also be added /* || (!stack.isEmpty() && ch=='^' && stack.peek()=='^') */
				stack.push(ch);
			} else if (ch == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				stack.pop();
			} else {
				while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
					postfix.append(stack.pop());
				}
				stack.push(ch);
			}
		}

		while (!stack.isEmpty()) {
			postfix.append(stack.pop());
		}
		return postfix.toString();
	}

	static int precedence(char ch) {
		switch (ch) {
		case '^':
			return 3;
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		default:
			return 0;
		}
	}
}
