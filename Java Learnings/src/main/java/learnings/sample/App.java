package learnings.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {

		int[][] ranges = { { 6, 10 }, { 5, 15 } };

		Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

		for (int[] arr : ranges) {
			System.out.println(Arrays.toString(arr));
		}

		System.out.printf("\n Great \n");
		System.out.printf("%n Movie %n");
		System.out.println("✌️🙌❤️😍😜🎶😎🤦‍♂️👌😊🎉Avengers");
//		char[][] grid = new char[5][5];
//
//		Scanner scan = new Scanner(System.in);
//
//		System.out.print("Enter:\n");
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++)
//				grid[i][j] = scan.next().charAt(0);
//		}
//
//		System.out.println(saveCivilians(5, 5, grid));
	}

	public static boolean saveCivilians(int n, int m, char[][] grid) {
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

		System.out.println("TerPos ");
		terPos.forEach(pos -> System.out.println(Arrays.toString(pos)));
		System.out.println("civPos ");
		civPos.forEach(pos -> System.out.println(Arrays.toString(pos)));
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
}
