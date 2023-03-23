package com.problems.gfg.contest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GFG {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] input = new String[35];

		for (int i = 0; i < 35; i++) {
			input[i] = scanner.next();
		}

		GFG obj = new GFG(2);

		for (String s : input) {
			System.out.println(obj.judgeOrNot(s));
		}

		scanner.close();
	}

	private int cap;
	private Map<String, String> map;

	public GFG(int cap) {
		// Constructor
		this.cap = cap;
		this.map = new HashMap<>();
	}

	public boolean judgeOrNot(String submissionId) {
		String[] userTime = submissionId.split("@");
		if (!map.containsKey(userTime[0])) {
			map.put(userTime[0], userTime[1]);
			return true;
		} else {
			try {
				String prevTime = map.get(userTime[0]);

				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:ss");
				java.util.Date d1 = sdf.parse(prevTime);
				java.util.Date d2 = sdf.parse(userTime[1]);

				if (((d2.getTime() - d1.getTime()) / 1000) < cap) {
					return false;
				}
				map.put(userTime[0], userTime[1]);
				return true;
			} catch (Exception e) {

			}
			return false;
		}
	}
}
