package learnings.collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CustomObjectSorting {

	public static void main(String[] args) {

		List<Employee> list = new ArrayList<>();

		list.add(new Employee(1, "Sony", 24, new Address("Bangalore", "Silk road", 593445)));
		list.add(new Employee(2, "Lenevo", 20, new Address("Indore", "MH road", 500145)));
		list.add(new Employee(3, "Dell", 23, new Address("Mumbai", "DBA road", 957005)));
		list.add(new Employee(4, "Apple", 56, new Address("Pune", "RMR road", 580011)));
		list.add(new Employee(5, "Microsoft", 42, new Address("Delhi", "Rajpath road", 666001)));
		list.add(new Employee(6, "Hp", 21, new Address("Kolkatta", "SCB road", 855921)));

		System.out.println("Unsorted :- ");
		list.forEach(System.out::println);
		System.out.println();

		System.out.println("Sorted by age :- ");
		list.sort((a, b) -> a.getAge() - b.getAge());
		list.forEach(System.out::println);
		System.out.println();

		System.out.println("Sorted by Name :- ");
		list.sort((a, b) -> a.getName().compareTo(b.getName()));
		list.forEach(System.out::println);
		System.out.println();

		System.out.println("Sorted by City :- ");
		list.sort((a, b) -> a.getAddress().getCity().compareTo(b.getAddress().getCity()));
		list.forEach(System.out::println);
		System.out.println();

		TreeSet<Employee> set = new TreeSet<>((a, b) -> a.getAddress().getCity().compareTo(b.getAddress().getCity()));

		for (Employee e : list) {
			set.add(e);
		}

		System.out.println("In a TreeSet:- Comparing by Age");
		set.forEach(System.out::println);
		System.out.println();

	}

}
