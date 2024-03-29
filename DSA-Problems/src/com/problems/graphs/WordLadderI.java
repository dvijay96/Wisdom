package com.problems.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderI {

//	A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words 
//	beginWord -> s1 -> s2 -> ... -> sk such that:
//
//		Every adjacent pair of words differs by a single letter.
//		Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//		sk == endWord
//		Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest 
//		transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
//
//		 
//
//		Example 1:
//
//		Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//		Output: 5
//		Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
//		
//		Example 2:
//
//		Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//		Output: 0
//		Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
//		 
//
//		Constraints:
//
//		* 1 <= beginWord.length <= 10
//		* endWord.length == beginWord.length
//		* 1 <= wordList.length <= 5000
//		* wordList[i].length == beginWord.length
//		* beginWord, endWord, and wordList[i] consist of lowercase English letters.
//		* beginWord != endWord
//		* All the words in wordList are unique.

	public static void main(String[] args) {

		String str = "cat";

		char[] arr = str.toCharArray();

		arr[0] = 'd';

		System.out.println(str);
		System.out.println(Arrays.toString(arr));

	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		Set<String> words = new HashSet<>(wordList);

		if (words.contains(endWord)) {
			Queue<Pair> queue = new LinkedList<>();
			queue.add(new Pair(beginWord, 1));
			words.remove(beginWord);
			while (!queue.isEmpty()) {
				Pair pair = queue.poll();
				String word = pair.word;
				int seq = pair.seqNo;

				if (endWord.equals(word)) {
					return seq;
				}

				for (int i = 0; i < word.length(); i++) {
					char[] ar = word.toCharArray();
					for (char ch = 'a'; ch <= 'z'; ch++) {
						ar[i] = ch;
						String newWord = new String(ar);
						if (words.contains(newWord)) {
							queue.add(new Pair(newWord, seq + 1));
							words.remove(newWord);
						}
					}
				}
			}
		}

		return 0;
	}

	private class Pair {
		String word;
		int seqNo;

		Pair(String word, int seqNo) {
			this.word = word;
			this.seqNo = seqNo;
		}
	}

}
