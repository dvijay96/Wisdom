package com.problems.recursions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateParentheses {

	public static void main(String[] args) {
		GenerateParentheses obj = new GenerateParentheses();
		System.out.println(obj.generate(3));
	}

	List<String> generate(int n) {
		if (n < 1)
			return Collections.emptyList();
		List<String> ans = new ArrayList<>();
		solve(n, ans, new StringBuilder(), 0, 0);
		return ans;
	}


	private void solve(int n, List<String> ans, StringBuilder str, int open, int closed) {
		if (open == n && closed == n) {
			ans.add(str.toString());
		} else {
			if (open < n) {
				str.append('(');
				solve(n, ans, str, open + 1, closed);
				str.deleteCharAt(str.length() - 1);
			}
			if (closed < open) {
				str.append(')');
				solve(n, ans, str, open, closed + 1);
				str.deleteCharAt(str.length() - 1);
			}
		}

	}
}
