package com.dsalgo.ds.design;

import java.util.HashMap;
import java.util.Map;

/*
 * An underground railway system is keeping track of customer travel times between different stations. 
 * They are using this data to calculate the average time it takes to travel from one station to another. 
 */
public class UndergroundSystem {
	private Map<Integer, Pair> checkInMap;
	private Map<String, int[]> journeyMap;

	public UndergroundSystem() {
		checkInMap = new HashMap<>();
		journeyMap = new HashMap<>();
	}

	/**
	 * A user checks in at station A at time t1.
	 * 
	 * @param id
	 * @param stationName
	 * @param t
	 */
	public void checkIn(int id, String stationName, int t) {
		checkInMap.put(id, new Pair(stationName, t));
	}

	/**
	 * A user checks out from station B at time t2.
	 * 
	 * @param id
	 * @param stationName
	 * @param t
	 */
	public void checkOut(int id, String stationName, int t) {
		if (checkInMap.containsKey(id)) {
			String sourceStation = checkInMap.get(id).station;
			int checkInTime = checkInMap.get(id).time;
			checkInMap.remove(id);

			if (!journeyMap.containsKey(sourceStation + "-" + stationName)) {
				journeyMap.put(sourceStation + "-" + stationName, new int[2]);
			}

			journeyMap.get(sourceStation + "-" + stationName)[0] += (t - checkInTime);
			journeyMap.get(sourceStation + "-" + stationName)[1]++;
		}
	}

	/**
	 * Gives the average time it takes from station A to B.
	 * 
	 * @param startStation
	 * @param endStation
	 * @return
	 */
	public double getAverageTime(String startStation, String endStation) {
		double totalTravelTime = journeyMap.get(startStation + "-" + endStation)[0];
		double totalTrips = journeyMap.get(startStation + "-" + endStation)[1];

		return totalTravelTime / totalTrips;
	}
}

class Pair {
	String station;
	int time;

	Pair(String station, int time) {
		this.station = station;
		this.time = time;
	}
}
