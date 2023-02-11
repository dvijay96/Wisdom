package com.problems.java.stacks;

import java.util.Deque;
import java.util.LinkedList;

public class TheCelebrityProblem {

//	A celebrity is a person who is known to all but does not know anyone at a party. If you go to a party of N people, 
//	find if there is a celebrity in the party or not.
//	A square NxN matrix M[][] is used to represent people at the party such that if an element of row i and column j  
//	is set to 1 it means ith person knows jth person. Here M[i][i] will always be 0.
//	Note: Follow 0 based indexing.
//	Follow Up: Can you optimize it to O(N)
//
//	Example 1:
//
//	Input:
//	N = 3
//	M[][] = {{0 1 0},
//	         {0 0 0}, 
//	         {0 1 0}}
//	Output: 1
//	Explanation: 0th and 2nd person both
//	know 1. Therefore, 1 is the celebrity.

//	Better explanation:-
//	
//		Since Oth row & 2nd row entity know 1st row entity i.e mat[0][1] = 1 and mat[2][1] = 1
// 		And 1 doesn't know 0th or 2nd row entity, i.e mat[1][0] = 0 and mat[2][0] = 0
//		Hence 1 is a celebrity.

//	Example 2:

//	mat[][] =  0 1 0 0
//			   0 0 0 0
//			   0 1 0 0
//			   0 1 0 0

//	Here 0th, 2nd & 3rd row know 1st row entity and 1st row doesn't know any one
//	Thus 1st is a celebrity.

	public static void main(String[] args) {

	}

//	We are given that 
//	A celebrity is someone who doesn't know any one but is known to all
//	So we need to find such a row no. (person no.) which is having a zero relation with others
//	but others are having 1 relation with him.

	static int celebrity(int[][] mat) {

		int celeb = -1;
		for (int row = 0; row < mat.length; row++) {
			for (int col = 0; col < mat.length; col++) {
				if (col != row && mat[row][col] == 1) {
					int person = col;

					for (int i = 0; i < mat.length; i++) {
						if (person != i && (mat[person][i] == 1 || mat[i][person] == 0)) {
							return -1;
						}
					}
					celeb = person;
					break;
				}
			}
			if (celeb != -1)
				break;
		}

		return celeb;
	}

	static int celebrity(int[][] mat, int n) {
		Deque<Integer> stack = new LinkedList<>();

		// Getting all the people in the stack for potential celebrity check
		for (int i = 0; i < mat.length; i++) {
			stack.push(i);
		}

		// There can be at least one celebrity
		while (stack.size() != 1) {
			int personA = stack.pop();
			int personB = stack.pop();

			if (mat[personA][personB] == 1) { // If Person A knows Person B, Person A is not a celebrity,
				stack.push(personB); // Person B might be a celebrity
			} else {
				stack.push(personA); // Person A might be a celebrity
			}
		}

		int celebrity = stack.pop();

		for (int person = 0; person < mat.length; person++) {

//			 Except for himself, does he knows anyone, 
//			If yes, then he is no celebrity
			if (person != celebrity && (mat[celebrity][person] == 1 || mat[person][celebrity] == 0)) {
				return -1;
			}
		}

		return celebrity;
	}
}
