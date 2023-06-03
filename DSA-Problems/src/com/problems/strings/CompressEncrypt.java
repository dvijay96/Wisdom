package com.problems.strings;

public class CompressEncrypt {

//	Compress and encrypt strings
//	
//	You are given a string thestring. Compress and encrypt it using the following algorithm:
//
//	Begin with an empty strings, for each group of consecutive repeating characters in thestring:
//
//	* If the group length is 1, append the character to s followed by 1
//	* Otherwise, get the group's length y (the group only contains consecutive repeating characters), then get the 
//		hexadecimal representation of y, append the character to s followed by y
//	* Reverse s and return it
//	
//	All Hexadecimal letters should be Lowercase.
//
//	Function Description
//	Complete the function compressAndEncrypt in the editor below. The function must state what must be returned or 
//	printed.
//
//	compressAndEncrypt has the following parameter(s):
//	thestring: a string
//	
//	Constraints
//	• 1 < str.length < 10000

	public static void main(String[] args) {
		CompressEncrypt obj = new CompressEncrypt();
		String str = "aabbccc";

		System.out.println(obj.compressAndEncrypt(str));
	}

	public String compressAndEncrypt(String str) {
		StringBuilder ans = new StringBuilder();

		int i = 0;

		while (i < str.length()) {
			int count = 0;
			char curr = str.charAt(i);

			while (i < str.length() && str.charAt(i) == curr) {
				i++;
				count++;
			}

			if (count > 1) {
				ans.append(curr);
				ans.append(Integer.toHexString(count));
			} else {
				ans.append(curr);
				ans.append(count);
			}
		}

		return ans.reverse().toString();
	}
}
