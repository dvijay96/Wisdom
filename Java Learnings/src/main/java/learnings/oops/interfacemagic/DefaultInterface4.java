package learnings.oops.interfacemagic;

public interface DefaultInterface4 extends DefaultInterface2, DefaultInterface3 {

	@Override
	default void print() {
		System.out.println("I'm default_4 (child) !!!");
		DefaultInterface3.super.print();
		DefaultInterface2.super.print();
	}

}
