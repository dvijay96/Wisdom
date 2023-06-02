package learnings.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class App {

	public static void main(String[] args) {

		TreeSet<String>[] set = new TreeSet[5];

		Arrays.fill(set, new TreeSet<>());
		Set<String> set1 = new HashSet<>();
		set1.add("TODO");

		TreeSet<String> set2 = new TreeSet<>();
		set2.add("TODO");

		set[0] = set2;

		
		System.out.println(Arrays.toString(set));
		
		List<String> list = new ArrayList<>();
		
		list.add("A");
		list.addAll(set[0]);
		
		System.out.println(list);
	}

	static boolean isPalindrome(List<Character> list) {
		int i = 0;
		int j = list.size() - 1;

		while (i < j) {
			if (!list.get(i++).equals(list.get(j--))) {
				return false;
			}
		}
		return true;
	}

	public static boolean saveCivilians(int n, int m, char[][] grid) {
		List<int[]> civPos = new ArrayList<>();
		List<int[]> terPos = new ArrayList<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 'C') {
					civPos.add(new int[] { i, j });
				} else if (grid[i][j] == 'T') {
					terPos.add(new int[] { i, j });
				}
			}
		}

		System.out.println("TerPos ");
		terPos.forEach(pos -> System.out.println(Arrays.toString(pos)));
		System.out.println("civPos ");
		civPos.forEach(pos -> System.out.println(Arrays.toString(pos)));
		for (int[] ter : terPos) {
			for (int[] civ : civPos) {
				if ((civ[0] == ter[0] && Math.abs(civ[1] - ter[1]) == 1)
						|| (civ[1] == ter[1] && Math.abs(civ[0] - ter[0]) == 1)) {
					return false;
				}
			}
		}
		return true;
	}

	public String mergeAlternately(String word1, String word2) {
		int i = 0;

		StringBuilder ans = new StringBuilder();

		while (i < word1.length() && i < word2.length()) {
			if (i % 2 == 0) {
				ans.append(word1.charAt(i++));
			} else {
				ans.append(word2.charAt(i++));
			}
		}

		while (i < word1.length()) {
			ans.append(word1.charAt(i++));
		}

		while (i < word2.length()) {
			ans.append(word2.charAt(i++));
		}

		return ans.toString();
	}
}
