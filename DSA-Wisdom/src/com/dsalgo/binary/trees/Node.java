package com.dsalgo.binary.trees;

public class Node {

	int data;
	Node left;
	Node right;

	protected Node(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data + "";
	}

}
