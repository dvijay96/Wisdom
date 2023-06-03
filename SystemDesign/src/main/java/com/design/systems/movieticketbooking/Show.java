package com.design.systems.movieticketbooking;

import java.util.ArrayList;
import java.util.List;

public class Show {

	private int id;
	private Movie movie;
	private long startTime;
	private Screen screen;
	private List<Integer> bookedIds = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<Integer> getBookedIds() {
		return bookedIds;
	}

	public void setBookedIds(List<Integer> bookedIds) {
		this.bookedIds = bookedIds;
	}

}
