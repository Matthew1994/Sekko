package com.sekko.times;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Times {
	@Id
	@NotNull
	private String id;
	
	@NotNull
	@Column(nullable=false)
	private String date;
	
	@NotNull
	@Column(nullable=false)
	private String cinemaId;
	
	@NotNull
	@Column(nullable=false)
	private String movieId;
	
	@NotNull
	@Column(nullable=false)
	private String startTime;
	
	@NotNull
	@Column(nullable=false)
	private String endTime;
	
	@NotNull
	@Column(nullable=false)
	private String languageAndEffect;
	
	@NotNull
	@Column(nullable=false)
	private String playingRoom;
	
	@NotNull
	@Column(nullable=false)
	private String price;
	
	public String getEndTime() {
		return endTime;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getCinemaId() {
		return cinemaId;
	}
	
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public String getMovieId() {
		return movieId;
	}
	
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getId() {
		return id;
	}
	
	
	public String getLanguageAndEffect() {
		return languageAndEffect;
	}
	
	public String getPlayingRoom() {
		return playingRoom;
	}
	
	public String getPrice() {
		return price;
	}
}
