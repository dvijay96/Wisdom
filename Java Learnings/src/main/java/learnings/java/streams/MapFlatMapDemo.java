package learnings.java.streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import learnings.utility.InputUtil;

public class MapFlatMapDemo {

	public static void main(String[] args) {

		List<List<Integer>> list = new ArrayList<>();

		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < 5; i++) {
			list.add(InputUtil.getIntList(random.nextInt(1, 10)));
		}

//		for (int i = 0; i < list.size(); i++) {
//			list.add(InputUtil.getIntList(random.nextInt(1, 10)));
//		}

		System.out.println(list);

		Map<Integer, List<List<Integer>>> map = list.stream().map(el -> el)
				.collect(Collectors.groupingBy(e -> e.size()));

		Set<Integer> flatMap = list.stream().flatMap(Collection::stream).collect(Collectors.toSet());

		System.out.println(map);
		System.out.println(flatMap);
	}

}
