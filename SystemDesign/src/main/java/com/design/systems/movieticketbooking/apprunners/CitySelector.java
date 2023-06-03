package com.design.systems.movieticketbooking.apprunners;

import java.util.Scanner;

import com.design.systems.movieticketbooking.City;

public class CitySelector extends AppRunner {

	public CitySelector(AppRunner next) {
		super(next);
	}

	@Override
	protected void process(Scanner scan, Object input) {
		System.out.println("Select City!!!");
		
		City[] cities = City.values();
		for(int i=0;i<cities.length;i++) {
			System.out.println((i+1)+" -> "+ cities[i]);
		}
		int city = scan.nextInt();
		
		if(city<=cities.length-1 && city>=0) {
			
		}
	}

}
