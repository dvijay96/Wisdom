package com.dsalgo.ds.heaps;

import java.util.ArrayList;
import java.util.List;

import com.dsalgo.exceptions.CustomException;

public class MinHeap<E> implements Heap<E> {

	private List<E> list;
	private int size = -1;

	public MinHeap() {
		super();
		this.list = new ArrayList<>();
	}

	@Override
	public void insert(E element) {
		list.add(element);
		size++;
		shiftUp(size);
	}

	@SuppressWarnings("unchecked")
	private void shiftUp(int index) {
		while (index > 0) {
			E currE = list.get(index);
			int parentIdx = (index - 1) / 2;
			Comparable<? super E> parent = (Comparable<? super E>) list.get(parentIdx);
			if (parent.compareTo(currE) > 0) {
				list.set(index, (E) parent);
				list.set(parentIdx, currE);
				index = parentIdx;
			} else {
				return;
			}
		}
	}

	@Override
	public E delete() {
		if (list.isEmpty())
			throw new CustomException("Heap is Empty!!!");

		E result = list.get(0);
		E lastElement = list.get(size);
		list.set(0, lastElement);
		deleteLast();
		shiftDown(0);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void shiftDown(int index) {
		while (index < this.size) {
			E curr = list.get(index);
			int left = 2 * index + 1;
			int right = 2 * index + 2;
			Comparable<? super E> leftChild = null;
			Comparable<? super E> rightChild = null;
			if (left <= this.size) {
				leftChild = (Comparable<? super E>) list.get(left);
			}
			if (right <= this.size) {
				rightChild = (Comparable<? super E>) list.get(right);
			}
			if (leftChild != null || rightChild != null) {
				index = doShift(index, curr, left, right, leftChild, rightChild);
			} else {
				return;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private int doShift(int index, E curr, int left, int right, Comparable<? super E> leftChild,
			Comparable<? super E> rightChild) {
		if (leftChild != null && leftChild.compareTo(curr) < 0) {
			if (rightChild != null && rightChild.compareTo((E) leftChild) < 0) {
				list.set(right, curr);
				list.set(index, (E) rightChild);
				index = right;
			} else {
				list.set(left, curr);
				list.set(index, (E) leftChild);
				index = left;
			}
		} else if (rightChild != null && rightChild.compareTo(curr) < 0) {
			if (leftChild != null && leftChild.compareTo((E) rightChild) < 0) {
				list.set(left, curr);
				list.set(index, (E) leftChild);
				index = left;
			} else {
				list.set(right, curr);
				list.set(index, (E) rightChild);
				index = right;
			}
		} else {
			return this.size + 1;
		}
		return index;
	}

	private void deleteLast() {
		list.remove(size--);
	}

	@Override
	public String toString() {
		return list.toString();
	}

}
