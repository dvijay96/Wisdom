package com.design.patterns.factorydesign;

public class ZApp {

	public static void main(String[] args) {

		CellularPlan plan = new SelectNetwork("Airtel").getPlan();
		plan.getRate();
		plan.processBill(6);
	}

}
