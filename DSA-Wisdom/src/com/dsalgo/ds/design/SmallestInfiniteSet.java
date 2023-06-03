package com.dsalgo.ds.design;

import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class SmallestInfiniteSet {

	public static void main(String[] args) {
		SmallestInfiniteSet obj = new SmallestInfiniteSet();

		ThreadLocalRandom random = ThreadLocalRandom.current();

		ExecutorService service = Executors.newFixedThreadPool(2);

		service.execute(() -> {
			for (int i = 1; i <= 20; i++) {
				int num = random.nextInt(21);
				System.out.println("Adding:- " + num);
				obj.addBack(num);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}
			}
		});

		service.execute(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Smallest: " + obj.popSmallest());
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}
			}
		});

		service.shutdown();

		while (!service.isTerminated()) {
			// wait
		}
	}

	private TreeSet<Integer> set;
	private int currentCounter;

	public SmallestInfiniteSet() {
		set = new TreeSet<>();
		currentCounter = 1;
	}

	public synchronized int popSmallest() {
		if (!set.isEmpty()) {
			int top = set.first();
			set.remove(top);
			return top;
		}
		return currentCounter++;
	}

	public synchronized void addBack(int num) {
		if (num >= currentCounter)
			return;
		set.add(num);
	}
}
