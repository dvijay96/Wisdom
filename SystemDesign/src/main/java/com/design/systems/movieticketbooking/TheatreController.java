package com.design.systems.movieticketbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TheatreController {

	private Map<City, List<Theatre>> theatreMap;

	private List<Theatre> allTheatres;

	public TheatreController() {
		this.theatreMap = new HashMap<>();
		this.allTheatres = new ArrayList<>();
	}

	public void addTheatre(Theatre theatre, City city) {
		allTheatres.add(theatre);

		if (!theatreMap.containsKey(city)) {
			theatreMap.put(city, new ArrayList<>());
		}
		theatreMap.get(city).add(theatre);
	}

	public Map<Theatre, List<Show>> getAllShows(Movie movie, City city) {

		List<Theatre> theaters = theatreMap.get(city);

		Map<Theatre, List<Show>> theatreShowMap = new HashMap<>();

		if (theaters != null && !theaters.isEmpty()) {
			for (Theatre theatre : theaters) {
				List<Show> shows = theatre.getShows().stream()
						.filter(show -> show.getMovie().getMovieName().equalsIgnoreCase(movie.getMovieName()))
						.collect(Collectors.toList());
				if (!shows.isEmpty())
					theatreShowMap.put(theatre, shows);
			}
		}

		return theatreShowMap;
	}
}
