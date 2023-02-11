package com.design.patterns.factorydesign;

public class Airtel extends CellularPlan {

	@Override
	void getRate() {
		rate = 1.25;
	}

}
