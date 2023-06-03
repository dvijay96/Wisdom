package learnings.oops.inheritance;

public class PrivateMembers {

	public static void main(String[] args) {

		Child c = new Child();

		c.setProperty1("Child Property 1");
		c.setProperty2("Child Property 2");
		c.setChildProperty("Child Property");

		System.out.println(c.getChildProperty());
		System.out.println(c.getProperty1());
		System.out.println(c.getProperty2());
	}

}

class Parent {
	private String property1;
	private String property2;

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}
}

class Child extends Parent {

	private String childProperty;

	public String getChildProperty() {
		return childProperty;
	}

	public void setChildProperty(String childProperty) {
		this.childProperty = childProperty;
	}

}