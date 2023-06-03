package com.design.patterns.solid.interfacesp.compliant.example1;

public class Demo {

	public static void main(String[] args) {
		IWaiter waiter = new Waiter();

		waiter.takeOrder();
		waiter.serveCustomer();

		IChef chef = new Chef();

		chef.cookFood();
		chef.decideMenu();
	}

}

interface IWaiter {

	void serveCustomer();

	void takeOrder();
}

interface IChef {
	void cookFood();

	void decideMenu();
}

class Waiter implements IWaiter {

	@Override
	public void serveCustomer() {
		System.out.println("Serving customer !!!");
	}

	@Override
	public void takeOrder() {
		System.out.println("Taking orders !!!");
	}

}

class Chef implements IChef {

	@Override
	public void cookFood() {
		System.out.println("Cooking Food !!!");
	}

	@Override
	public void decideMenu() {
		System.out.println("Deciding Menu !!!");
	}

}