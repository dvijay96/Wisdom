package com.design.systems.movieticketbooking;

public class Movie {

	private int movieId;
	private String movieName;
	/**
	 * movie duration in mins
	 */
	private long movieDuration;
	
	/*
	 * Other details can be added like , Genre , Languages etc.
	 */

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public long getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(long movieDuration) {
		this.movieDuration = movieDuration;
	}

}
