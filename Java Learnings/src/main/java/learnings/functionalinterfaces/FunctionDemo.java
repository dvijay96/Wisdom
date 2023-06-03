package learnings.functionalinterfaces;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo {

	public static void main(String[] args) {

		Function<Integer, Double> div = (a) -> a / 2.0;

		System.out.println(div.apply(10));
		
		List<Integer> list= new ArrayList<>();
		
		list.stream().max(Comparator.naturalOrder());

	}

}
