package com.dsalgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class App2 {

	public static void main(String[] args) {
		App2 obj = new App2();
		obj.sample();

	}

	private void sample() {
		try {
			List<Integer> list = new ArrayList<>();

			for (int i = 1; i <= 10; i++)
				list.add(i);

			Set<Integer> set1 = new TreeSet<>();
			Set<Integer> set2 = new TreeSet<>();

			Thread t1 = new Thread() {

				@Override
				public void run() {
					for (int i : list) {
						String name = Thread.currentThread().getName();
						try {
							add(set1, set2, i, name);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			};

			Thread t2 = new Thread() {

				@Override
				public void run() {
					for (int i : list) {
						String name = Thread.currentThread().getName();
						try {
							add(set1, set2, i, name);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};

			t1.start();
			t2.start();

			t1.join();
			t2.join();
			System.out.println(set1);
			System.out.println(set2);
		} catch (InterruptedException e) {
			Thread.dumpStack();
		}
	}

	private void add(Set<Integer> set1, Set<Integer> set2, int num, String tName) throws InterruptedException {
		if (num % 2 == 0) {
			System.out.println(tName + " accessing set1");
			set1.add(num);
		} else {
			System.out.println(tName + " accessing set2");
			set2.add(num % 11);
		}
	}
}
