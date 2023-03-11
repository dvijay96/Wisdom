package learnings.java.constructors;

public class Constructors {

	private String name;
	private int age;
	private long dis;
	private double speed;

	public Constructors(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Constructors(String name, int age, long dis, double speed) {
		super();
		this.name = name;
		this.age = age;
		this.dis = dis;
		this.speed = speed;
	}

	public Constructors(long dis, double speed) {
		super();
		this.dis = dis;
		this.speed = speed;
	}

	public Constructors() {
		this(2L, 55.67);
//		this("ABC", 23);  Can't have two diff calls to a constructor within a constructor
	}

}
