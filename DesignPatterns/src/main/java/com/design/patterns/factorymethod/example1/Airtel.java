package com.design.patterns.factorymethod.example1;

public class Airtel extends CellularPlan {

	@Override
	void getRate() {
		rate = 0.65;
	}

}
