package learnings.java.inheritance;

public class SuperKeyword {

	public static void main(String[] args) {

		Child c = new Child();

		System.out.println(c.name);

		c.updateName();
		System.out.println(c.name);
		System.out.println(((Parent) c).name);
	}

}

class Parent {
	String name = "Parent";

}

class Child extends Parent {
	String name = "Child";

	public void updateName() {
		name = "Child-Updated";
		super.name = "Parent-Child";
	}
}