package com.dsalgo.ds.design;

import java.util.LinkedList;
import java.util.List;

public class DesignHashSet {

	public static void main(String[] args) {

		MyHashSet set = new MyHashSet();

		set.add(1);
		set.add(2);
		set.remove(1);
		System.out.println(set.contains(1));
		System.out.println(set.contains(2));
	}

}

class MyHashSet {

	private List<Integer>[] table;
	private int size = 50;

	public MyHashSet() {
		this.table = new LinkedList[size];

		for (int i = 0; i < size; i++) {
			table[i] = new LinkedList<>();
		}
	}

	public int hash(int key) {
		return key % size;
	}

	public void add(int key) {
		int hash = hash(key);

		if (table[hash].isEmpty() || !table[hash].contains(key)) {
			table[hash].add(key);
		}
	}

	public void remove(int key) {
		int hash = hash(key);

		table[hash].remove(Integer.valueOf(key));
	}

	public boolean contains(int key) {
		int hash = hash(key);

		return table[hash].contains(key);
	}
}