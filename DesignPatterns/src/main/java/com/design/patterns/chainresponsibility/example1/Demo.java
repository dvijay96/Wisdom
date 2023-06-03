package com.design.patterns.chainresponsibility.example1;

import java.util.concurrent.ThreadLocalRandom;

public class Demo {

	public static void main(String[] args) {

		PaymentHandler bank = new BankPaymentHandler();
		PaymentHandler credit = new CreditCardPaymentHandler();
		PaymentHandler upi = new UPIPaymentHandler();

		bank.setNext(credit);
		credit.setNext(upi);

		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int i = 0; i < 5; i++) {
			double amount = random.nextDouble(1, 6000);
			System.out.print("Amount: " + amount + " ");
			bank.handlePayment(amount);
		}

	}

}
