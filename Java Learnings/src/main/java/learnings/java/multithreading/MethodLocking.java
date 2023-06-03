package learnings.java.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MethodLocking {

	public static void main(String[] args) {

		Demo obj1 = new Demo();
		Demo obj2 = new Demo();

		ExecutorService service = Executors.newFixedThreadPool(2);

		service.submit(Demo::print);
		service.submit(obj1::printII);
		service.submit(obj2::printII);
		service.submit(Demo::print);

		service.shutdown();

		while (!service.isTerminated()) {
			// wait
		}

	}

}

class Demo {

	public static synchronized void print() {
		for (int i = 0; i < 10; i++) {
			System.out.println(" static synchronised " + i + " " + System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	public synchronized void printII() {
		for (int i = 0; i < 10; i++) {
			System.out.println(" synchronised " + i + " " + System.currentTimeMillis());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
}