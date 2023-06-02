package com.design.patterns.factorymethod.example1;

public class ZApp {

	public static void main(String[] args) {

		CellularPlan plan = new CellularNetwork("Jio").getPlan();
		plan.getRate();
		plan.processBill(45);
	}

}
