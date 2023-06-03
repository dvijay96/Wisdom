package learnings.java.inheritance;

public class MultipleInheritance {

	public static void main(String[] args) {
		Motor m = new Car();
		m.oil();
		Motor.print();
	}

}

interface Motor {

	static void print() {
		System.out.println("Motor");
	}

	default void oil() {
		System.out.println("Motor Oil");
	}
}

interface Engine {

	static void print() {
		System.out.println("Motor");
	}

	default void oil() {
		System.out.println("Engine Oil");
	}
}

class Car implements Motor, Engine {

	@Override
	public void oil() {
		System.out.println("Car's Oil!!!");

	}

}