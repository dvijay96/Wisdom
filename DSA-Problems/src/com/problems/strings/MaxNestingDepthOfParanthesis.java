package com.problems.strings;

public class MaxNestingDepthOfParanthesis {

	public static void main(String[] args) {
//		String s = "( 4 ((5+6)(7+8*9)))";
		String s = "(4((5+6)(7+8*9)))";
		System.out.println(maxDepth(s));
	}

	public static int maxDepthII(String s) {
		int depth = 0;
		int openCount = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				openCount++;
			if (s.charAt(i) == ')')
				openCount--;
			depth = Math.max(openCount, depth);
		}
		return depth;
	}

	public static int maxDepth(String s) {
		int max = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char br = s.charAt(i);
			if (br == '(') {
				count++;
				if (count > max)
					max = count;
			} else if (br == ')')
				count--;
		}
		return max;
	}
}
