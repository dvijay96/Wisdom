package learnings.oops.interfacemagic;

public interface DefaultInterface {

	default void print() {
		System.out.println("I'm deafult_1 !!!");
	}
	
	void print(String name);
}
