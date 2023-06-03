package learnings.java.functions;

import java.util.function.Function;

public class FunctionsDemo {

	public static void main(String[] args) {

		Function<Integer, Integer> doubleIt = i -> 2 * i;

		Function<Integer, Integer> cubeIt = i -> i * i * i;

		int num = 5;
		System.out.println("Number:- " + num);
		System.out.println("Double:-" + doubleIt.apply(num));
		System.out.println("Cube:- " + cubeIt.apply(num));

		// Functional chaining
		System.out.println("double then cube with andThen():- " + doubleIt.andThen(cubeIt).apply(num));
		System.out.println("cube then double with compose():- " + doubleIt.compose(cubeIt).apply(num));
		
		System.out.println();

	}

}
