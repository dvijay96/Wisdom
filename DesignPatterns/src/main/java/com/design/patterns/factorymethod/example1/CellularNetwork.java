package com.design.patterns.factorymethod.example1;

public class CellularNetwork {

	private String network;

	public CellularNetwork(String network) {
		super();
		this.network = network;
	}

	public CellularPlan getPlan() {
		if (network == null)
			return null;

		if (network.equalsIgnoreCase("Airtel"))
			return new Airtel();
		else if (network.equalsIgnoreCase("Jio"))
			return new Jio();
		else if (network.equalsIgnoreCase("Vodafone"))
			return new Vodafone();
		throw new RuntimeException("Invalid network type!!!");
	}
}
