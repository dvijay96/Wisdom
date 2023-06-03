package com.design.systems.movieticketbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

public class BookMyShowApp {

	private static Scanner scan = new Scanner(System.in);

	private MovieController movieController;
	private TheatreController theatreContoller;

	BookMyShowApp() {
		movieController = new MovieController();
		theatreContoller = new TheatreController();
	}

	public static void main(String[] args) {
		BookMyShowApp app = new BookMyShowApp();
		app.run();
	}

	void run() {
		initializeData();
		System.out.println("Welcome");
		while (true) {
			City city = citySelection();
			List<Movie> movies = movieController.getMovieByCity(city);

			if (movies != null && !movies.isEmpty()) {
				System.out.println("Select Movie: ");

				for (int i = 0; i < movies.size(); i++) {
					System.out.println((i + 1) + " -> " + movies.get(i).getMovieName());
				}

				int userMovie = scan.nextInt();

				Map<Theatre, List<Show>> movieShowsMap = theatreContoller.getAllShows(movies.get(userMovie - 1),
						city);

				if (movieShowsMap != null && !movieShowsMap.isEmpty()) {
					System.out.println("Select Theatre: ");

					for (Theatre theatre : movieShowsMap.keySet()) {
						System.out.println(theatre.getId() + " -> " + theatre.getAddress());
					}
					int theatreId = scan.nextInt();

					Optional<Entry<Theatre, List<Show>>> showOptional = movieShowsMap.entrySet().stream()
							.filter(entry -> entry.getKey().getId() == theatreId).findFirst();
					if (!showOptional.isPresent()) {
						System.out.println("No Shows present for this movie!!!");
						continue;
					}
					List<Show> shows = showOptional.get().getValue();

					System.out.println("Select show ");
					for (Show show : shows) {
						System.out.println(show.getId() + " -> " + show.getStartTime() + ":00 hrs");
					}

					int showId = scan.nextInt();

					Show show = shows.stream().filter(s -> s.getId() == showId).findFirst().get();

					List<Seat> seats = show.getScreen().getSeats();

					SeatCategory[] category = SeatCategory.values();

					System.out.println("Select seat category: ");
					for (int i = 0; i < category.length; i++) {
						System.out.println((i + 1) + " -> " + category[i]);
					}

					int catId = scan.nextInt();

					List<Seat> availableSeats = seats.stream()
							.filter(s -> s.getSeatCategory().equals(category[catId - 1]) && !s.isBooked())
							.collect(Collectors.toList());

					System.out.println("Select seat no:");

					for (int i = 0; i < availableSeats.size(); i++) {
						System.out.print(availableSeats.get(i).getId() + " ");
					}

					int seatNo = scan.nextInt();

					Optional<Seat> optional = availableSeats.stream().filter(s -> s.getId() == seatNo).findFirst();

					if (optional.isPresent()) {
						Seat seat = optional.get();
						ThreadLocalRandom random = ThreadLocalRandom.current();
						Booking booking = new Booking();
						seat.setBooked(true);
						booking.setId(random.nextInt());
						show.getBookedIds().add(booking.getId());

						System.out.println("Movie Booked!!!");

						System.out.println("Booking Id: " + booking.getId());
						System.out.println("Movie Name: " + movies.get(userMovie - 1).getMovieName());
						System.out.println("Show: " + show.getStartTime() + ":00 hrs");
						System.out.println("Screen: " + show.getScreen().getName());
						System.out.println("Seat No: " + seat.getSeatCategory() + " " + seat.getId());

					} else {
						System.out.println("Invalid Seat. Try again!!!");
					}

				} else {
					System.out.println("No Theatres showing!!!");
				}
			} else {
				System.out.println("No movies showing!!!");
			}
		}

		scan.close();
	}

	private City citySelection() {
		System.out.println("\n\nSelect City: ");

		City[] cities = City.values();
		for (int i = 0; i < cities.length; i++) {
			System.out.println((i + 1) + " -> " + cities[i]);
		}

		int userCity = scan.nextInt();

		if (userCity > cities.length) {
			System.out.println("Existing App !!! ");
			
		}

		City city = cities[userCity - 1];
		return city;
	}

	private void initializeData() {
		createMovies();
		createTheatres();
	}

