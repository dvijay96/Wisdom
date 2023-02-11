package learnings.java.multithreading;

import java.util.ArrayList;
import java.util.List;

public class RunnableDemo {

	public static void main(String[] args) throws InterruptedException {

		List<Integer> list = new ArrayList<>();
		Runnable thread = () -> {
			for (int i = 1; i <= 2000; i++)
				list.add(i);
			System.out.println("Runnable Lambda Completed!!!!\t" + Thread.currentThread().getName());
			System.out.println("Runnable Lambda Deamon = " + Thread.currentThread().isDaemon());
		};
		Thread t = new Thread(thread);
		t.setDaemon(true);
		t.start();

		t = new Thread(new DemoRunThread(list));
		t.setDaemon(true);
		t.start();

		for (int i = 1; i <= 20; i++)
			list.add(i);
		System.out.println("Main thread Completed \t" + Thread.currentThread().getName());
		System.out.println("Main thread Deamon = " + Thread.currentThread().isDaemon());

//		t.join();
		System.out.println(list.size());
		System.out.println("Exit!!!");

	}

}

class DemoRunThread implements Runnable {

	List<Integer> set;

	DemoRunThread(List<Integer> set) {
		this.set = set;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++)
			set.add(i);
		System.out.println("Runnable Implemented Completed \t" + Thread.currentThread().getName());
		System.out.println("Runnable Implemented Deamon = " + Thread.currentThread().isDaemon());

	}

}