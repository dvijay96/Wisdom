package com.problems.graphs;

import java.util.ArrayList;
import java.util.List;

import com.problems.graphs.template.DisjointSet;

public class NumberOfIslandsII {

//	You are given an n, m which means the row and column of the 2D matrix, and an array of size k denoting the 
//	number of operations. Matrix elements are 0 if there is water or 1 if there is land. Originally, the 2D matrix 
//	is all 0 which means there is no land in the matrix. The array has k operator(s) and each operator has two 
//	integers A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. 
//	Return how many islands are there in the matrix after each operation. You need to return an array of size k.
//
//	Note: An island means a group of 1s such that they share a common side.

	public static void main(String[] args) {

		NumberOfIslandsII obj = new NumberOfIslandsII();

		int n = 4;
		int m = 5;
		int[][] queries = { { 1, 1 }, { 0, 1 }, { 3, 3 }, { 3, 4 } };

		System.out.println(obj.numOfIslands(n, m, queries));
	}

//	Approach:
//			-> We are going to use disjoint set data structure to solve this.
//			-> for each query, we convert the spot into a land i.e 1 and increment count. Considering it as a 
//				separate land.
//			-> after converting, we'll check the adjacent and if any land cell i.e 1 found we'll connect them and 
//				decrement the count	as it is no longer a separate land.
//			-> With disjoint set, we'll consider each cell as a node, 
//				so in a n*m grid, the nodes will be from 0 to n*m-1.
//			-> Formula for converting a (row, col) cell into a node is
//				totalCols * rowi + coli.
	public List<Integer> numOfIslands(int n, int m, int[][] operators) {

		int[][] grid = new int[n][m];

		DisjointSet dsu = new DisjointSet(n * m);

		int count = 0;

		List<Integer> ans = new ArrayList<>();

		int[][] dirs = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

		for (int[] operator : operators) {
			int row = operator[0];
			int col = operator[1];

			if (grid[row][col] == 1) {
				ans.add(count);
			} else {
				grid[row][col] = 1;
				count++;
				int node = row * m + col;

				for (int[] dir : dirs) {
					int r = row + dir[0];
					int c = col + dir[1];

					if (r == -1 || r == n || c == -1 || c == m || grid[r][c] == 0) {
						continue;
					}
					int adj = r * m + c;
					if (dsu.findParent(node) != dsu.findParent(adj)) {
						count--;
						dsu.unionByRank(node, adj);
					}
				}
				ans.add(count);
			}
		}

		return ans;
	}
}
