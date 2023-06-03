package com.problems.strings;

public class ZigzagConversion {

//	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
//	(you may want to display this pattern in a fixed font for better legibility)
//
//	P   A   H   N
//	A P L S I I G
//	Y   I   R
//	And then read line by line: "PAHNAPLSIIGYIR"
//
//	Write the code that will take a string and make this conversion given a number of rows:
//
//	string convert(string s, int numRows);
//	 
//
//	Example 1:
//
//	Input: s = "PAYPALISHIRING", numRows = 3
//	Output: "PAHNAPLSIIGYIR"
//	Example 2:
//
//	Input: s = "PAYPALISHIRING", numRows = 4
//	Output: "PINALSIGYAHRPI"
//	Explanation:
//	P     I    N
//	A   L S  I G
//	Y A   H R
//	P     I
//	Example 3:
//
//	Input: s = "A", numRows = 1
//	Output: "A"
	public static void main(String[] args) {
		String str = "PAYPALISHIRING";

		System.out.println(convert(str, 4));
	}

//	Suppose numRows = 4 and String = "PAYPALISHIRING"
//	its indices 
//				0 1 2 3 4 5 6 7 8 9 10 11 12 13
//				P A Y P A L I S H I  R  I  N  G
//	
//	Its zigzag representation of indices:

//				0     6       12
//				1   5 7    11 13 
//				2 4   8 10
//				3     9
//	
//	The idea is to maintain a array of stringbuilders of size numRows and push the corresponding char in those row no. stringbuilder
//	Keep a pointer on string chars to run through them
//	First we will fill each row string builder with current ith char. i.e vertically down
//	Then to move upward in the zigzag way, we start from numRows-2 till 1 and append ith char in resp row stringbuilders
//	For any numRow value, we can observe that for moving diagonally up we have to start from numRows-2 till 1.
//	At the end merge all stringbuilder.
	public static String convert(String s, int numRows) {
		int len = s.length();
		StringBuilder[] sb = new StringBuilder[numRows];
		for (int i = 0; i < sb.length; i++)
			sb[i] = new StringBuilder();

		int i = 0;
		while (i < len) {
			for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
				sb[idx].append(s.charAt(i++));
			for (int idx = numRows - 2; idx >= 1 && i < len; idx--) // obliquely up
				sb[idx].append(s.charAt(i++));
		}
		for (int idx = 1; idx < sb.length; idx++)
			sb[0].append(sb[idx]);
		return sb[0].toString();
	}
}
