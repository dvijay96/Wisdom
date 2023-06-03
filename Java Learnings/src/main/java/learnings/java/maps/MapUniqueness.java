package learnings.java.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public class MapUniqueness {

	public static void main(String[] args) {

		Map<Employee, Employee> map = new HashMap<>();

		Employee emp = null;

		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 1; i <= 4; i++) {
			emp = new Employee("ABC", random.nextInt(20));
			map.put(emp, emp);
		}

		System.out.println(map);

		Employee lastEmp = map.get(emp);
		System.out.println(lastEmp);

		for (Entry<Employee, Employee> entry : map.entrySet()) {
			System.out.print(entry.getValue().equals(lastEmp) + " ");
			System.out.print((entry.getValue().hashCode() == lastEmp.hashCode()) + " ");
			System.out.println(entry.getValue() == lastEmp);
			System.out.println();
		}
	}

	static class Employee {
		String name;
		int age;

		public Employee(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public boolean equals(Object obj) {
			return true;
		}

		@Override
		public String toString() {
			return "{ " + name + ", " + age + " }";
		}

	}
}
