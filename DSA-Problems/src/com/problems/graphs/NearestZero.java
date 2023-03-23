package com.problems.graphs;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;

public class NearestZero {
//
//	Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
//	The distance between two adjacent cells is 1.
//
//			Example 1:
//
//			Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
//			Output: [[0,0,0],[0,1,0],[0,0,0]]
//			
//			Example 2:
//
//			Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
//			Output: [[0,0,0],[0,1,0],[1,2,1]]

	public static void main(String[] args) {
		NearestZero obj = new NearestZero();
		int[][] arr = new int[5][5];
		System.out.println("Input: ");
		for (int[] ar : arr) {
			ArrayUtils.fillRandomRange(ar, 0, 1);
			ArrayUtils.print(ar);
		}
		System.out.println();
		obj.updateMatrix(arr);

		System.out.println("Output: ");
		ArrayUtils.print(arr);
	}

//	Approach:- 
//			-> Need to find the nearest 1 that could be found from each 0.
//			-> By performing BFS, we could find the distance.
//			-> Store all zero locations in the queue.
//			-> Now start BFS from each stored zero position, if a 1 is found, update that cell with the current level of BFS and push it in the queue.
//			-> Need to skip the visited cells/ zero cells.

//	TC:- O(m*n) + max(n,m)
//	SC:- O(m*n)			could be optimized in space complexity
	public int[][] updateMatrix(int[][] mat) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[mat.length][mat[0].length];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == 0) {
					queue.add(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

		bfs(mat, queue, visited);

		return mat;
	}

	private void bfs(int[][] mat, Queue<int[]> queue, boolean[][] visited) {
		int level = 0;
		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!queue.isEmpty()) {
			int size = queue.size();
			level++;
			while (size > 0) {
				int x = queue.peek()[0];
				int y = queue.peek()[1];
				// visited[x][y] = true;
				queue.remove();
				for (int[] d : dir) {
					int row = x + d[0];
					int col = y + d[1];

					if (col == -1 || col == mat[0].length || row == -1 || row == mat.length || visited[row][col])
						continue;
					visited[row][col] = true;
					mat[row][col] = level;
					queue.add(new int[] { row, col });

				}
				size--;
			}
		}
	}
}
