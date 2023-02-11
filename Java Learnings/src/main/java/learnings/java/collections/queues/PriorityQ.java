package learnings.java.collections.queues;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQ {

	static class Node {
		int key;
		int value;
		int freq;

		public Node(int key, int value, int freq) {
			this.key = key;
			this.value = value;
			this.freq = freq;
		}

		@Override
		public String toString() {
			return "{ " + key + ", " + value + ", " + freq + " }";
		}

	}

	public static void main(String[] args) {

		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.freq - o2.freq;
			}
		});

		queue.add(new Node(1, 1, 4));
		queue.add(new Node(2, 2, 5));
		queue.add(new Node(3, 3, 1));
		queue.add(new Node(4, 4, 7));
		queue.add(new Node(5, 5, 3));
		queue.add(new Node(6, 6, 2));
		queue.add(new Node(7, 7, 5));

		System.out.println(queue);
	}

}
