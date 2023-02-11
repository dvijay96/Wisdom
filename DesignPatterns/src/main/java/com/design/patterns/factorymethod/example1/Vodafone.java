package com.design.patterns.factorymethod.example1;

public class Vodafone extends CellularPlan {

	@Override
	void getRate() {
		rate = 0.25;
	}

}
