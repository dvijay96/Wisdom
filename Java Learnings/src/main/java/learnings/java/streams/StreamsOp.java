package learnings.java.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsOp {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
		for(int i=1;i<=20;i++) {
			list.add(i);
		}

		// filter
		List<Integer> evens = list.stream().filter(n -> n%2==0).collect(Collectors.toList());
		
		System.out.println(list);
		System.out.println(evens);
		
		// map
		List<Integer> squares = list.stream().map(n -> n*n).collect(Collectors.toList());
		
		System.out.println(list);
		System.out.println(squares);
		
		// flat map
		List<List<Integer>> comb = Arrays.asList(evens,squares);
		
		List<Integer> ans = comb.stream().flatMap(Collection::stream).collect(Collectors.toList());
		
		System.out.println(ans);
		System.out.println(ans.containsAll(squares));
	}

}
