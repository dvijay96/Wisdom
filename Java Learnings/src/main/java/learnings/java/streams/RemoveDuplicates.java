package learnings.java.streams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import learnings.utility.InputUtil;

public class RemoveDuplicates {

	public static void main(String[] args) {

		List<Integer> list = InputUtil.getIntList(5);

		System.out.println(list);

		Set<Integer> uniques = new HashSet<>();

		List<Integer> duplicates = list.stream().filter(e -> !uniques.add(e)).collect(Collectors.toList());

//		List<Integer> uniques = list.stream().distinct().collect(Collectors.toList());

//		list = list.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());

//		Set<Integer> set = new HashSet<>(list);
//		
//		list = new ArrayList<>(set);

		System.out.println(duplicates);
		System.out.println(uniques);
	}

}
