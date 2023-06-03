package com.design.systems.movieticketbooking.apprunners;

import java.util.Scanner;

public abstract class AppRunner {

	protected AppRunner next;

	public AppRunner(AppRunner next) {
		super();
		this.next = next;
	}



	protected abstract void process(Scanner scan, Object input);
}
