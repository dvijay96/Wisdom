package com.design.patterns.solid.interfacesp.noncompliant.example1;

public class Demo {

	public static void main(String[] args) {
		Waiter waiter = new Waiter();

		waiter.cookFood();
		waiter.servesCustomer();
		waiter.washDishes();

	}

}

interface RestaurantEmployee {
	void washDishes();

	void servesCustomer();

	void cookFood();
}

class Waiter implements RestaurantEmployee {

	@Override // Its not a waiter's job to wash dishes
	public void washDishes() {
		System.out.println("Washing dishes? Not my job!!!");
	}

	@Override
	public void servesCustomer() {

		System.out.println("Serving customer !!!");

	}

	@Override // Its not a waiter's job to cook.
	public void cookFood() {
		System.out.println("Cooking Food? Not my job!!!");
	}

}