package com.dsalgo.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public interface GraphUtils {

	/**
	 * 0 based adjacency list for the graph (Undirected)
	 * 
	 * @param vertices
	 * @return
	 */
	static List<List<Integer>> adjacencyListUndirected(int vertices) {
		List<List<Integer>> list = new ArrayList<>(vertices);
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int edges = vertices * ((vertices - 1) / 2);
		edges = random.nextInt(edges);
		for (int i = 0; i < vertices; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < edges; i++) {
			// randomly select two vertices to
			// create an edge between them
			int v = random.nextInt(vertices);
			int w = random.nextInt(vertices);

			if (list.get(v).contains(w)) {
				// Reduce the value of i
				// so that again v and w can be chosen
				// for the same edge count
				i--;
				continue;
			}
			if (v != w) {
				// add an edge between them
				list.get(v).add(w);

				// Add v to w's adjacency list
				list.get(w).add(v);
			}
		}
		return list;
	}

	/**
	 * 0 based adjacency list for the graph (Directed)
	 * 
	 * @param vertices
	 * @return
	 */
	static List<List<Integer>> adjacencyListDirected(int vertices) {
		List<List<Integer>> list = new ArrayList<>(vertices);
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int edges = vertices * ((vertices - 1) / 2);
		edges = random.nextInt(edges);
		for (int i = 0; i < vertices; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < edges; i++) {
			// randomly select two vertices to
			// create an edge between them
			int v = random.nextInt(vertices);
			int w = random.nextInt(vertices);

			if (list.get(v).contains(w)) {
				// Reduce the value of i
				// so that again v and w can be chosen
				// for the same edge count
				i--;
				continue;
			}
			if (v != w)
				list.get(v).add(w);
		}
		return list;
	}
}
