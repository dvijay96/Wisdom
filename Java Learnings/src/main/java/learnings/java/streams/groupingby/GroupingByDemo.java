package learnings.java.streams.groupingby;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByDemo {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<>();

		empList.add(new Employee(new Address("Street1", "Delhi", 123), "ABC", 34));
		empList.add(new Employee(new Address("Street2", "Delhi", 453), "DEF", 25));
		empList.add(new Employee(new Address("Street2", "Delhi", 123), "FGR", 43));
		empList.add(new Employee(new Address("Street11", "Delhi", 212), "CGI", 23));
		empList.add(new Employee(new Address("Street1", "Delhi", 453), "IBM", 34));
		empList.add(new Employee(new Address("Street1", "Delhi", 123), "KOP", 44));
		empList.add(new Employee(new Address("Street13", "Delhi", 212), "BOB", 34));

		Map<Integer, List<Employee>> empMapByPin = empList.stream()
				.collect(Collectors.groupingBy(emp -> emp.address.pincode));

//		empMapByPin.entrySet().forEach(System.out::println);

		empMapByPin.forEach((a, b) -> System.out.println(a + " -> " + b));
	}

	static class Employee {

		public Employee(Address address, String name, int age) {
			super();
			this.address = address;
			this.name = name;
			this.age = age;
		}

		Address address;
		String name;
		int age;

		@Override
		public String toString() {
			return "Employee [address=" + address + ", name=" + name + ", age=" + age + "]";
		}

	}

	static class Address {

		public Address(String streetName, String place, int pincode) {
			super();
			this.streetName = streetName;
			this.place = place;
			this.pincode = pincode;
		}

		String streetName;
		String place;
		int pincode;

		@Override
		public String toString() {
			return "Address [streetName=" + streetName + ", place=" + place + ", pincode=" + pincode + "]";
		}

	}

}
