package learnings.collections.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomObjectSortingWithJava8Streams {

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<>();

		list.add(new Employee(1, "Sony", 24, new Address("Bangalore", "Silk road", 593445)));
		list.add(new Employee(2, "Lenevo", 20, new Address("Indore", "MH road", 500145)));
		list.add(new Employee(3, "Dell", 23, new Address("Mumbai", "DBA road", 957005)));
		list.add(new Employee(4, "Apple", 56, new Address("Pune", "RMR road", 580011)));
		list.add(new Employee(5, "Microsoft", 42, new Address("Delhi", "Rajpath road", 666001)));
		list.add(new Employee(6, "Hp", 21, new Address("Kolkatta", "SCB road", 855921)));
		list.add(new Employee(7, "Xiomi", 20, new Address("Chennai", "BBC road", 854721)));
		list.add(new Employee(8, "Red Hate", 42, new Address("Bhopal", "KBC road", 834021)));

		List<Employee> sortedByAge = list.stream().sorted((a, b) -> b.getAge() - a.getAge())
				.collect(Collectors.toList());

		System.out.println("Sorted by Age (Desc) :- ");
		sortedByAge.forEach(System.out::println);
		System.out.println();

//		List<Employee> top3Elders = list.stream().sorted((a, b) -> b.getAge() - a.getAge()).limit(3)
//				.collect(Collectors.toList());

		Map<Integer, List<Employee>> map = list.stream()
				.collect(Collectors.groupingBy(Employee::getAge, Collectors.toList()));

		System.out.println("Employee age Map :- ");
		map.entrySet().forEach(System.out::println);
		System.out.println();

		List<Employee> top3 = map.entrySet().stream().sorted((a, b) -> b.getKey().compareTo(a.getKey())).limit(3)
				.map(entry -> entry.getValue().get(0)).collect(Collectors.toList());

		System.out.println("Top 3 :- ");
		top3.forEach(System.out::println);
		System.out.println();
	}

}
