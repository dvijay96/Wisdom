package learnings.oops.interfacemagic;

public interface DefaultInterface2 extends DefaultInterface{

	default void print() {
		System.out.println("I'm deafult_2 (child) !!!");
		DefaultInterface.super.print();
	}
	
}
