package com.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

import com.problems.java.utility.ArrayUtils;
import com.problems.java.utility.TreeUtils;

public class MaxWitdhOfTree {

//	Given the root of a binary tree, return the maximum width of the given tree.
//
//	The maximum width of a tree is the maximum width among all levels.
//
//	The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), 
//	where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted 
//	into the length calculation.
//
//	It is guaranteed that the answer will in the range of a 32-bit signed integer.
//
//	Example:
//	
//										1
//									  /   \
//									3       2
//								  /           \
//								 5             9
//								/             /  
//							   6             7
//	
//	Input: root = [1,3,2,5,null,null,9,6,null,7]
//	Output: 7
//	Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

	public static void main(String[] args) {
		int[] arr = ArrayUtils.getIntArray(10);

		Node root = TreeUtils.createBinaryTree(arr);
		System.out.println(maxWidth(root));
	}

//	Approach:-
//			-> We do a level order traversal.
//			-> We try to mark the index for each node starting from root, with their equivalent markers with regards to how they would have fit
//				in a complete binary tree.
//			-> Since if we try to start with 1 as the root marker then gradually increase the markers, eventually it may overflow at a level if 
//				the tree is too large.
//			-> To avoid this overflow, we start marking in such a way that any root's left child would be 2*( min marker from previous level) + 1
//				and right child would be left child's pos + 1.
//				for example:-
//												    12(1)										---- Level 1
//	                                              /     \
//	                                            9(1)	  3(2)									---- Level 2
//	                                          /   \     /   \
//	                                        4(1)  5(2) 1(3)  2(4)								---- Level 3
//	
//								
//					for level 1 the min is fix that is 1.

//					for level 2 the min = previous min i.e. 1.
//								thus for 9 -> 2 * (1 - 1) + 1				first 1 is pos of head and second 1 is the min.
//								&    for 3 -> 2 * (1 - 1) + 2

//					for level 3 the min = previous min i.e. 1.
//								thus for 4 -> 2 * (1 - 1) + 1				head pos = 1
//									 for 5 -> 2 * (1 - 1) + 2
//									 for 1 -> 2 * (2 - 1) + 1				head pos = 2
//									 for 2 -> 2 * (2 - 1) + 2
//	
//			-> And finally the width for any level would be the last pos - first pos + 1.
//			-> Thus we can find the max width.
//	
//		TC:- O(n)
//		SC:- O(n)
	static int maxWidth(Node root) {
		if (root == null) {
			return 0;
		}

		Queue<PairI> queue = new LinkedList<>();
		queue.add(new PairI(root, 1));
		int width = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			int min = queue.peek().pos;
			int first = 0;
			int last = 0;
			for (int i = 0; i < size; i++) {
				PairI p = queue.poll();
				Node node = p.node;
				int pos = p.pos - min;
				if (i == 0)
					first = pos;
				if (i == size - 1)
					last = pos;
				if (node.left != null) {
					queue.add(new PairI(node.left, 2 * (pos) + 1));
				}
				if (node.right != null) {
					queue.add(new PairI(node.right, 2 * (pos) + 2));
				}
			}
			width = Math.max(width, last - first + 1);
		}

		return width;
	}
}

class PairI {
	Node node;
	int pos;

	public PairI(Node node, int pos) {
		super();
		this.node = node;
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "[ " + node + " , " + pos + "]";
	}

}