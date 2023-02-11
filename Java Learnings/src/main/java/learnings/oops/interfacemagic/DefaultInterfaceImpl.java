package learnings.oops.interfacemagic;

public class DefaultInterfaceImpl implements DefaultInterface4 {

	public static void main(String[] args) {
		DefaultInterface obj = new DefaultInterfaceImpl();
		obj.print();

//		DefaultInterface2 obj2 = new DefaultInterfaceImpl();
//		obj2.print();
	}

	@Override
	public void print(String name) {
		System.out.println(name);
		
	}

	@Override
	public void print() {
		System.out.println("I'm overriden!!!");
		DefaultInterface4.super.print();
	}
}
