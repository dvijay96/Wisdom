package com.problems.strings;

public class StringToInteger {

	public static void main(String[] args) {
		int myAtoiLC = myAtoiLC("-+2147483648");
		System.out.println(myAtoiLC);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);

	}

	public static int myAtoiLC(String s) {
		int ans = 0;
		int i = 0;

		while (i < s.length() && s.charAt(i) == ' ')
			i++;

		boolean isPositive = true;
		if (i < s.length()) {
			if (s.charAt(i++) == '-')
				isPositive = false;
		}

		while (i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
			int digit = s.charAt(i++) - '0';
			if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && digit > 7))
				return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;

//			clamping
//            if(ans>(Integer.MAX_VALUE-digit)/10){
//                if(!isPositive)return Integer.MIN_VALUE;
//                return Integer.MAX_VALUE;
//            }

			ans = ans * 10 + digit;
		}

		return isPositive ? ans : -ans;
	}

	int atoiGFG(String str) {
		int mul = 1;
		int ans = 0;

		for (int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				ans += (ch - '0') * mul;
				mul *= 10;
			} else if (i == 0 && ch == '-') {
				ans = -ans;
			} else {
				return -1;
			}
		}
		return ans;
	}

}
