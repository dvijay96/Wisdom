package learnings.java.cloning;

import java.util.ArrayList;
import java.util.List;

public class CloningDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		shallowCopyDemo();
		System.out.println("\n\n");
		deepCopyDemo();
	}

	private static void deepCopyDemo() throws CloneNotSupportedException {
		System.err.println("Deep Copy Demo \n");
		DeepCopy obj = new DeepCopy("Deep", 1);
		List<Integer> scores = new ArrayList<>();
		scores.add(4);
		scores.add(2);
		scores.add(5);
		scores.add(7);
		obj.setScores(scores);

		DeepCopy clone = (DeepCopy) obj.clone();

		System.out.println("Are they equal (==) " + obj.equals(clone));

		System.out.println("Name: " + obj.name + " || " + clone.name); // primitives are just copied
		System.out.println("Id: " + obj.id + " || " + clone.id);
		System.out.println("Scores: " + obj.scores + " || " + clone.scores);
		System.out.println("Scores eqauls?: " + obj.scores.equals(clone.scores)); // object references are not copied
		System.out.println("Hashcodes: " + obj.hashCode() + " || " + clone.hashCode()); // a new shallow object with
																						// deep copy

		clone.scores.add(8);
		System.out.println(obj.scores + " || " + clone.scores);
		System.out.println(obj.scores.equals(clone.scores));
	}

	private static void shallowCopyDemo() throws CloneNotSupportedException {

		System.err.println("Shallow Copy Demo \n");
		ShallowCopy obj = new ShallowCopy("shallow", 1);
		List<Integer> scores = new ArrayList<>();
		scores.add(4);
		scores.add(2);
		scores.add(5);
		scores.add(7);
		obj.setScores(scores);

		ShallowCopy clone = (ShallowCopy) obj.clone();

		System.out.println(obj.equals(clone));

		System.out.println(obj.name + " || " + clone.name); // primitives are just copied
		System.out.println(obj.id + " || " + clone.id);
		System.out.println(obj.scores + " || " + clone.scores);
		System.out.println(obj.scores.equals(clone.scores)); // object references are copied
		System.out.println(obj.hashCode() + " || " + clone.hashCode()); // a new shallow object

		clone.scores.add(8);
		System.out.println(obj.scores + " || " + clone.scores);
		System.out.println(obj.scores.equals(clone.scores));
	}

	static class ShallowCopy implements Cloneable {

		private String name;
		private int id;

		private List<Integer> scores;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public ShallowCopy(String name, int id) {
			super();
			this.name = name;
			this.id = id;
		}

		public List<Integer> getScores() {
			return scores;
		}

		public void setScores(List<Integer> scores) {
			this.scores = scores;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

	}

	static class DeepCopy implements Cloneable {

		private String name;
		private int id;

		private List<Integer> scores;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public DeepCopy(String name, int id) {
			super();
			this.name = name;
			this.id = id;
		}

		public List<Integer> getScores() {
			return scores;
		}

		public void setScores(List<Integer> scores) {
			this.scores = scores;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			DeepCopy copy = (DeepCopy) super.clone();
			copy.setScores(new ArrayList<>());
			for (int score : this.scores) {
				copy.getScores().add(score);
			}
			return copy;
		}

	}
}
