package com.problems.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.problems.metadata.Hard;

@Hard
public class EvaluateDivision {

//	You are given an array of variable pairs equations and an array of real numbers values, where 
//	equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string 
//	that represents a single variable.
//
//			You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must 
//			find the answer for Cj / Dj = ?.
//
//			Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//			Note: The input is always valid. You may assume that evaluating the queries will not result in 
//			division by zero and that there is no contradiction.
//
//			 
//
//			Example 1:
//
//			Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//			Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//			Explanation: 
//			Given: a / b = 2.0, b / c = 3.0
//			queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//			return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//			Example 2:
//
//			Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//			Output: [3.75000,0.40000,5.00000,0.20000]
//			Example 3:
//
//			Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//			Output: [0.50000,2.00000,-1.00000,-1.00000]

	public static void main(String[] args) {
		EvaluateDivision obj = new EvaluateDivision();

		String[][] equationsQ = { { "a", "b" }, { "c", "d" } };
		double[] values = { 1.0, 1.0 };
		String[][] queriesQ = { { "a", "c" }, { "b", "d" }, { "b", "a" }, { "d", "c" } };

		List<List<String>> equations = new ArrayList<>();
		for (int i = 0; i < equationsQ.length; i++) {
			equations.add(Arrays.asList(equationsQ[i][0], equationsQ[i][1]));
		}

		List<List<String>> queries = new ArrayList<>();

		for (int i = 0; i < queriesQ.length; i++) {
			queries.add(Arrays.asList(queriesQ[i][0], queriesQ[i][1]));
		}

		double[] ans = obj.calcEquation(equations, values, queries);

		System.out.println(Arrays.toString(ans));
	}

//	Approach:-
//			-> Considering this problem as a graph problem, where eqch equation is like an edge between numerator and
//				denominator, with distance as value.
//				
//			-> We need to consider it as a undirected graph cuz, if a/b exists then b/a does too.
//				Which is nothing but, if a -> b is value then b/a is 1/value.
//				
//			-> By these conditions, we need to build the graph around these equations.
//			
//			-> Now, every query is asking us to find distance between numerator and denominator i.e the product of each.
//				for ex: if a/b = 2 i.e a->b and b/c = 3 i.e b->c 
//							then a/c = a->b * b->c
//									 = 2 * 3
//									 = 6
//						
//						Similarly, c/a = c -> b * b -> a
//									   = 1/3 * 1/2
//									   = 0.167
//			
//			-> So, we can simply do traversal from numerator to denominator and find the ans.

//	TC:- O( q * (E+V)) , q = no. of queries
//	SC:- O(E + V)

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String, List<Pair>> graph = buildGraph(equations, values);

		double[] ans = new double[queries.size()];
		
		for (int i = 0; i < queries.size(); i++) {
			String nume = queries.get(i).get(0);
			String deno = queries.get(i).get(1);

			if (!graph.containsKey(nume) || !graph.containsKey(deno)) {
				ans[i] = -1.0;
			} else {
				double[] res = { -1.0 };
				Set<String> vis = new HashSet<>();
				dfs(nume, deno, graph, vis, 1.0, res);
				ans[i] = res[0];
			}
		}
		return ans;
	}

	private Map<String, List<Pair>> buildGraph(List<List<String>> equations, double[] values) {

		Map<String, List<Pair>> graph = new HashMap<>();
		for (int i = 0; i < equations.size(); i++) {
			String nume = equations.get(i).get(0);
			String deno = equations.get(i).get(1);
			double value = values[i];

			graph.putIfAbsent(nume, new ArrayList<>());

			graph.get(nume).add(new Pair(deno, value));

			graph.putIfAbsent(deno, new ArrayList<>());

			graph.get(deno).add(new Pair(nume, 1 / value));
		}
		return graph;
	}

	private void dfs(String src, String dest, Map<String, List<Pair>> graph, Set<String> vis, double temp,
			double[] res) {
		if (vis.contains(src))
			return;
		if (src.equals(dest)) {
			res[0] = temp;
			return;
		}
		vis.add(src);

		for (Pair p : graph.get(src)) {
			dfs(p.node, dest, graph, vis, temp * p.value, res);
		}
	}

	static class Pair {
		String node;
		double value;

		Pair(String node, double value) {
			this.node = node;
			this.value = value;
		}

		@Override
		public String toString() {
			return "[ " + node + ", " + value + " ]";
		}

	}
}
