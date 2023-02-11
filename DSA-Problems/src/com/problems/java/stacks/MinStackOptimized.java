package com.problems.java.stacks;

public class MinStackOptimized {

	public static void main(String[] args) {
		MinStackOptimized minStack = new MinStackOptimized();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); // return -3
		minStack.pop();
		System.out.println(minStack.top()); // return 0
		System.out.println(minStack.getMin()); // return -2
	}

	private class Node {
		int val;
		int min;
		Node next;

		Node(int val, int min, Node next) {
			this.val = val;
			this.min = min;
			this.next = next;
		}
	}

	private Node head;

	public void push(int val) {
		if (head == null) {
			head = new Node(val, val, null);
		} else {
			head = new Node(val, Math.min(val, head.min), head);
		}
	}

	public void pop() {
		if (head != null)
			head = head.next;
	}

	public int top() {
		if (head != null) {
			return head.val;
		}
		return -1;
	}

	public int getMin() {
		if (head != null) {
			return head.min;
		}
		return -1;
	}
}
