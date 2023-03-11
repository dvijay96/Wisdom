package com.problems.gfg.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SaveCivilians {

//	Some civilians are partying in a grid of dimensions n * m. Some 
//	terrorists also sneaked into the party and occupied some cells. Each 
//	cell in the grid will either be empty or can accommodate at most one 
//	person which can be either {civilian, terrorist, or soldier}. 
//	Assuming that the terrorists can move in any direction (left, up, 
//	right, above) if the corresponding cell is empty or contains a 
//	civilian, find if there exists an arrangement of soldiers such that none 
//	of the civilians got harmed or report if that is impossible. 
//	
//	Note - 
//	1. Initially, no soldiers are on the grid 
//	2. Soldiers can only be deputed into empty cells. 
//	3. Neither of Soldiers or Civilians can't move. 
//	4. Many braveheart soldiers are there hence you need not minimize 
//	the soldiers. 
//	5. grid[i][j] = 'E' represents an empty cell, grid[i][j] = 'T' represents a 
//	terrorist, grid[i][j] = 'C' represents a civiliana, and grid[i][j] = 'S' 
//	represents a soldier. 

//	Example 1 :
//		n = 5 , m= 5
//		
//		grid =  
//				E  E  E  C  E
//				E  E  C  C  E
//				E  C  E  E  E
//				E  E  T  T  E
//				E  E  E  E  E
//		
//		Output:- 
//				Possible
//		
//		Explanation:- We can depute Soldiers like
//						
//				E  E  E  C  E
//				E  E  C  C  E
//				E  C  S  S  S
//				S  S  T  T  E
//				E  E  E  E  E

//	Example 2:
//		
//		n = 5  , m = 5
//		
//			grid = 
//					E  E  E  C  E
//					E  E  C  C  E
//					E  C  E  T  E
//					E  E  T  T  E
//					E  E  E  E  E
//					
//			Output:
//					Impossible
//			
//			Explanation:
//				
//					No matter how we place soldiers on the grid the terrorist in cell (2, 3) can always kill the civilian 
//					in the cell (1, 3).

	public static void main(String[] args) {

		char[][] grid = getTestCase(5, 5);

		for (char[] g : grid) {
			System.out.println(Arrays.toString(g));
		}

		System.out.println(saveCivilians(grid));
	}

//	Approach:-
//			-> If for any civilian, a Terrorist is present in its next immediate direction block (left, right, top , bottom)
//				, then that civilian can't be saved.
//			-> At least one unit of distance should be there between civilian and terrorist so that a soldier can be put in 
//				between to protect the civilian

//	TC:- O(n*m)
	public static boolean saveCivilians(char[][] grid) {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 'C') {
					if (i > 0 && grid[i - 1][j] == 'T') {
						return false;
					}
					if (i < grid.length - 1 && grid[i + 1][j] == 'T') {
						return false;
					}
					if (j > 0 && grid[i][j - 1] == 'T') {
						return false;
					}
					if (j < grid[i].length - 1 && grid[i][j + 1] == 'T') {
						return false;
					}
				}
			}
		}

		return true;
	}

	public static boolean saveCiviliansII(int n, int m, char[][] grid) {
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

	private static char[][] getTestCase(int n, int m) {
		char[][] grid = new char[n][m];
		char[] options = { 'E', 'T', 'C' };
		int flip = -1;

		for (int i = 0; i < grid.length; i++) {
			flip = ThreadLocalRandom.current().nextInt();
			for (int j = 0; j < grid[i].length; j++) {
				if ((flip & 1) == 0) {
					grid[i][j] = options[ThreadLocalRandom.current().nextInt(0, 3)];
				} else {
					grid[i][j] = 'E';
				}
			}
		}

		return grid;
	}
}
