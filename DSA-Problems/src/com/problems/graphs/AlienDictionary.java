package com.problems.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlienDictionary {

//	Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary. Find the order of characters 
//	in the alien language.
//	Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be 1 if the order of 
//			string returned by the function is correct else 0 denoting incorrect string returned.
//	 
//
//	Example 1:
//
//	Input: 
//	N = 5, K = 4
//	dict = {"baa","abcd","abca","cab","cad"}
//	Output:
//	1
//	Explanation:
//	Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", 
//	therefore 'b' is before 'a' in output. Similarly we can find other orders.
//	
//	Example 2:
//
//	Input: 
//	N = 3, K = 3
//	dict = {"caa","aaa","aab"}
//	Output:
//	1
//	Explanation:
//	Here order of characters is	'c', 'a', 'b' Note that words are sorted and in the given language "caa" comes before "aaa", 
//	therefore 'c' is before 'a' in output. Similarly we can find other orders.
//	
	public static void main(String[] args) {
		AlienDictionary obj = new AlienDictionary();

		String[] dict = new String[] { "baa", "abcd", "abca", "cab", "cad" };

		// Follow up question, when chars are not first k alphabetic chars
//		for (int i = 0; i < 7; i++) {
//			char[] str = new char[7];
//
//			ArrayUtils.fillRandomRanges(str, 'a', 'z');
//
//			dict[i] = new String(str);
//		}
//
//		System.out.println("Dictionary => " + Arrays.toString(dict));

		System.out.println(obj.findOrder(dict, 4));
	}

	public String findOrder(String[] dict, int k) {

		Map<Character, List<Character>> adj = new LinkedHashMap<>();

		for (int i = 0; i < k; i++) {
			adj.put((char) ('a' + i), new ArrayList<>());
		}

		int[] indegree = new int[k];

		for (int i = 0, j = 1; i < dict.length - 1 && j < dict.length; i++, j++) {
			String word1 = dict[i];
			String word2 = dict[j];

			for (int z = 0; z < word1.length() && z < word2.length(); z++) {
				if (word1.charAt(z) != word2.charAt(z)) {
					adj.get(word1.charAt(z)).add(word2.charAt(z));
					indegree[word2.charAt(z) - 'a']++;
					break;
				}
			}
		}

		return topoSort(adj, indegree);
	}

	private String topoSort(Map<Character, List<Character>> adj, int[] indegree) {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		StringBuilder ans = new StringBuilder();

		while (!queue.isEmpty()) {
			int node = queue.poll();
			char ch = (char) ('a' + node);
			ans.append(ch);

			for (char nodes : adj.get(ch)) {
				indegree[nodes - 'a']--;
				if (indegree[nodes - 'a'] == 0) {
					queue.add(nodes - 'a');
				}
			}
		}

		return ans.toString();
	}
}
