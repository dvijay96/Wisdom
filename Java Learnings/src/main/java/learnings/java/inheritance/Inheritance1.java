package learnings.java.inheritance;

public class Inheritance1 {

	public static void main(String[] args) {
		Employee a = new TrainedEmployee("A", "25", "Bhopal");
		Employee b = new Employee("B", "35");

		System.out.println(a);
		System.out.println(b);
	}

}

class Employee {
	private String name;
	private String age;

	public Employee(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [" + name + ", " + age + "]";
	}

}

class TrainedEmployee extends Employee {

	private String trainingArea;

	public TrainedEmployee(String name, String age, String trainingArea) {
		super(name, age);
		this.trainingArea = trainingArea;
	}

	public String getTrainingArea() {
		return trainingArea;
	}

	public void setTrainingArea(String trainingArea) {
		this.trainingArea = trainingArea;
	}

	@Override
	public String toString() {
		return "TrainedEmployee [ " + getName() + ", " + getAge() + ", " + trainingArea + "]";
	}

}