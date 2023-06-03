package com.problems.graphs;

import java.util.HashSet;
import java.util.Set;

import com.problems.graphs.template.DisjointSet;

//Most Stones Removed with Same Row or Column
public class RemoveMostStones {

//	On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one 
//	stone.
//
//	A stone can be removed if it shares either the same row or the same column as another stone that has not been 
//	removed.
//
//	Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, 
//	return the largest possible number of stones that can be removed.
//
//	Example 1:
//
//	Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//	Output: 5
//	Explanation: One way to remove 5 stones is as follows:
//	1. Remove stone [2,2] because it shares the same row as [2,1].
//	2. Remove stone [2,1] because it shares the same column as [0,1].
//	3. Remove stone [1,2] because it shares the same row as [1,0].
//	4. Remove stone [1,0] because it shares the same column as [0,0].
//	5. Remove stone [0,1] because it shares the same row as [0,0].
//	Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
//	
//	Example 2:
//
//	Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//	Output: 3
//	Explanation: One way to make 3 moves is as follows:
//	1. Remove stone [2,2] because it shares the same row as [2,0].
//	2. Remove stone [2,0] because it shares the same column as [0,0].
//	3. Remove stone [0,2] because it shares the same row as [0,0].
//	Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
//	
//	Example 3:
//
//	Input: stones = [[0,0]]
//	Output: 0
//	Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
//	
	public static void main(String[] args) {
		RemoveMostStones obj = new RemoveMostStones();
		int[][] stones = { { 1, 2 }, { 0, 1 }, { 7, 3 }, { 5, 5 }, { 7, 1 }, { 6, 1 }, { 0, 6 }, { 5, 1 }, { 4, 2 },
				{ 8, 4 } };

		System.out.println(obj.removeStones(stones));
		System.out.println(obj.removeStonesOp(stones));
	}

//	Approach:-
//			-> Consider each stone as a node in a graph.
//			-> Check for each stone and connect the stones that share same row/col.
//			-> Consider all connected stones as a connected component of the graph.
//			-> So from each component we can remove all stones except the last one.
//				If no. of stones in a component be Si then
//				total stones removed = (S1 - 1) + (S2 - 1) + .... + (Sn-1 - 1)
//									 = (S1 + S2 + ... + Sn-1) - (1 + 1 +... + n-1)
//									 = (Total Stones) - no. of components
//		
//	        -> Hence, answer would be total no. of stones - no. of components.
//	
//	TC:- O(N^2)
//	SC:- O(N)
	public int removeStones(int[][] stones) {
		int n = stones.length;

		DisjointSet dsu = new DisjointSet(n);

		for (int i = 0; i < n; i++) {
			int x1 = stones[i][0];
			int y1 = stones[i][1];
			for (int j = i + 1; j < n; j++) {
				int x2 = stones[j][0];
				int y2 = stones[j][1];

				if (((x1 == x2) || (y2 == y1))) {
					dsu.unionByRank(i, j);
				}
			}
		}

		int comps = 0;

		int[] par = dsu.getParent();

		for (int i = 0; i < n; i++) {
			if (par[i] == i)
				comps++;
		}

		return n - comps;
	}

	/******************************************************************************************************/

//	Approach:- 
//			-> Instead of considering each stone as a node, we can consider rows and cols as node.
//				Thus a stone on row R will be connected with all stones of col C.
//				In short, it is the row and col that are connected rather than the stones.
//			-> As with DSU, we can have nodes as 0 to N, for row and col, we will consider the rows from 0 to total
//				rows - 1 as first half nodes and remaining nodes will be marked accordingly with no. of cols.
//			-> So if maxRow = 3 then col 0 becomes maxRow + coli + 1 i.e 4.
//			-> Considering above points, connect each such row and col of the stones.
//			-> Since DSU contains all nodes data in it, and there could be a scenario where a row / col does not have 
//				any stones in it. Thus it can't be considered as a component.
//			-> For this, we store the row and  col in Set for uniqueness.
//			-> After that, we need to calculate the number of components.
//			-> And answer as Total Stones - no. of components.
//	
//	TC:- O(N)
//	TC:- O(N)
	public int removeStonesOp(int[][] stones) {
		int maxRow = 0;
		int maxCol = 0;

		for (int[] stone : stones) {
			maxRow = Math.max(maxRow, stone[0]);
			maxCol = Math.max(maxCol, stone[1]);
		}

		DisjointSet dsu = new DisjointSet(maxRow + maxCol + 1);

		Set<Integer> uniqueNodes = new HashSet<>();
		for (int[] stone : stones) {
			int row = stone[0];
			int col = stone[1] + maxRow + 1;
			dsu.unionByRank(row, col);
			uniqueNodes.add(row);
			uniqueNodes.add(col);
		}

		int comps = 0;

		for (int node : uniqueNodes) {
			if (dsu.findParent(node) == node) {
				comps++;
			}
		}

		return stones.length - comps;
	}
}
