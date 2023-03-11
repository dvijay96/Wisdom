package com.design.patterns.singleton;

public class Demo {

	public static void main(String[] args) {
		
		System.out.println(ThreadSafeSingletonII.getInstance());

		System.out.println(ThreadSafeSingletonII.getInstance());
	}

}
