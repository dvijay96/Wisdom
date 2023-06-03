package learnings.java.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class StreamOpII {

	public static void main(String[] args) {
		
		int[] arr = {4000,3000,1000,2000};
		
		IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
		
		double ans = (double)(stats.getSum() - stats.getMax() - stats.getMin())/ ( stats.getCount() - 2);

//		Arrays.stream(arr);
		System.out.println(ans);
	}

}
