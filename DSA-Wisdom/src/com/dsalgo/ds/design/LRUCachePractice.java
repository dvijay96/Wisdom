package com.dsalgo.ds.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCachePractice {

	class Node {
		int key;
		int value;
		Node prev;
		Node next;

		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node head;
	private Node tail;
	private Map<Integer, Node> cache;
	private int capacity;

	public LRUCachePractice(int capacity) {
		this.capacity = capacity;
		cache = new HashMap<>();
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			Node existingNode = cache.get(key);
			remove(existingNode);
			insert(existingNode);
			return existingNode.value;
		}
		return -1;
	}

	public void put(int key, int value) {
		Node node = new Node(key, value);

		if (cache.containsKey(key)) {
			remove(cache.get(key));
		}
		insert(node);

		if (cache.size() > capacity) {
			remove(tail.prev);
		}
	}

	private void insert(Node node) {
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;

		cache.put(node.key, node);
	}

	private void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;

		cache.remove(node.key);
	}

	public static void main(String[] args) {

		LRUCachePractice obj = new LRUCachePractice(2);

		System.out.println(obj.get(2));
		obj.put(2, 6);
		System.out.println(obj.get(1));
		obj.put(1, 5);
		obj.put(1, 2);
		System.out.println(obj.get(1));
		System.out.println(obj.get(2));
	}

}
