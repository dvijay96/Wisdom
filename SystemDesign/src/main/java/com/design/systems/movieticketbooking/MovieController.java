package com.design.systems.movieticketbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// To Perform CRUD ops
public class MovieController {

	private Map<City, List<Movie>> movieMap;
	private List<Movie> allMovies;

	MovieController() {
		movieMap = new HashMap<>();
		allMovies = new ArrayList<>();
	}

	public void addMovie(Movie movie, City city) {
		allMovies.add(movie);

		if (!movieMap.containsKey(city)) {
			movieMap.put(city, new ArrayList<>());
		}
		movieMap.get(city).add(movie);
	}

	public List<Movie> getMovieByCity(City city) {
		return movieMap.get(city);
	}

	public Movie getMovieByName(String name) {
		Optional<Movie> optional = allMovies.stream().filter(movie -> movie.getMovieName().equalsIgnoreCase(name))
				.findFirst();
		if (optional.isPresent())
			return optional.get();
		throw new RuntimeException("No such Movie present");
	}
}
