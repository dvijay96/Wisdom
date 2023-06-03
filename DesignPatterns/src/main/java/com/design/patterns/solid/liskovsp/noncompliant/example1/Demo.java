package com.design.patterns.solid.liskovsp.noncompliant.example1;

public class Demo {

	public static void main(String[] args) {
		Bike bike = new MotorCycle();

		bike.turnOnEngine();
		bike.accelerate();

		bike = new Bicycle();

		bike.turnOnEngine(); // Replacing with other child breaks the program behaviour
		bike.accelerate();

	}

}

interface Bike {
	void turnOnEngine();

	void accelerate();
}

class MotorCycle implements Bike {

	private boolean engineOn;
	private int speed;

	@Override
	public void turnOnEngine() {
		engineOn = true;
		System.out.println("Engine turned on: " + engineOn);

	}

	@Override
	public void accelerate() {
		speed += 10;
		System.out.println("Speed incremented: " + speed);
	}

	public boolean isEngineOn() {
		return engineOn;
	}

}

class Bicycle implements Bike {

	private int speed;

	@Override
	public void turnOnEngine() {
		throw new UnsupportedOperationException("Doesn't have engine");
	}

	@Override
	public void accelerate() {
		if (speed < 100) {
			speed += 10;
			System.out.println("Speed incremented: " + speed);
		} else {
			System.out.println("Speed limit reached!!!");
		}
	}

}