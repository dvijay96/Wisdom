package com.problems.graphs;

import com.problems.java.utility.ArrayUtils;

public class FloodFill {

//	An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
//
//	You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from 
//	the pixel image[sr][sc].
//
//	To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel 
//	of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the 
//			same color), and so on. Replace the color of all of the aforementioned pixels with color.
//
//	Return the modified image after performing the flood fill.
//
//	 
//
//	Example 1:
//
//
//	Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
//	Output: [[2,2,2],[2,2,0],[2,0,1]]
//	Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
//	Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
//	
//	Example 2:
//
//	Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
//	Output: [[0,0,0],[0,0,0]]
//	Explanation: The starting pixel is already colored 0, so no changes are made to the image.
//	 
//
//	Constraints:
//
//	m == image.length
//	n == image[i].length
//	1 <= m, n <= 50
//	0 <= image[i][j], color < 216
//	0 <= sr < m
//	0 <= sc < n
//	
	public static void main(String[] args) {

		int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		int sr = 1, sc = 1, color = 2;

		FloodFill obj = new FloodFill();

		obj.floodFill(image, sr, sc, color);

		ArrayUtils.print(image);
	}

//	Approach:- 
//			-> Asking us to convert the given grid with the new color from the given source pos.
//			-> If the source pos is colored differently then convert that color into new color and all other such adjacent 
//				pixels which were colored with the same color.
//			->  We need to perform BFS/DFS from the given source position color convert all such connecting pixels into 
//				new color.
//	
//	TC:- O(n*m)		in worst case all pixels can be of the same color and all require converting
//	SC:- O(n*m)		aux stack space
	public int[][] floodFill(int[][] image, int sr, int sc, int color) {
		int curr = image[sr][sc];

		dfs(image, curr, sr, sc, color);

		return image;
	}

	void dfs(int[][] image, int curr, int i, int j, int color) {
		if (i == -1 || j == -1 || i == image.length || j == image[0].length || image[i][j] != curr
				|| image[i][j] == color)
			return;
		image[i][j] = color;
		dfs(image, curr, i, j - 1, color);
		dfs(image, curr, i, j + 1, color);
		dfs(image, curr, i - 1, j, color);
		dfs(image, curr, i + 1, j, color);
	}
}
