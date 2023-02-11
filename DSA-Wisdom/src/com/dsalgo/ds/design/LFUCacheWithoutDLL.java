package com.dsalgo.ds.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCacheWithoutDLL {

	private int cap;
	private Map<Integer, Integer> cache = new HashMap<>();
	private Map<Integer, Integer> keycount = new HashMap<>();
	private Map<Integer, LinkedHashSet<Integer>> freqmap = new HashMap<>();
	private int min = -1;

	public LFUCacheWithoutDLL(int capacity) {
		cap = capacity;
		freqmap.put(1, new LinkedHashSet<>());
	}

	public int get(int key) {
		if (!cache.containsKey(key))
			return -1;
		int count = keycount.get(key);
		keycount.put(key, count + 1);
		freqmap.get(count).remove(key);
		if (count == min && freqmap.get(count).isEmpty()) {
			min++;
			freqmap.remove(count);
		}
		if (!freqmap.containsKey(count + 1))
			freqmap.put(count + 1, new LinkedHashSet<>());
		freqmap.get(count + 1).add(key);
		return cache.get(key);
	}

	public void put(int key, int value) {
		if (cap <= 0)
			return;
		if (cache.containsKey(key)) {
			cache.put(key, value);
			get(key);
			return;
		}
		if (cache.size() >= cap) {
			int evict = freqmap.get(min).iterator().next();
			freqmap.get(min).remove(evict);
			cache.remove(evict);
			keycount.remove(evict);
		}
		cache.put(key, value);
		keycount.put(key, 1);
		min = 1;
		freqmap.get(1).add(key);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
