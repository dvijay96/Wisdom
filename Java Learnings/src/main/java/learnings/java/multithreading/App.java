package learnings.java.multithreading;

import java.util.concurrent.ConcurrentLinkedQueue;

public class App {

	public static void main(String[] args) {

		ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

		ProducerI p = new ProducerI(queue);
		ConsumerI c = new ConsumerI(queue);

		p.run();
		c.run();

	}
}

class ProducerI implements Runnable {

	private ConcurrentLinkedQueue<Integer> queue;

	ProducerI(ConcurrentLinkedQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		int i = 0;
		while (i <= 10) {
			try {
				System.out.println("Produced " + i);
				queue.offer(i++);
			} catch (Exception e) {
				System.out.println("Exception while producing " + e.getMessage());
			}
		}

	}
}

class ConsumerI implements Runnable {

	private ConcurrentLinkedQueue<Integer> queue;

	ConsumerI(ConcurrentLinkedQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (!queue.isEmpty()) {
			try {
				System.out.println("Consumed " + queue.poll());
			} catch (Exception e) {
				System.out.println("Exception while consuming " + e.getMessage());
			}
		}
	}
}