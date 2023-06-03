package learnings.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import learnings.utility.InputUtil;

public class ReduceDemo {

	public static void main(String[] args) {

		List<Integer> list = InputUtil.getIntList(10);

		System.out.println(list);

		Optional<Integer> max = list.stream().reduce((a, b) -> a > b ? a : b);
		
		int sum = list.stream().reduce(0, (a,b) -> a+b);

		if (max.isPresent())
			System.out.println(max.get());

		System.out.println(sum);
		
		char[] c = new char[4];
		
		System.out.println(Arrays.toString(c));
	}

}
