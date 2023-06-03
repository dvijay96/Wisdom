package com.problems.slidingwindow;

import com.problems.java.utility.ArrayUtils;

public class SubstringsContainingAllThreeCharacters {

//	Given a string s consisting only of characters a, b and c.
//
//	Return the number of substrings containing at least one occurrence of all these characters a, b and c.
//	 
//
//	Example 1:
//
//	Input: s = "abcabc"
//	Output: 10
//	Explanation: The substrings containing at least one occurrence of the characters a, b and c are 
//	"abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

//	Example 2:
//
//	Input: s = "aaacb"
//	Output: 3
//	Explanation: The substrings containing at least one occurrence of the characters a, b and c are 
//		"aaacb", "aacb" and "acb".

//	Example 3:

//	Input: s = "bcaaacc"
//	Output: 5
//	Explanation: THe substrings containing at least one occurence of the characters a, b & c are
//		"bca" , "bcaa", "bcaaa", "bcaaac", "bcaaacc"

	public static void main(String[] args) {
		char[] ch = new char[7];

		ArrayUtils.fillRandomRanges(ch, 'a', 'c');

		ArrayUtils.print(ch);

		System.out.println(numberOfSubstrings(new String(ch)));
	}

//	Okay so these questions look like they have sliding window, But in this one i figured out something else.
//	Take three pointers l1, l2, l3 for a, b and c respectively.
//	Now as you iterate over the string of length n, you can count the number of sub-strings ending at that particular index.
//	How can we do that is here ->
//
//	* Keep on updating l1, l2 and l3.
//	* And take the minimum of l1, l2 and l3.
//	* Now from this min-index (min(l1, l2, l3) to the curr index i this is the smallest possible sub-string ending 
//		at curr-index i which follows the constraint.
//	* If the smallest sub-string is from min-index to curr-index, then for every sub-string starting from 
//		index 0, 1, 2, 3, ......min-index and ending at curr-index is valid, (So how many are these...... So there are 
//		min-index + 1 sub-strings)
//	* Add this min-index + 1 to the count.
	public static int numberOfSubstrings(String s) {
		int ans = 0;
		int a = -1;
		int b = -1;
		int c = -1;

		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'a':
				a = i;
				break;
			case 'b':
				b = i;
				break;
			case 'c':
				c = i;
				break;
			default:
			}

			// if(a==-1 || b == -1 || c == -1)
			// continue;

			int min = Math.min(a, Math.min(b, c));
			ans += min + 1;
		}
		return ans;
	}

	public static int alternative(String s) {
		int ans = 0;
		int[] map = new int[] { -1, -1, -1 };

		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i) - 'a'] = i;
			ans += 1 + Math.min(map[0], Math.min(map[0], map[1]));
		}
		return ans;
	}
}
