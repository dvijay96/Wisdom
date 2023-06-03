package com.dsalgo.ds.design;

import java.util.HashMap;
import java.util.Map;

import com.dsalgo.meta.data.Medium;

@Medium
public class FrequencyTracker {

//	Design a data structure that keeps track of the values in it and answers some queries regarding their 
//	frequencies.
//
//	Implement the FrequencyTracker class.
//
//	FrequencyTracker(): Initializes the FrequencyTracker object with an empty array initially.
//	void add(int number): Adds number to the data structure.
//	void deleteOne(int number): Deletes one occurence of number from the data structure. The data structure may 
//	not contain number, and in this case nothing is deleted.
//	bool hasFrequency(int frequency): Returns true if there is a number in the data structure that occurs frequency 
//	number of times, otherwise, it returns false.
//	 
//
//	Example 1:
//
//	Input
//	["FrequencyTracker", "add", "add", "hasFrequency"]
//	[[], [3], [3], [2]]
//	Output
//	[null, null, null, true]
//
//	Explanation
//	FrequencyTracker frequencyTracker = new FrequencyTracker();
//	frequencyTracker.add(3); // The data structure now contains [3]
//	frequencyTracker.add(3); // The data structure now contains [3, 3]
//	frequencyTracker.hasFrequency(2); // Returns true, because 3 occurs twice

	private static final String DELETE_ONE = "deleteOne";
	private static final String ADD = "add";
	private static final String HAS_FREQUENCY = "hasFrequency";

	public static void main(String[] args) {

		String[] commands = { "FrequencyTracker", HAS_FREQUENCY, ADD, HAS_FREQUENCY, HAS_FREQUENCY, ADD, ADD, ADD,
				DELETE_ONE, DELETE_ONE, HAS_FREQUENCY, ADD, HAS_FREQUENCY, HAS_FREQUENCY };

		int[][] numbers = { {}, { 1 }, { 3 }, { 1 }, { 1 }, { 6 }, { 2 }, { 6 }, { 6 }, { 6 }, { 2 }, { 2 }, { 2 },
				{ 1 } };

		FrequencyTracker obj = new FrequencyTracker();

		for (int i = 0; i < commands.length; i++) {
			String command = commands[i];

			if (command.equalsIgnoreCase(ADD)) {
				obj.add(numbers[i][0]);
			} else if (command.equalsIgnoreCase(DELETE_ONE)) {
				obj.deleteOne(numbers[i][0]);
			} else if (command.equalsIgnoreCase(HAS_FREQUENCY)) {
				System.out.println("hasFrequency( " + numbers[i][0] + " )  -> " + obj.hasFrequency(numbers[i][0]));
			}
		}
	}

//	Approach:- 
//			-> Having two maps, one for caring numbers and their frequency and one for caring frequency and the count of numbers 
//				having that frequency.
//			-> In this way we can achieve each operation in O(1) time.

	private Map<Integer, Integer> numMap;
	private Map<Integer, Integer> freqMap;

	public FrequencyTracker() {
		numMap = new HashMap<>();
		freqMap = new HashMap<>();
	}

	public void add(int number) {
		int currFreq = numMap.getOrDefault(number, 0);

		if (currFreq > 0) {
			freqMap.put(currFreq, freqMap.get(currFreq) - 1);

			if (freqMap.get(currFreq) == 0) {
				freqMap.remove(currFreq);
			}
		}

		numMap.put(number, numMap.getOrDefault(number, 0) + 1);

		int newFreq = numMap.get(number);

		freqMap.put(newFreq, freqMap.getOrDefault(newFreq, 0) + 1);
	}

	public void deleteOne(int number) {
		if (numMap.containsKey(number)) {

			int currFreq = numMap.getOrDefault(number, 0);

			if (currFreq > 0) {
				freqMap.put(currFreq, freqMap.get(currFreq) - 1);

				if (freqMap.get(currFreq) == 0) {
					freqMap.remove(currFreq);
				}
			}

			numMap.put(number, numMap.get(number) - 1);

			int newFreq = numMap.get(number);

			if (newFreq == 0) {
				numMap.remove(number);
			} else {
				freqMap.put(newFreq, freqMap.getOrDefault(newFreq, 0) + 1);
			}
		}
	}

	public boolean hasFrequency(int frequency) {
		return freqMap.containsKey(frequency);
	}
}
