package learnings.java.streams.groupingby;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GroupingByDemoII {

	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<>();

		ThreadLocalRandom random = ThreadLocalRandom.current();

		emps.add(new Employee(Department.ENGINEERING, random.nextInt(20000, 50000), "Rahul", random.nextInt(25, 40)));
		emps.add(new Employee(Department.PAYROLL, random.nextInt(20000, 50000), "Ravi", random.nextInt(25, 40)));
		emps.add(new Employee(Department.SALES, random.nextInt(20000, 50000), "Arjun", random.nextInt(25, 40)));
		emps.add(new Employee(Department.HR, random.nextInt(20000, 50000), "Kunal", random.nextInt(25, 40)));
		emps.add(new Employee(Department.LEARNINGS, random.nextInt(20000, 50000), "Rohit", random.nextInt(25, 40)));
		emps.add(new Employee(Department.ENGINEERING, random.nextInt(20000, 50000), "Lampard", random.nextInt(25, 40)));
		emps.add(new Employee(Department.PAYROLL, random.nextInt(20000, 50000), "Rooney", random.nextInt(25, 40)));
		emps.add(new Employee(Department.HR, random.nextInt(20000, 50000), "Leo", random.nextInt(25, 40)));
		emps.add(new Employee(Department.SALES, random.nextInt(20000, 50000), "Dembele", random.nextInt(25, 40)));
		emps.add(new Employee(Department.SALES, random.nextInt(20000, 50000), "Pollard", random.nextInt(25, 40)));
		emps.add(new Employee(Department.SALES, random.nextInt(20000, 50000), "Tony", random.nextInt(25, 40)));

		Map<Department, List<Employee>> empDep = emps.stream().collect(Collectors.groupingBy(emp -> emp.department));

		empDep.forEach((d, e) -> System.out.println(d + " -> " + e));
		
		// Collect total salary of employees by each department

		Map<Department, Integer> result = emps.stream()
				.collect(Collectors.groupingBy(emp -> emp.department, Collectors.summingInt(emp -> emp.salary)));

		System.out.println(result);
	}

	static class Employee {

		Department department;
		int salary;
		String name;
		int age;

		public Employee(Department department, int salary, String name, int age) {
			super();
			this.department = department;
			this.salary = salary;
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "[ department= " + department + ", salary= " + salary + ", name= " + name + ", age= " + age + " ]";
		}

	}

	enum Department {
		ENGINEERING, HR, PAYROLL, LEARNINGS, SALES;
	}

}
