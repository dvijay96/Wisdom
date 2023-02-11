package com.problems.java.queues;

public class ArrayQueue {

	public static void main(String[] args) {
		ArrayQueue q = new ArrayQueue();
		q.push(2);
		q.push(3);
		System.out.println(q.pop());
		q.push(4);
		System.out.println(q.pop());

	}

	int front, rear;
	int arr[] = new int[100005];

	ArrayQueue() {
		front = -1;
		rear = -1;
	}

	// Function to push an element x in a queue.
	void push(int x) {
		if (front == -1 && rear == -1) {
			front = rear = 0;
			arr[rear] = x;
		} else {
			if (rear < arr.length - 1)
				arr[++rear] = x;
		}
	}

	// Function to pop an element from queue and return that element.
	int pop() {
		if (front == -1 || rear == -1) {
			return -1;
		} else if (front == rear) {
			int val = arr[front];
			front = rear = -1;
			return val;
		} else {
			return arr[front++];
		}
	}
}
