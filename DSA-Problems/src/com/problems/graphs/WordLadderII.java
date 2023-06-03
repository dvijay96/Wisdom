package com.problems.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {

	public static void main(String[] args) {
		String startWord = "der";
		String targetWord = "dfs";
		String[] wordList = { "des", "der", "dfr", "dgt", "dfs" };

		WordLadderII obj = new WordLadderII();
		ArrayList<ArrayList<String>> ans = obj.findSequences(startWord, targetWord, wordList);
		if (ans.isEmpty())
			System.out.println(-1);
		else {
			Collections.sort(ans, new Comp());
			for (int i = 0; i < ans.size(); i++) {
				for (int j = 0; j < ans.get(i).size(); j++) {
					System.out.print(ans.get(i).get(j) + " ");
				}
				System.out.println();
			}
		}

	}

	public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {
		Set<String> dictionary = new HashSet<>();
		for (String word : wordList) {
			dictionary.add(word);
		}

		ArrayList<ArrayList<String>> ans = new ArrayList<>();

		if (dictionary.contains(targetWord)) {

			Queue<ArrayList<String>> queue = new LinkedList<>();
			ArrayList<String> initList = new ArrayList<>();
			initList.add(startWord);
			queue.add(initList);
			dictionary.remove(startWord);

			while (!queue.isEmpty()) {

				int size = queue.size();

				Set<String> usedWords = new HashSet<>();

				while (size-- > 0) {
					ArrayList<String> seq = queue.poll();
					String word = seq.get(seq.size() - 1);

					if (word.equals(targetWord)) {
						ans.add(seq);
					} else {
						char[] ar = word.toCharArray();
						for (int i = 0; i < word.length(); i++) {
							for (char ch = 'a'; ch <= 'z'; ch++) {
								ar[i] = ch;
								String newWord = new String(ar);

								if (dictionary.contains(newWord)) {
									ArrayList<String> newSeq = new ArrayList<>(seq);
									newSeq.add(newWord);
									queue.add(newSeq);
									usedWords.add(newWord);
								}
							}
							ar[i] = word.charAt(i);
						}
					}

				}
				dictionary.removeAll(usedWords);
			}
		}
		return ans;
	}
}

class Comp implements Comparator<ArrayList<String>> {
	@Override
	public int compare(ArrayList<String> a, ArrayList<String> b) {
		StringBuilder x = new StringBuilder();
		StringBuilder y = new StringBuilder();
		for (int i = 0; i < a.size(); i++)
			x.append(a.get(i));
		for (int i = 0; i < b.size(); i++)
			y.append(b.get(i));
		return x.toString().compareTo(y.toString());
	}
}
