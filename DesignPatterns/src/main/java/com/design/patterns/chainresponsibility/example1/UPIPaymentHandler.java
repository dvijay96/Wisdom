package com.design.patterns.chainresponsibility.example1;

public class UPIPaymentHandler extends PaymentHandler {

	@Override
	public void handlePayment(double amount) {
		if (amount <= 5000.0) {
			System.out.println("Payment done by UPI!!!");
		} else if (next != null) {
			next.handlePayment(amount);
		} else {
			System.out.println("Payment can't be processed.");
		}
	}

}
