package com.design.patterns;

public class ZApp {

	public static void main(String[] args) {

		CellularPlan plan = new SelectNetwork("Jio").getPlan();
		plan.getRate();
		plan.processBill(45);
	}

}
