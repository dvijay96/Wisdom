package com.design.patterns.chainresponsibility.example1;

public class BankPaymentHandler extends PaymentHandler {

	@Override
	public void handlePayment(double amount) {
		if (amount <= 1000.0) {
			System.out.println("Payment done by Bank!!!");
		} else if(next!=null){
			next.handlePayment(amount);
		}else {
			System.out.println("Payment can't be processed.");
		}

	}

}
