package com.design.patterns.factorydesign;

public class Vodafone extends CellularPlan {

	@Override
	void getRate() {
		rate = 1.50;
	}

}
