package com.dsalgo.ds.design;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCacheWithOutDLL {

	private LinkedHashMap<Integer, Integer> cache;
	private int capacity;

	public LRUCacheWithOutDLL(int capacity) {
		this.capacity = capacity;
		cache = new LinkedHashMap<Integer, Integer>(this.capacity, 0.75f, true) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
				return size() > capacity;
			}

		};
	}

	public int get(int key) {
		if (cache.containsKey(key))
			return cache.get(key);
		return -1;
	}

	public void put(int key, int value) {
		cache.put(key, value);
	}

	public static void main(String[] args) {

		LRUCacheWithOutDLL obj = new LRUCacheWithOutDLL(2);
		
		obj.put(1, 1);
		obj.put(2, 2);
		System.out.println(obj.get(1));
		obj.put(3, 3);
		System.out.println(obj.get(2));
		System.out.println(obj.get(1));
		obj.put(4, 4);
		System.out.println(obj.get(3));
		System.out.println(obj.get(4));
		
	}

}
