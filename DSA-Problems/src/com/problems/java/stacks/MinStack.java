package com.problems.java.stacks;

import java.util.Stack;

public class MinStack {

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); // return -3
		minStack.pop();
		System.out.println(minStack.top()); // return 0
		System.out.println(minStack.getMin()); // return -2
	}

	private Stack<Integer[]> stack;

	public MinStack() {
		stack = new Stack<>();
	}

	public void push(int val) {
		if (stack.isEmpty()) {
			stack.push(new Integer[] { val, val });
		} else {
			if (val < stack.peek()[1]) {
				stack.push(new Integer[] { val, val });
			} else {
				stack.push(new Integer[] { val, stack.peek()[1] });
			}
		}
	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		if (stack.isEmpty())
			return -1;
		return stack.peek()[0];
	}

	public int getMin() {
		if (stack.isEmpty())
			return -1;
		return stack.peek()[1];
	}
}