	private void createMovies() {

		Movie avengers = new Movie();
		avengers.setMovieId(1);
		avengers.setMovieName("AVENGERS");
		avengers.setMovieDuration(2);

		Movie baahubali = new Movie();
		baahubali.setMovieId(2);
		baahubali.setMovieName("BAAHUBALI");
		baahubali.setMovieDuration(2);

		movieController.addMovie(avengers, City.MUMBAI);
		movieController.addMovie(avengers, City.DELHI);
		movieController.addMovie(avengers, City.BANGALORE);
		movieController.addMovie(baahubali, City.MUMBAI);
		movieController.addMovie(baahubali, City.DELHI);
		movieController.addMovie(baahubali, City.BANGALORE);
		movieController.addMovie(baahubali, City.KOLKATA);

	}

	private void createTheatres() {

		Movie avengers = movieController.getMovieByName("AVENGERS");
		Movie baahubali = movieController.getMovieByName("BAAHUBALI");

		Theatre inox = new Theatre();
		inox.setId(1);
		inox.setCity(City.MUMBAI);
		inox.setAddress("Street 1");
		inox.setScreens(createScreen());
		List<Show> inoxShows = new ArrayList<>();
		inoxShows.add(createShows(1, inox.getScreens().get(0), avengers, 6));
		inoxShows.add(createShows(2, inox.getScreens().get(1), baahubali, 6));
		inoxShows.add(createShows(3, inox.getScreens().get(1), avengers, 12));
		inoxShows.add(createShows(4, inox.getScreens().get(0), baahubali, 12));
		inoxShows.add(createShows(5, inox.getScreens().get(0), avengers, 18));
		inoxShows.add(createShows(6, inox.getScreens().get(1), baahubali, 18));
		inox.setShows(inoxShows);

		Theatre pvr = new Theatre();
		pvr.setId(2);
		pvr.setCity(City.BANGALORE);
		pvr.setAddress("Street 2");
		pvr.setScreens(createScreen());
		List<Show> pvrShows = new ArrayList<>();
		pvrShows.add(createShows(1, inox.getScreens().get(0), avengers, 6));
		pvrShows.add(createShows(2, inox.getScreens().get(1), baahubali, 6));
		pvrShows.add(createShows(3, inox.getScreens().get(1), avengers, 12));
		pvrShows.add(createShows(4, inox.getScreens().get(0), baahubali, 12));
		pvrShows.add(createShows(5, inox.getScreens().get(0), avengers, 18));
		pvrShows.add(createShows(6, inox.getScreens().get(1), baahubali, 18));
		pvr.setShows(inoxShows);

		theatreContoller.addTheatre(inox, City.MUMBAI);
		theatreContoller.addTheatre(pvr, City.BANGALORE);
	}

	private Show createShows(int showId, Screen screen, Movie movie, int startTime) {
		Show show = new Show();
		show.setId(showId);
		show.setScreen(screen);
		show.setMovie(movie);
		show.setStartTime(startTime);
		return show;
	}

	private List<Screen> createScreen() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		List<Screen> screens = new ArrayList<>();
		Screen screen1 = new Screen();
		screen1.setId(1);
		screen1.setName("screen 1");
		screen1.setSeats(createSeats(random.nextInt(50, 101)));

		screens.add(screen1);

		Screen screen2 = new Screen();
		screen2.setId(2);
		screen2.setName("screen 2");
		screen2.setSeats(createSeats(random.nextInt(50, 101)));

		screens.add(screen2);
		return screens;
	}

	private List<Seat> createSeats(int size) {
		List<Seat> seats = new ArrayList<>();

		int silverSeats = size / 2;
		int goldSeat = size / 3;
		int platinumSeats = size - silverSeats - goldSeat;

		int seatNo = 1;

		for (int i = 0; i < silverSeats; i++) {
			Seat seat = new Seat();
			seat.setId(seatNo++);
			seat.setSeatCategory(SeatCategory.SILVER);
			seats.add(seat);
		}
		for (int i = 0; i < goldSeat; i++) {
			Seat seat = new Seat();
			seat.setId(seatNo++);
			seat.setSeatCategory(SeatCategory.GOLD);
			seats.add(seat);
		}
		for (int i = 0; i < platinumSeats; i++) {
			Seat seat = new Seat();
			seat.setId(seatNo++);
			seat.setSeatCategory(SeatCategory.PLATINUM);
			seats.add(seat);
		}
		return seats;
	}
}
