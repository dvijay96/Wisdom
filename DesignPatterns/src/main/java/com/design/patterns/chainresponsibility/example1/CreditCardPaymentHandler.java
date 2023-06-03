package com.design.patterns.chainresponsibility.example1;

public class CreditCardPaymentHandler extends PaymentHandler {

	@Override
	public void handlePayment(double amount) {
		if (amount <= 2000.0) {
			System.out.println("Payment done by credit card!!!");
		} else if (next != null) {
			next.handlePayment(amount);
		} else {
			System.out.println("Payment can't be processed.");
		}

	}

}
