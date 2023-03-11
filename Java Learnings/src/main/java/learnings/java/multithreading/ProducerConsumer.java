package learnings.java.multithreading;

public class ProducerConsumer {

	public static void main(String[] args) {

		MyQueue queue = new MyQueue();

		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);

		p.start();
		c.start();
	}

}

class Consumer extends Thread {
	MyQueue q;

	Consumer(MyQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		while (true) {
			q.get();
		}
	}

}

class Producer extends Thread {
	MyQueue q;

	Producer(MyQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		int i = 1;
		while (true) {
			q.put(++i);
		}
	}

}

class MyQueue {
	int x;
	boolean valueInX = false;

	public synchronized void put(int m) {
		try {
			if (valueInX) {
				wait();
			} else {
				x = m;
				System.out.println("Published " + x);
				valueInX = true;
				notifyAll();
			}
		} catch (Exception ex) {
			System.out.println("Exception while pushing " + ex.getMessage());
		}
	}

	public synchronized void get() {
		try {
			if (!valueInX) {
				wait();
			} else {
				System.out.println("Consumed " + x);
				valueInX = false;
				notifyAll();
			}
		} catch (Exception ex) {
			System.out.println("Exception while consuming " + ex.getMessage());
		}
	}
}