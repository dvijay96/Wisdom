package com.dsalgo.ds.heaps;

public interface Heap<E> {

	/**
	 * Inserts the element in the heap
	 * 
	 * @param element
	 */
	public void insert(E element);

	/**
	 * Deletes the root element
	 */
	public E delete();

}
