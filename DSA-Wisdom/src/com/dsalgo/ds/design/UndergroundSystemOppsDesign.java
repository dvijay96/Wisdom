package com.dsalgo.ds.design;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystemOppsDesign {

	public static void main(String... args) {
		UndergroundSystemOp obj = new UndergroundSystemOp();

		obj.checkIn(45, "Leyton", 3);
		obj.checkIn(32, "Paradise", 8);
		obj.checkIn(27, "Leyton", 10);
		obj.checkOut(45, "Waterloo", 15); // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
		obj.checkOut(27, "Waterloo", 20); // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
		obj.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14

		System.out.println(obj.getAverageTime("Paradise", "Cambridge")); // return 14.00000. One trip "Paradise" ->
																			// "Cambridge", (14) / 1 =
		// 14
		System.out.println(obj.getAverageTime("Leyton", "Waterloo")); // return 11.00000. Two trips "Leyton" ->
																		// "Waterloo", (10 + 12) / 2 =
		// 11
		obj.checkIn(10, "Leyton", 24);
		System.out.println(obj.getAverageTime("Leyton", "Waterloo")); // return 11.00000
		obj.checkOut(10, "Waterloo", 38); // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
		System.out.println(obj.getAverageTime("Leyton", "Waterloo"));

	}
}

class UndergroundSystemOp {

	Map<Integer, Passenger> currentPassengerMap;
	Map<String, Route> routeMap;

	public UndergroundSystemOp() {
		currentPassengerMap = new HashMap<>();
		routeMap = new HashMap<>();
	}

	public void checkIn(int id, String stationName, int t) {
		if (!currentPassengerMap.containsKey(id)) {
			Passenger passenger = new Passenger(stationName, t);
			currentPassengerMap.put(id, passenger);
		}
	}

	public void checkOut(int id, String stationName, int t) {
		if (currentPassengerMap.containsKey(id)) {
			Passenger passenger = currentPassengerMap.get(id);
			passenger.checkout(stationName, t);
			String routeKey = passenger.checkinLocation + "," + passenger.checkoutLocation;
			Route route = routeMap.getOrDefault(routeKey,
					new Route(passenger.checkinLocation, passenger.checkoutLocation));
			route.addTrip(passenger.checkinTime, passenger.checkoutTime);
			routeMap.put(routeKey, route);
			currentPassengerMap.remove(id);
		}
	}

	public double getAverageTime(String startStation, String endStation) {
		return routeMap.get(startStation + "," + endStation).getAverageTime();
	}
}

class Passenger {
	int checkinTime;
	int checkoutTime;
	String checkinLocation;
	String checkoutLocation;

	public Passenger(String checkinLocation, int checkinTime) {
		this.checkinLocation = checkinLocation;
		this.checkinTime = checkinTime;
	}

	void checkout(String checkoutLocation, int checkoutTime) {
		this.checkoutLocation = checkoutLocation;
		this.checkoutTime = checkoutTime;
	}

}

class Route {
	String startStation;
	String endStation;
	int totalNumberOfTrips;
	long totalTimeSpentInTrips;

	public Route(String startStation, String endStation) {
		this.startStation = startStation;
		this.endStation = endStation;
	}

	double getAverageTime() {
		return (double) totalTimeSpentInTrips / totalNumberOfTrips;
	}

	void addTrip(int startTime, int endTime) {
		totalTimeSpentInTrips += endTime - startTime;
		totalNumberOfTrips++;
	}
}