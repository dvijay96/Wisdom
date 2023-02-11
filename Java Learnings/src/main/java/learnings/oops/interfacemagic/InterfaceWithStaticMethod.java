package learnings.oops.interfacemagic;

public interface InterfaceWithStaticMethod {

	// not accessible to implementing classes
	static void print() {
		System.out.println("from InterfaceWithStaticMethod's print !!!");
	}
	
}
