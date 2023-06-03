package learnings.java.inheritance;

public class Abstraction {

	public static void main(String[] args) {

		B obj = new C();

		obj.print();
	}

}

abstract class A implements B {

	A() {
		System.out.println("In abstract class A !!!");
	}

}

class C extends A {

	C() {
		System.out.println("In class C !!!");
	}

	@Override
	public void print() {
		System.out.println("C Prints.");
	}

}

interface B {
	default void print() {
		System.out.println("B prints.");
	}
}
