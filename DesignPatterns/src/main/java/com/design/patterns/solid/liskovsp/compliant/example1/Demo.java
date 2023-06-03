package com.design.patterns.solid.liskovsp.compliant.example1;

public class Demo {

	public static void main(String[] args) {
		Bike bike = new MotorCycle();

		operation(bike);

		bike = new Bicycle();

		operation(bike);
	}

	private static void operation(Bike bike) {
		if (bike instanceof MachineBike) {
			((MachineBike) bike).turnOnEngine();
		}
		bike.accelerate();
	}

}

interface Bike {
	void accelerate();
}

interface MechanicalBike extends Bike {

}

interface MachineBike extends Bike {
	void turnOnEngine();
}

class MotorCycle implements MachineBike {

	private boolean engineOn;
	private int speed;

	@Override
	public void turnOnEngine() {
		engineOn = true;
		System.out.println(" MotorCycle Engine turned on: " + engineOn);

	}

	@Override
	public void accelerate() {
		speed += 10;
		System.out.println(" MotorCycle Speed incremented: " + speed);
	}

	public boolean isEngineOn() {
		return engineOn;
	}

}

class Bicycle implements MechanicalBike {

	private int speed;

	@Override
	public void accelerate() {
		if (speed < 100) {
			speed += 10;
			System.out.println(" Bicycle Speed incremented: " + speed);
		} else {
			System.out.println(" Bicycle Speed limit reached!!!");
		}
	}

}