package com.design.patterns.singleton;

public class SingletonEnum {

	public static void main(String[] args) {
		Singleton instance1 = Singleton.INSTANCE;
		Singleton instance2 = Singleton.INSTANCE;

		System.out.println(instance1);
		System.out.println(instance2);
	}

	enum Singleton {
		INSTANCE;
	}

}
