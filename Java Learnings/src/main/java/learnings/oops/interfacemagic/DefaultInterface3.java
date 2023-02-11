package learnings.oops.interfacemagic;

public interface DefaultInterface3 extends DefaultInterface {

	default void print() {
		System.out.println("I'm deafult_3 (child) !!!");
		DefaultInterface.super.print();
	}
}
