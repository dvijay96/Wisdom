package learnings.java.streams;

import java.util.stream.IntStream;

public class Iterations {

	public static void main(String[] args) {

		int sum = 0;
		
		for(int i=0;i<=50;i++) {
			sum+=i;
		}
		
		System.out.println(sum);
		
		System.out.println(IntStream.range(0, 51).sum());

	}

}
