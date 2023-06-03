package com.problems.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

//	Given a reference of a node in a connected undirected graph.
//
//	Return a deep copy (clone) of the graph.
//
//	Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
//
//	class Node {
//	    public int val;
//	    public List<Node> neighbors;
//	}
//	 
//
//	Test case format:
//
//	For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with 
//	val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
//
//	An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set 
//			of neighbors of a node in the graph.
//
//	The given node will always be the first node with val = 1. You must return the copy of the given node as a reference 
//			to the cloned graph.
//
//	 
//
//	Example 1:
//
//
//	Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//	Output: [[2,4],[1,3],[2,4],[1,3]]
//	Explanation: There are 4 nodes in the graph.
//	1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//	2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//	3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//	4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//	
//	Example 2:
//
//
//	Input: adjList = [[]]
//	Output: [[]]
//	Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
//	
//	Example 3:
//
//	Input: adjList = []
//	Output: []
//	Explanation: This an empty graph, it does not have any nodes.

	public static void main(String[] args) {
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);

		one.neighbors = Arrays.asList(two, four);
		two.neighbors = Arrays.asList(one, three);
		three.neighbors = Arrays.asList(two, four);
		four.neighbors = Arrays.asList(one, three);

		CloneGraph obj = new CloneGraph();

		Node ans = obj.cloneGraph(one);

		System.out.println(ans);
	}

//	Approach:- 	Use HashMap to look up nodes and add connection to them while performing BFS.
//			-> Idea is to BFS.
//			-> Store the curr graph node in a map as new instance of that node(clone) and while visiting its neighbors , 
//				get the node from graph and add these neighbors.
//			-> Also, each time add the unvisited nodes 1st in the map and then the curr node's neighbors.
//	
//	TC:- O(V+2E)
//	SC:- O(V)
	public Node cloneGraph(Node node) {
		if (node == null)
			return null;

		Map<Integer, Node> map = new HashMap<>();

		Node clone = new Node(node.val);

		map.put(clone.val, clone);

		Queue<Node> queue = new LinkedList<>();

		queue.add(node);

		while (!queue.isEmpty()) {
			Node curr = queue.poll();

			for (Node neighs : curr.neighbors) {
				if (!map.containsKey(neighs.val)) {
					map.put(neighs.val, new Node(neighs.val));
					queue.add(neighs);
				}
				map.get(curr.val).neighbors.add(map.get(neighs.val));
			}
		}
		return clone;
	}

}

//Definition for a Node.
class Node {
	public int val;
	public List<Node> neighbors;

	public Node() {
		val = 0;
		neighbors = new ArrayList<>();
	}

	public Node(int val) {
		this.val = val;
		this.neighbors = new ArrayList<>();
	}

	public Node(int val, List<Node> neighbors) {
		this.val = val;
		this.neighbors = neighbors;
	}

	@Override
	public String toString() {
		StringBuilder nodes = new StringBuilder();
		neighbors.forEach(n -> nodes.append(n.val + ", "));
		return "[ " + val + ", {" + nodes + "} ]";
	}

}
