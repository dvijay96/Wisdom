package com.problems.array.medium;

import java.util.ArrayList;
import java.util.List;

import com.problems.java.utility.ArrayUtils;

public class SpiralMatrix {

	public static void main(String[] args) {

		SpiralMatrix obj = new SpiralMatrix();

		int[][] matrix = new int[5][4];

		for (int i = 0; i < matrix.length; i++) {
			ArrayUtils.fillRandom(matrix[i]);
			ArrayUtils.print(matrix[i]);
		}

		System.out.println(obj.spiralOrder(matrix));
		System.out.println(obj.spiralOrderEasy(matrix));

	}

	public List<Integer> spiralOrderEasy(int[][] matrix) {

		int n = matrix.length;
		int m = matrix[0].length;
		int rowLow = 0;
		int rowHigh = matrix.length - 1;
		int colLow = 0;
		int colHigh = matrix[0].length - 1;

		List<Integer> spiral = new ArrayList<>();

		int temp = 0;

		while (spiral.size() < n * m) {
			temp = colLow;

			while (temp <= colHigh && spiral.size() < n * m) {
				spiral.add(matrix[rowLow][temp++]);
			}

			rowLow++;
			temp = rowLow;

			while (temp <= rowHigh && spiral.size() < n * m) {
				spiral.add(matrix[temp++][colHigh]);
			}

			colHigh--;
			temp = colHigh;

			while (temp >= colLow && spiral.size() < n * m) {
				spiral.add(matrix[rowHigh][temp--]);
			}
			rowHigh--;
			temp = rowHigh;

			while (temp >= rowLow && spiral.size() < n * m) {
				spiral.add(matrix[temp--][colLow]);
			}
			colLow++;
		}

		return spiral;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();

		if (matrix == null || matrix.length == 0) {
			return res;
		}

		int m = matrix.length;
		int n = matrix[0].length;

		int[][] dirMatrix = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		int[] range = { n, m - 1 };

		int dir = 0; // index of dirMatrix, 0: right, 1: down, 2: left, 3: up

		int row = 0;
		int col = -1; // initial position

		while (range[dir % 2] != 0) {
			for (int i = 0; i < range[dir % 2]; i += 1) {
				row += dirMatrix[dir][0];
				col += dirMatrix[dir][1];
				res.add(matrix[row][col]);
			}

			range[dir % 2] -= 1;
			dir = (dir + 1) % 4;
		}

		return res;

	}
}
